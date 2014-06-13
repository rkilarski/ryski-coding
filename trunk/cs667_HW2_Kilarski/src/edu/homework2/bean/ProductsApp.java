/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.homework2.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * This is the application-scoped list of products. It creates an initial set of products.
 */
@ManagedBean
@ApplicationScoped
public class ProductsApp extends Products {

    public ProductsApp() {
        super(new ProductBean("Dr. Who Tardis", 5.00), new ProductBean(
                "Luke Skywalker Lightsaber", 10.00), new ProductBean("Captain America Shield",
                15.00), new ProductBean("Eye of Agamotto", 20.00), new ProductBean(
                "U.S.S. Enterprise NCC-1701-E", 25.00));
    }
}
