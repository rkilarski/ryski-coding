/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751homework4.client;

import java.math.BigInteger;
import java.util.List;

import edu.cs751homework4.service.BillTo;
import edu.cs751homework4.service.Item;
import edu.cs751homework4.service.Order;
import edu.cs751homework4.service.OrderServiceImplService;
import edu.cs751homework4.service.OrderServiceImplServicePortType;

public class TestClient {
	public static void main(String[] args) {
		OrderServiceImplService service = new OrderServiceImplService();
		OrderServiceImplServicePortType port = service.getOrderServiceImplPort();

		System.out.println("**************************************Testing getOrders");
		testGetOrders(port);
		System.out.println("**************************************End getOrders");
		System.out.println(" ");

		System.out.println("**************************************Testing getOrder");
		testGetOrder("a1", port);
		System.out.println("**************************************End getOrder");
		System.out.println(" ");

		System.out.println("**************************************Testing addOrder");
		testAddOrder(port);
		testGetOrder("b3", port);
		testGetOrders(port);
		System.out.println("**************************************End addOrder");
		System.out.println(" ");

		System.out.println("**************************************Testing updateOrder");
		testUpdateOrder("a2", port);
		testGetOrder("a2", port);
		System.out.println("**************************************End updateOrder");
		System.out.println(" ");

		System.out.println("**************************************Testing deleteOrder");
		testDeleteOrder("a1", port);
		testGetOrders(port);
		System.out.println("**************************************End deleteOrder");

	}

	private static void printOrder(Order order) {
		try {
			System.out.println("order id: " + order.getOrderId());
			System.out.println("name: " + order.getBillTo().getName());
			System.out.println("street: " + order.getBillTo().getStreet());
			System.out.println("city/state/zip: " + order.getBillTo().getCity() + " "
					+ order.getBillTo().getState() + " " + order.getBillTo().getZipCode());
			System.out.println("phone: " + order.getBillTo().getPhone());
			System.out.println(" ");
			List<Item> items = order.getOrderItems();
			System.out.println("items:");
			for (Item item : items) {
				System.out.println("     " + item.getProductName() + " " + item.getPrice() + " "
						+ item.getQuantity());
			}
			System.out.println(" ");
		} catch (Exception ex) {
			System.out.println("error printing order " + ex.getMessage());
		}
	}

	private static void testGetOrders(OrderServiceImplServicePortType port) {
		List<Order> orders = port.getOrders();
		for (Order order : orders) {
			printOrder(order);
		}
	}

	private static void testGetOrder(String id, OrderServiceImplServicePortType port) {
		Order order = port.getOrder(id);

		if (order != null) {
			printOrder(order);
		} else {
			System.out.println("order " + id + " does not exist");
		}
	}

	private static void testAddOrder(OrderServiceImplServicePortType port) {
		Order order = new Order();
		Item item;

		order.setOrderId("b3");

		// Create BillTo element.
		order.setBillTo(new BillTo());

		order.getBillTo().setName("Sheldon Cooper");
		order.getBillTo().setStreet("Les Robles Drive");
		order.getBillTo().setCity("Boston");
		order.getBillTo().setState("CA");
		order.getBillTo().setZipCode("99999");
		order.getBillTo().setPhone("617-353-5000");

		// Create list of elements.
		item = new Item();
		item.setProductName("Item 4");
		item.setPrice(15);
		item.setQuantity(BigInteger.valueOf(5));
		order.getOrderItems().add(item);

		item = new Item();
		item.setProductName("Item 5");
		item.setPrice(18);
		item.setQuantity(BigInteger.valueOf(6));
		order.getOrderItems().add(item);

		if (port.addOrder(order)) {
			System.out.println("order " + order.getOrderId() + " added.");
		} else {
			System.out.println("order " + order.getOrderId() + " NOT added.");
		}
	}

	private static void testUpdateOrder(String id, OrderServiceImplServicePortType port) {
		Item item = new Item();
		item.setProductName("Item 3");
		item.setPrice(28.99);
		item.setQuantity(BigInteger.valueOf(15));

		if (port.updateOrder(id, item)) {
			System.out.println("order " + id + " updated.");
		} else {
			System.out.println("order " + id + " NOT added.");
		}
	}

	private static void testDeleteOrder(String id, OrderServiceImplServicePortType port) {
		if (port.deleteOrder(id)) {
			System.out.println("order " + id + " deleted.");
		} else {
			System.out.println("order " + id + " NOT deleted.");
		}
	}
}
