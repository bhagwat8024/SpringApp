package com.bhagwat.dao;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bhagwat.model.Product;
import com.bhagwat.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.save(user);
		trx.commit();
		session.close();
		return;
	}

	@Override
	public boolean isUserExist(String username,String password) {
		Session session = sessionFactory.openSession();

		User user = (User) session.get(User.class, username);
		session.close();

		if (user != null && user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

}
