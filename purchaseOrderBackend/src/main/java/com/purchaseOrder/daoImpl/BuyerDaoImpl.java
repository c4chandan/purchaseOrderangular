package com.purchaseOrder.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.purchaseOrder.dao.BuyerDao;
import com.purchaseOrder.model.Buyer;

@Repository("userDao")
@Transactional
public class BuyerDaoImpl implements BuyerDao {
	

	@Autowired
	SessionFactory sessionfactory;

	@Override
	public boolean registerBuyer(Buyer uobj) {

		try {
			Session session = sessionfactory.getCurrentSession();
			session.save(uobj);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Buyer Validatelogin(Buyer uobj) {
		try {
			Session session = sessionfactory.getCurrentSession();
			Query query = session.createQuery("from com.purchaseOrder.model.Buyer where Email=:x and password=:y");
			query.setParameter("x", uobj.getEmail());
			query.setParameter("y", uobj.getBuyer_password());
			List<Buyer> list = query.list();
			if (list == null) {
				session.close();

			} else {
				if (list.size() != 0) {

					return list.get(0);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	
	@Override
	public boolean deletePerson(Buyer pObj) {
		try {
			Session session = sessionfactory.getCurrentSession();
			session.delete(pObj);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePerson(Buyer pObj) {
		try {
			Session session = sessionfactory.getCurrentSession();
			session.update(pObj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public Buyer getPersonById(int buyerId) {
		try {
			Session session = sessionfactory.getCurrentSession();
			Buyer pObj = session.get(Buyer.class,buyerId);
			return pObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	}

	@Override
	public List<Buyer> getAllPersons() {
		try {
			Session session = sessionfactory.getCurrentSession();
			Query query = session.createQuery("from com.angular.model.Person");
			List<Buyer> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
}
