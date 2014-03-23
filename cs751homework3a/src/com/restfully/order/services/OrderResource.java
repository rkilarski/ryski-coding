/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.restfully.order.services;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.restfully.order.domain.BillTo;
import com.restfully.order.domain.Item;
import com.restfully.order.domain.Order;

@Path("/order")
public class OrderResource {
	private Map<String, Order> map = new HashMap<String, Order>();

	/**
	 * Constructor to create some existing data on the server.
	 */
	public OrderResource() {
		Order order1 = new Order();
		order1.setOrderId("a1");
		order1.setBillTo(new BillTo("Ryszard Kilarski", "881 Comm Ave", "Boston", "MA", "02115", "617-353-5000"));
		order1.setOrderItems(new ArrayList<Item>());
		order1.getOrderItems().add(new Item("Item 1", BigInteger.valueOf(1), 3.00));
		order1.getOrderItems().add(new Item("Item 2", BigInteger.valueOf(2), 6.00));

		map.put(order1.getOrderId(), order1);

		Order order2 = new Order();
		order2.setOrderId("a2");
		order2.setBillTo(new BillTo("Suresh Kalathur", "975 Comm Ave", "Boston", "MA", "02116", "617-353-5001"));
		order2.setOrderItems(new ArrayList<Item>());
		order2.getOrderItems().add(new Item("Item 3", BigInteger.valueOf(3), 9.00));
		order2.getOrderItems().add(new Item("Item 4", BigInteger.valueOf(4), 12.00));
		map.put(order2.getOrderId(), order2);

	}

	/**
	 * Adds a given order to the database and returns the URI for the order.
	 * 
	 * @param order
	 * @return
	 * @throws OrderAlreadyExistsException
	 */
	@POST
	@Consumes("application/xml")
	public Response addOrder(Order order) throws OrderAlreadyExistsException {
		String id = order.getOrderId();
		if (map.get(id) != null) {
			throw new OrderAlreadyExistsException("Cannot add details of order " + id
					+ ". This order already exists.");
		}
		map.put(id, order);
		return Response.created(URI.create("/order/" + order.getOrderId())).build();

	}

	/**
	 * Delete the given order.
	 * 
	 * @param id
	 * @throws OrderNotFoundException
	 */
	@DELETE
	@Path("{id}")
	public void deleteOrder(@PathParam("id") String id) throws OrderNotFoundException {
		if (map.get(id) == null) {
			throw new OrderNotFoundException("Details of order " + id + " cannot be found.");
		}
		map.remove(id);
	}

	/**
	 * Returns the XML for an order.
	 * 
	 * @param id
	 * @return
	 * @throws OrderNotFoundException
	 */
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Order getOrder(@PathParam("id") String id) throws OrderNotFoundException {
		Order order = map.get(id);
		if (order == null) {
			throw new OrderNotFoundException("Details of order" + id + " cannot be found.");
		}
		return order;
	}

	/**
	 * Getting all orders returns an array of partial URIs to the data.
	 * 
	 * @return
	 */
	@GET
	@Path("/getall")
	public Response getOrders() {
		int size = map.size();
		String[] orders = new String[size];
		Iterator<String> iterator = map.keySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			String orderId = iterator.next();
			orders[i] = URI.create("/order/" + orderId).toString();
			i++;
		}
		return Response.ok(orders).build();
	}

	/**
	 * Given an item and order id, update the existing item in the exiting order with the new
	 * information.
	 * 
	 * @param id
	 * @param item
	 * @return
	 * @throws OrderNotFoundException
	 */
	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public Response updateOrder(@PathParam("id") String id, Item item) throws OrderNotFoundException {
		Order order = map.get(id);
		if (order == null) {
			throw new OrderNotFoundException("Details of order " + id + " cannot be found.");
		}
		boolean found = false;
		for (Item orderItem : order.getOrderItems()) {
			if (orderItem.getProductName().equals(item.getProductName())) {
				orderItem.setQuantity(item.getQuantity());
				orderItem.setPrice(item.getPrice());
				found = true;
			}
		}
		if (!found) {
			throw new OrderNotFoundException("Details of order " + id + " with item "
					+ item.getProductName() + " cannot be found.");
		}
		return Response.created(URI.create("/order/" + order.getOrderId())).build();
	}
}
