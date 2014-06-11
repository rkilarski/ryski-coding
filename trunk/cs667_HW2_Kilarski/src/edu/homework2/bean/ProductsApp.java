package edu.homework2.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * This is the application-scoped list of products.
 */
@ManagedBean
@ApplicationScoped
public class ProductsApp extends Products {

    public ProductsApp() {
        super(new ProductBean("Item 1", 5.00), new ProductBean("Item 2", 10.00), new ProductBean(
                "Item 3", 15.00), new ProductBean("Item 4", 20.00), new ProductBean("Item 5",
                25.00));
        System.out.println("productsApp access");
    }
}
