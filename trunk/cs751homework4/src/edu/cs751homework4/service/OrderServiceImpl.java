/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751homework4.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import edu.cs751homework4.model.BillTo;
import edu.cs751homework4.model.Item;
import edu.cs751homework4.model.Order;

@WebService(endpointInterface = "edu.cs751homework4.service.OrderService", wsdlLocation = "META-INF/OrderServiceImplService.wsdl")
@HandlerChain(file = "handler-chain.xml")
public class OrderServiceImpl implements OrderService {

	private static Map<String, Order> map = null;

	public OrderServiceImpl() {
		if (map == null) {
			map = new HashMap<String, Order>();

			Order order1 = new Order();
			order1.setOrderId("a1");
			order1.setBillTo(new BillTo());
			order1.getBillTo().setName("Ryszard Kilarski");
			order1.getBillTo().setStreet("881 Comm Ave");
			order1.getBillTo().setCity("Boston");
			order1.getBillTo().setState("MA");
			order1.getBillTo().setZipCode("02115");
			order1.getBillTo().setPhone("617-353-5000");

			order1.setOrderItems(new ArrayList<Item>());
			Item item1 = new Item();
			item1.setProductName("Item 1");
			item1.setPrice(3.00);
			item1.setQuantity(BigInteger.valueOf(1));

			order1.getOrderItems().add(item1);

			Item item2 = new Item();
			item2.setProductName("Item 2");
			item2.setPrice(6.00);
			item2.setQuantity(BigInteger.valueOf(2));
			order1.getOrderItems().add(item2);

			map.put(order1.getOrderId(), order1);

			Order order2 = new Order();
			order2.setOrderId("a2");
			order2.setBillTo(new BillTo());
			order2.getBillTo().setName("Suresh Kalathur");
			order2.getBillTo().setStreet("975 Comm Ave");
			order2.getBillTo().setCity("Boston");
			order2.getBillTo().setState("MA");
			order2.getBillTo().setZipCode("02116");
			order2.getBillTo().setPhone("617-353-5001");
			order2.setOrderItems(new ArrayList<Item>());

			Item item3 = new Item();
			item3.setProductName("Item 3");
			item3.setPrice(9.00);
			item3.setQuantity(BigInteger.valueOf(3));
			order2.getOrderItems().add(item3);

			Item item4 = new Item();
			item4.setProductName("Item 4");
			item4.setPrice(12.00);
			item4.setQuantity(BigInteger.valueOf(4));
			order2.getOrderItems().add(item4);

			map.put(order2.getOrderId(), order2);
		}
	}

	public Order[] getOrders() {
		int size = map.size();
		Order[] orders = new Order[size];
		Iterator<String> iterator = map.keySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			String orderId = iterator.next();
			orders[i] = map.get(orderId);
			i++;
		}
		return orders;
	}

	public Order getOrder(String id) {// throws OrderNotFoundException {
		Order order = map.get(id);
		// if (order == null) {
		// throw new OrderNotFoundException("Details of order" + id + " cannot be found.");
		// }
		return order;
	}

	public boolean addOrder(Order order) {// throws OrderAlreadyExistsException {
		String id = order.getOrderId();
		if (map.get(id) != null) {
			// throw new OrderAlreadyExistsException("Cannot add details of order " + id
			// + ". This order already exists.");
			return false;
		}
		map.put(id, order);
		return true;
	}

	public boolean updateOrder(String id, Item item) {// throws OrderNotFoundException {
		Order order = map.get(id);
		if (order == null) {
			// throw new OrderNotFoundException("Details of order " + id + " cannot be found.");
			return false;
		}
		boolean found = false;
		for (Item orderItem : order.getOrderItems()) {
			if (orderItem.getProductName().equals(item.getProductName())) {
				orderItem.setQuantity(item.getQuantity());
				orderItem.setPrice(item.getPrice());
				found = true;
			}
		}
		// if (!found) {
		// return false;
		// throw new OrderNotFoundException("Details of order " + id + " with item " +
		// item.getProductName() + " cannot be found.");
		// }
		return found;
	}

	public boolean deleteOrder(String id) {// throws OrderNotFoundException {
		if (map.get(id) == null) {
			// throw new OrderNotFoundException("Details of order " + id + " cannot be found.");
			return false;
		}
		map.remove(id);
		return true;
	}
}