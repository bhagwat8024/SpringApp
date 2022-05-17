package com.bhagwat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhagwat.dao.ProductDAO;
import com.bhagwat.dao.ProductDAOImpl;
import com.bhagwat.model.Product;
 
@Service
public class ProductServiceImpl implements ProductService{

@Autowired
private ProductDAO productDAO;

@Transactional
public void insertProduct(List<Product> list) {
	System.out.println("in insertProduct"+list.size());
	productDAO.insertProducts(list);
	return;
}

@Transactional
public List<Product> getProductsByPrice(String color, String size, String gender){
	return productDAO.getProductsByPrice(color, size, gender);
}

@Transactional
public List<Product> getProductsByRating(String color, String size, String gender){
	return productDAO.getProductsByRating(color, size, gender);
}

@Transactional
public void deleteAllProducts() {
	productDAO.deleteAllProducts();
}
	
}
