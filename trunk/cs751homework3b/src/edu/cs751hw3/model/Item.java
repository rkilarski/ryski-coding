/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw3.model;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "item", propOrder = { "price", "productName", "quantity" })
public class Item {

	public Item() {
	}

	public Item(String productName, BigInteger quantity, double price) {
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	private String productName;
	private BigInteger quantity;
	private double price;

	public String getProductName() {
		return productName;
	}

	public BigInteger getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
