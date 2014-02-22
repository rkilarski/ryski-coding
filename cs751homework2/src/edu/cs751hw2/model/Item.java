/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw2.model;

public class Item {

    private String description;
    private int  quantity;
    private double unitPrice;
    private String upc;
    
    public String getDescription() {
        return description;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public String getUpc() {
        return upc;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public void setUpc(String upc) {
        this.upc = upc;
    }
    
}
