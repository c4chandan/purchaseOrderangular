package com.project.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import com.project.dao.PurchaseOrderItemsDao;

@Repository("pruchaseOrderItems")
@Transactional
public class PurchaseOrderItemsDaoImpl implements PurchaseOrderItemsDao {

	@Autowired
	SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(PurchaseOrderItemsDaoImpl.class);

	@Override
	public List<PurchaseOrderItemsDao> getLineItemsById(int purchaseOrderId) {
		try {

			logger.info("i am in my PurchaseOrderItemsDaoImpl and method name is getLineItemsById");
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery("from com.project.model.PurchaseOrderItems where purchaseOrderId=:x");
			q.setParameter("x", purchaseOrderId);
			List<PurchaseOrderItemsDao> poiList = q.list();
			return poiList;
		} catch (Exception e) {

			logger.error("error in getLineItemsById" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
