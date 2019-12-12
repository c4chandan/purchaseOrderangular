package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dao.ProductDao;
import com.project.dao.VendorProductDao;
import com.project.model.User;
import com.project.model.VendorProduct;

import org.springframework.http.HttpStatus;

@Controller
@CrossOrigin("http://localhost:4200")
public class vendorController {

	@Autowired
	VendorProductDao vendorDaoObj;

	@Autowired
	HttpSession session;

	@Autowired
	ProductDao productDaoObj;
	private static final Logger logger = Logger.getLogger(vendorController.class);

	@PostMapping(value = "/updateProductQuantity")
	public ResponseEntity<?> addInVendorproductTable(@RequestParam(name = "pId") int pId,
			@RequestParam(name = "quantity") int quantity) {
		try {
			logger.info("i am in my vendorController and method name is updateProductQuantity");

			User userObj = (User) session.getAttribute("uObj");
			VendorProduct r = vendorDaoObj.checkProductForVendor(userObj.getUserId(), pId);
			if (r != null) {
				r.setQuantity(r.getQuantity() + quantity);
			} else {
				r = new VendorProduct();
				r.setVendorId(userObj.getUserId());
				r.setProductId(pId);
				r.setQuantity(quantity);
			}

			vendorDaoObj.add(r);
			return new ResponseEntity<String>("Person added successfully", HttpStatus.OK);

		} catch (Exception e) {
			logger.error("error in updateProductQuantity" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/viewAllAvailableProducts")
	public ResponseEntity<?> viewAllAvailableProducts() {

		try {
			logger.info("i am in my vendorController and method name is viewAllAvailableProducts");

			User userObj = (User) session.getAttribute("uObj");
			System.out.println(session.getAttribute("uObj"));
			int vendorId = userObj.getUserId();

			List<?> vendorProducts = vendorDaoObj.getAllProducts(vendorId);

			return new ResponseEntity<List<?>>(vendorProducts, HttpStatus.OK);

		} catch (Exception e) {
			logger.info("i am in my vendorController and method name is viewAllAvailableProducts");

			e.printStackTrace();
		}
		return null;
	}
}
