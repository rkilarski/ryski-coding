/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751homework4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Order.
 */
public class Order {
	protected BillTo billTo;
	protected String orderId;
	protected List<Item> orderItems;

	public BillTo getBillTo() {
		if (billTo == null) {
			return new BillTo();
		}
		return billTo;
	}

	public String getOrderId() {
		return orderId;
	}

	public List<Item> getOrderItems() {
		if (orderItems == null) {
			return new ArrayList<Item>();
		}
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