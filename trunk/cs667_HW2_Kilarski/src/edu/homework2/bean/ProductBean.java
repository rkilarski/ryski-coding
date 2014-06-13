/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.homework2.bean;

/**
 * This is the product bean, describing the product.
 */
public class ProductBean {

    private String name;
    private double price;

    public ProductBean() {
    }

    public ProductBean(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
