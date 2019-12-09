package com.purchaseOrder.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purchaseOrder.dao.BuyerDao;
import com.purchaseOrder.model.Buyer;
import com.purchaseOrder.services.BuyerService;

@Service("buyerService")
@Transactional
public class BuyerServiceImpl implements BuyerService {
	

	@Autowired
	BuyerDao buyerdao;
	
	@Override
	public boolean registerBuyer(Buyer uobj) {
		uobj.setRole("Buyer");
		uobj.setIsActive("Y");
		uobj.setCreated_date(LocalDate.now());
		uobj.setCreated_by("System");

		return buyerdao.registerBuyer(uobj);
		
	}

	@Override
	public Buyer Validatelogin(Buyer uobj) {
		
		
		return  buyerdao.Validatelogin(uobj);
	}

	@Override
	public boolean deletePerson(Buyer pObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePerson(Buyer pObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Buyer getPersonById(int buyerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Buyer> getAllPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
