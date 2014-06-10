package edu.homework2.bean;

/**
 * This is the products element, containing the list of products.
 */
public class Products {
    private ProductBean[] products;

    public Products(ProductBean... products) {
        this.products = products;
    }

    public ProductBean[] getProducts() {
        return products;
    }

    public void setProducts(ProductBean[] products) {
        this.products = products;
    }

}
