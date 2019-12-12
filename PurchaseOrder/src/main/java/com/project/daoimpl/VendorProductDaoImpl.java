
package com.project.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.VendorProductDao;
import com.project.model.Products;
import com.project.model.VendorProduct;

@Repository("vendorProductDao")
@Transactional
public class VendorProductDaoImpl implements VendorProductDao {

	@Autowired
	SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(VendorProductDaoImpl.class);

	public Products getProductById(int pId) {
		try {

			logger.info("i in my VendorProductDaoImpl and method name is getProductById");
			Session session = sessionFactory.getCurrentSession();

			Products pro = session.get(Products.class, pId);

			return pro;

		}

		catch (Exception e) {

			logger.error("error in getProductById" + e.getMessage());
			e.printStackTrace();

		}

		return null;
	}

	public List<Products> getAllProducts(int id) {
		try {

			logger.info("i in my VendorProductDaoImpl and method name is getAllProducts");
			Session session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("from com.project.model.VendorProduct where vendorId=" + id);

			return query.list();

		}

		catch (Exception e) {

			logger.error("error in getAllProducts" + e.getMessage());
			e.printStackTrace();

		}

		return null;
	}

	public boolean add(VendorProduct vObj) {

		try {

			logger.info("i in my VendorProductDaoImpl and method name is add");
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(vObj);
			System.out.println(vObj + " ");
			return true;
		} catch (Exception e) {

			logger.error("error in add" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public VendorProduct checkProductForVendor(int vendorId, int productId) {
		try {

			logger.info("i am in my VendorProductDaoImpl and method name is checkProductForVendor");
			Session session = sessionFactory.getCurrentSession();

			Query query = session
					.createQuery("from com.project.model.VendorProduct where vendorId=:x and productId=:y");
			query.setParameter("x", vendorId);
			query.setParameter("y", productId);
			List list = query.list();
			if (list.size() != 0) {
				return (VendorProduct) list.get(0);
			}
		}

		catch (Exception e) {

			logger.error("error in checkProductForVendor" + e.getMessage());
			e.printStackTrace();

		}

		return null;
	}

}