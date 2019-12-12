package com.project.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PurchaseOrderDao;
import com.project.model.PurchaseOrder;
import com.project.service.purchaseOrderDaoService;

@Service("purchaseOrderDaoService")
public class purchaseOrderDaoImplService implements purchaseOrderDaoService {

	@Autowired
	PurchaseOrderDao purchaseOrderdao;
	
	
	@Override
	public boolean addPurchaseOrder(PurchaseOrder pobj) {
	
		return purchaseOrderdao.addPurchaseOrder(pobj);
	}

	@Override
	public List<PurchaseOrder> viewAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
