package com.project.daoimpl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.ProductDao;
import com.project.model.Products;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

	@Override
	public boolean addProduct(Products productObj) {
		try {

			logger.info("i am in my ProductDaoImpl and method name is addProduct");
			Session session = sessionFactory.getCurrentSession();

			session.saveOrUpdate(productObj);
			return true;
		} catch (Exception e) {

			logger.error("error in addProduct" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Products> viewAllProducts() {
		try {

			logger.info("i am in my ProductDaoImpl and method name is viewAllProducts");
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery("from com.project.model.Products");
			List<Products> productList = q.list();
			return productList;
		} catch (Exception e) {

			logger.error("error in viewAllProducts" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteProduct(Products productObj) {
		try {

			logger.info("i am  in my ProductDaoImpl and method name is deleteProduct");
			Session session = sessionFactory.getCurrentSession();
			session.delete(productObj);
			return true;
		} catch (Exception e) {

			logger.error("error in deleteProduct" + e.getMessage());
			e.printStackTrace();

		}

		return false;
	}

	@Override
	public Products getProductByName(String productName) {
		try {

			logger.info("i am in my ProductDaoImpl and method name is getProductByName");
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery("from com.project.model.Products where product_name=:x");
			q.setParameter('x', productName);
			List productList = q.list();
			if (productList.size() != 0) {
				return (Products) productList.get(0);
			}
		} catch (Exception e) {

			logger.error("error in getProductByName" + e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean updateProduct(Products productObj) {
		try {

			logger.info("i am in my ProductDaoImpl and method name is updateProduct");
			Session session = sessionFactory.getCurrentSession();
			session.update(productObj);
			return true;
		} catch (Exception e) {

			logger.error("error in updateProduct" + e.getMessage());
			e.printStackTrace();

		}

		return false;
	}

	@Override
	public Products getProductById(int productId) {
		try {

			logger.info("i am in my ProductDaoImpl and method name is getProductById");
			Session session = sessionFactory.getCurrentSession();
			Products pro = session.get(Products.class, productId);
			return pro;
		} catch (Exception e) {

			logger.error("error in getProductById" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
