package com.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.dao.ProductDao;
import com.project.dao.PurchaseOrderDao;
import com.project.dao.UserDao;
import com.project.model.Products;
import com.project.model.PurchaseOrder;
import com.project.model.PurchaseOrderItems;
import com.project.service.purchaseOrderDaoService;

@Controller
@CrossOrigin("http://localhost:4200")
public class productsController {

	@Autowired
	ProductDao productDao;
	@Autowired
	UserDao userDao;
	@Autowired
	purchaseOrderDaoService purchaseOrderDaoService;

	private static final Logger logger = Logger.getLogger(productsController.class);

	@GetMapping(value = "/viewAllProducts")
	public ResponseEntity<?> viewAllProducts() {

		try {

			logger.info("i am in my productsController and method name is viewAllProducts");
			List<Products> list = productDao.viewAllProducts();

			if (list.size() != 0) {
				return new ResponseEntity<List<Products>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("list empty", HttpStatus.INTERNAL_SERVER_ERROR);

			}
		} catch (Exception e) {

			logger.error("error in viewAllProducts" + e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	@PostMapping(value = "/raisePurchaseOrder/{userId}")
	public ResponseEntity<?> raisePurchaseOrder(@PathVariable int userId,
			@RequestBody List<PurchaseOrderItems> purchaseOrderItemsList) {

		try {
			logger.info("i am in my productsController and method name is raisePurchaseOrder");
			
			System.out.println("buyer Id"+userId);
			PurchaseOrder po = new PurchaseOrder();
			po.setCreatedDate(LocalDate.now());
			
			po.setSellerObj(userDao.getSeller());
	
			po.setUserObj(userDao.getUser(userId));
			po.setStatus("Sent to Seller");

			for (PurchaseOrderItems obj : purchaseOrderItemsList) {
				obj.setProductObj(productDao.getProductById(obj.getProductId()));
				obj.setPurchaseOrderObj(po);
			}
			po.setPurchaseOrderItemsObj(purchaseOrderItemsList);
			System.out.println("Purchase Order : " + po);

			purchaseOrderDaoService.addPurchaseOrder(po);
			if (po != null) {
				return new ResponseEntity<PurchaseOrder>(po, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("po not Raised", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {

			logger.error("error in raisePurchaseOrder" + e.getMessage());
			e.printStackTrace();

		}
		return null;

	}
	
	@Autowired
	PurchaseOrderDao purchaseOrderDao;

	@GetMapping(value = "/viewAllPo")
	public ResponseEntity<?> viewAllPo() {

		try {
			logger.info("i am in my productsController and method name is viewAllProducts");
			List<PurchaseOrder> list =purchaseOrderDao.viewAllOrders();
			if (list.size() != 0) {
				return new ResponseEntity<List<PurchaseOrder>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("list empty", HttpStatus.INTERNAL_SERVER_ERROR);

			}
		} catch (Exception e) {

			logger.error("error in viewAllProducts" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}


