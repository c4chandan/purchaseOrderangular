package com.project.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.UserDao;
import com.project.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	public boolean addUser(User uObj) {
		try {

			logger.info("i am in my UserDaoImpl and method name is addUser");
			Session session = sessionFactory.getCurrentSession();
			session.save(uObj);
			return true;
		} catch (Exception e) {

			logger.error("error in addUser" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public User validateUser(String email, String pass) {
		try {
			logger.info("i am in my UserDaoImpl and method name is validateUser");

			Session session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("from com.project.model.User where User_Email=:email and User_Password=:password");

			query.setParameter("email", email);
			query.setParameter("password", pass);

			List<User> list = query.list();

			if (list.size() != 0) {
				return list.get(0);
			}

		} catch (Exception e) {

			logger.error("error in validateUser" + e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public User getSeller() {
		try {

			logger.info("i am in my UserDaoImpl and method name is getSeller");
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from com.project.model.User where userRole='Seller'");

			List<User> list = query.list();

			if (list.size() != 0) {

				return list.get(0);

			}

		} catch (Exception e) {

			logger.error("error in getSeller" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User getUser(int id) {
		try {

			logger.info("i am in my UserDaoImpl and method name is getUser");
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from com.project.model.User where userRole='Buyer' and userId="+id);

			List<User> list = query.list();

			if (list.size() != 0) {

				return list.get(0);

			}

		} catch (Exception e) {

			logger.error("error in getUser" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	
}
