/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.restfully.order.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents the Order annotated by JAXB.
 */
@XmlRootElement(name = "order")
public class Order {
	protected BillTo billTo;
	protected String orderId;
	protected List<Item> orderItems;

	@XmlElement
	public BillTo getBillTo() {
		return billTo;
	}

	@XmlElement
	public String getOrderId() {
		return orderId;
	}

	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	public List<Item> getOrderItems() {
		return orderItems;
	}

	public void setBillTo(BillTo billTo) {
		this.billTo = billTo;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setOrderItems(List<Item> order) {
		this.orderItems = order;
	}
}