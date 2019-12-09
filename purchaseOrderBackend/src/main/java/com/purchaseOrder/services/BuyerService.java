package com.purchaseOrder.services;

import java.util.List;

import com.purchaseOrder.model.Buyer;

public interface BuyerService {

	public boolean registerBuyer(Buyer uobj);

	public Buyer Validatelogin(Buyer uobj);
	
	public boolean deletePerson(Buyer pObj);

	public boolean updatePerson(Buyer pObj);

	public Buyer getPersonById(int buyerId);

	public List<Buyer> getAllPersons();
}
