/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw2.model;

import java.util.List;

/**
 * This class represents the Order. 
 * 
 */
public class Order {
    private String customerId;
    protected BillTo billTo;
    protected String orderId;
    protected List<Item> orderItems;
    protected String submitted;

    public BillTo getBillTo() {
        return billTo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Item> getOrderItems() {
        return orderItems;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setBillTo(BillTo billTo) {
        this.billTo = billTo;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderItems(List<Item> order) {
        this.orderItems = order;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

}