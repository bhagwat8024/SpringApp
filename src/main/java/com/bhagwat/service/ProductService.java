package com.bhagwat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhagwat.model.Product;


public interface ProductService {
	
	public void insertProduct(List<Product> list);

	
	public List<Product> getProductsByPrice(String color, String size, String gender);

	
	public List<Product> getProductsByRating(String color, String size, String gender);

	
	public void deleteAllProducts();
		
}
