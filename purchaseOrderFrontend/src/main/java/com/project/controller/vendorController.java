package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	
	@PostMapping(value="/updateProductQuantity")
	public ResponseEntity<?> addInVendorproductTable(@RequestParam(name="pId") int pId  , @RequestParam(name="quantity") int quantity)
	{
		User userObj=(User)session.getAttribute("uObj");							 	//Get userId from session
		VendorProduct r=vendorDaoObj.checkProductForVendor(userObj.getUserId(), pId);   //check if product is already added, if present then update the quantity else add the quantity
		if(r!=null) {
			r.setQuantity(r.getQuantity()+quantity);      								//Quantity added to existing product's quantity
		}
		else {
			r = new VendorProduct();													//else create new row for vendor product and set values 
			r.setVendorId(userObj.getUserId());
			r.setProductId(pId);
			r.setQuantity(quantity);
		}
		
		vendorDaoObj.add(r);                    										//call add method from VendorProductDao

		return new ResponseEntity<String>("Person added successfully",HttpStatus.OK);
	}
	
	@GetMapping(value = "/viewAllAvailableProducts")
	public ResponseEntity<?> viewAllAvailableProducts() {
		
		User userObj=(User)session.getAttribute("uObj");  
		System.out.println(session.getAttribute("uObj"));
		int vendorId=userObj.getUserId();                      						    //fetch user's id through session 
		
		List<?> vendorProducts = vendorDaoObj.getAllProducts(vendorId);      //view products added by vendor getallProducts method in VendorProductDao
				
		return new ResponseEntity<List<?>>(vendorProducts,HttpStatus.OK);    											    //return view product

	}
}
