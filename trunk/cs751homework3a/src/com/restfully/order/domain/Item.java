/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.restfully.order.domain;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
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

	@XmlElement
	public String getProductName() {
		return productName;
	}

	@XmlElement
	public BigInteger getQuantity() {
		return quantity;
	}

	@XmlElement
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
