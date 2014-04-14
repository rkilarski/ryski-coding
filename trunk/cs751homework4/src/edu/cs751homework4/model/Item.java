/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751homework4.model;

import java.math.BigInteger;

public class Item {

	private double price;
	private String productName;
	private BigInteger quantity;

	public Item() {
	}

	public double getPrice() {
		return price;
	}

	public String getProductName() {
		return productName;
	}

	public BigInteger getQuantity() {
		return quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}
}
