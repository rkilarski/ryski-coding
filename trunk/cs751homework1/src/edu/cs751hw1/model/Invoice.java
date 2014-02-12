/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw1.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents the Invoice. For our purposes, the invoice currently extends from the
 * Order since they share all the same fields.
 */
public class Invoice extends Order {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final double TAX = .05;
    private String invoiceDate;
    private String invoiceId;
    private double itemTotalCost;
    private double shipping;
    private double tax;
    private double totalCost;

    // Create the invoice from the order.
    public Invoice(Order order) {
        this.setBillTo(order.getBillTo());
        this.setOrderId(order.getOrderId());
        this.setSubmitted(order.getSubmitted());
        this.setCustomerId(order.getCustomerId());

        this.setOrderItems(order.getOrderItems());

        // Calculate the prices and the total item cost.
        this.calculatePrices();

        this.setInvoiceId(generateInvoice());
        this.setInvoiceDate(generateInvoiceDate());

        this.setShipping(this.calculateShipping());
        this.setTax(this.calculateTax());
        this.setTotalCost(this.calculateTotalCost());

    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public double getItemTotalCost() {
        return itemTotalCost;
    }

    public Double getShipping() {
        return shipping;
    }

    public Double getTax() {
        return tax;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setItemTotalCost(double itemTotalCost) {
        this.itemTotalCost = itemTotalCost;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    private void calculatePrices() {
        double totalCost = 0;
        for (Item item : this.orderItems) {
            item.setUnitPrice(this.getPrice(item.getUpc()));
            totalCost += item.getUnitPrice() * item.getQuantity();
        }
        this.setItemTotalCost(totalCost);
    }

    private Double calculateShipping() {
        return 10.00;
    }

    private Double calculateTax() {
        return this.getItemTotalCost() * TAX;
    }

    private Double calculateTotalCost() {
        return this.getItemTotalCost() + this.getTax() + this.getShipping();
    }

    private String generateInvoice() {
        return "123456";
    }

    private String generateInvoiceDate() {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);

        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(today);
    }

    private double getPrice(String upc) {
        double price = 0.0;
        if (upc.equals("XYZ-01")) {
            price = 2.00;
        } else if (upc.equals("XYZ-02")) {
            price = 3.00;
        } else if (upc.equals("XYZ-03")) {
            price = 4.00;
        } else {
            price = 5.00;
        }
        return price;
    }

}