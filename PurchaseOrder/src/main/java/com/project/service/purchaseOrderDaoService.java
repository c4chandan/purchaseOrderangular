package com.project.service;

import java.util.List;

import com.project.model.PurchaseOrder;

public interface purchaseOrderDaoService {
	
	public boolean addPurchaseOrder(PurchaseOrder pobj);
	public List<PurchaseOrder> viewAllOrders();

}
