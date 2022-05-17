package com.bhagwat.dao;

import java.util.List;

import com.bhagwat.model.Product;

public interface ProductDAO {
	
	public List<Product> getProductsByPrice(String colour,String size,String gender);
	public List<Product> getProductsByRating(String colour,String size,String gender);
	public void insertProducts(List<Product> list);
	public void deleteAllProducts();
	
}
