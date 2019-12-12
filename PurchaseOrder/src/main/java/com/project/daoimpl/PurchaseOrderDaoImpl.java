
package com.project.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.PurchaseOrderDao;
import com.project.model.PurchaseOrder;

@Transactional
@Repository("purchaseOrderDao")
public class PurchaseOrderDaoImpl implements PurchaseOrderDao {

	@Autowired
	SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(PurchaseOrderDaoImpl.class);

	@Override
	public boolean addPurchaseOrder(PurchaseOrder pobj) {
		try {

			logger.info("i am in my PurchaseOrderDaoImpl and method name is addPurchaseOrder");
			Session session = sessionFactory.getCurrentSession();

			session.saveOrUpdate(pobj);
			return true;

		} catch (Exception e) {

			logger.error("error in addPurchaseOrder" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<PurchaseOrder> viewAllOrders() {
		try {

			logger.info("i am in my PurchaseOrderDaoImpl and method name is viewAllOrders");
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery("from com.project.model.PurchaseOrder where status='Sent to Seller'");
			return q.list();

		} catch (Exception e) {

			logger.error("error in viewAllOrders" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}