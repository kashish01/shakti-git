package com.shakti.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ProductDetails {
	int sno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date date;
	String actualDate;
	
	List<String> allProducts;
	String productName;
	String partyName;
	String name;
	Integer price;
	Integer quantity;
	Integer balance;
	
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public List<String> getAllProducts() {
		return allProducts;
	}
	public void setAllProducts(List<String> allProducts) {
		this.allProducts = allProducts;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getActualDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		String format = formatter.format(getDate());
		return format;
	}
	public void setActualDate(String actualDate) {
		this.actualDate = actualDate;
	}
}
