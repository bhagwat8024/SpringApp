package com.bhagwat.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.bhagwat.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public static final String RATING_QUERY = "FROM Product WHERE COLOR=:color AND GENDER=:gender AND SIZE=:size ORDER BY RATING";
	public static final String PRICE_QUERY = "FROM Product WHERE COLOR=:color AND GENDER=:gender AND SIZE=:size ORDER BY PRICE";
	public static final String DELETE_QUERY = "DELETE FROM Product";
	public static final String GET_ALL_QUERY = "FROM Product";
	
	@Override
	public void insertProducts(List<Product> list) {
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		System.out.print("in product dao"+list.size());
		for(Product product:list) {
			session.save(product);
		}
		trx.commit();
		session.close();
		return;
	}

	@Override
	public List<Product> getProductsByPrice(String color, String size, String gender) {
		Session session = sessionFactory.openSession();
		// create the query for hibernate
		Query query = session.createQuery(PRICE_QUERY);
		query.setParameter("color", color);
		query.setParameter("gender", gender);
		query.setParameter("size", size);

		ArrayList<Product> searchList = (ArrayList<Product>) query.list();
		
		session.close();

		return searchList;

	}

	@Override
	public List<Product> getProductsByRating(String color, String size, String gender) {
		Session session = sessionFactory.openSession();
		// create the query for hibernate
		Query query = session.createQuery(RATING_QUERY);
		query.setParameter("color", color);
		query.setParameter("gender", gender);
		query.setParameter("size", size);

		ArrayList<Product> searchList = (ArrayList<Product>) query.list();

		session.close();

		return searchList;

	}
	@Override
	public void deleteAllProducts() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(GET_ALL_QUERY);
		
		List<Product> list = query.list();
		for(Product product:list) {
			session.delete(product);
		}
		session.flush();
		transaction.commit();
	}
}
