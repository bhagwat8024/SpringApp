package com.bhagwat.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	
	@Id
	private String Id;
	private String Name, Color, Gender, Size, Avail;
	private double Price, Rating;
	public Product() {}
	public Product(String id, String name, String color, String gender, String size, String avail, double price,
			double rating) {
		super();
		Id = id;
		Name = name;
		Color = color;
		Gender = gender;
		Size = size;
		Avail = avail;
		Price = price;
		Rating = rating;
	}

	public void printDetails() {
		System.out.println("\nName:-" + Name + "\nColor:-" + Color + "\nGender:-" + Gender + "\nSize:-" + Size
				+ "\nRating:-" + Rating + "\nPrice:-" + Price);
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public String getAvail() {
		return Avail;
	}

	public void setAvail(String avail) {
		Avail = avail;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public double getRating() {
		return Rating;
	}

	public void setRating(double rating) {
		Rating = rating;
	}

	
}
