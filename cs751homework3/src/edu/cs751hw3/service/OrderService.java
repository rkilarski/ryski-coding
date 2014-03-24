package edu.cs751hw3.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.TransportInDescription;
import org.apache.axis2.engine.AxisConfiguration;

import edu.cs751hw3.model.BillTo;
import edu.cs751hw3.model.Item;
import edu.cs751hw3.model.Order;

public class OrderService {

    private Map<String, Order> map = new HashMap<String, Order>();
    private String baseURL = null;

    public OrderService() {
        Order order1 = new Order();
        order1.setOrderId("a1");
        order1.setBillTo(new BillTo("Ryszard Kilarski", "881 Comm Ave", "Boston", "MA", "02115",
                "617-353-5000"));
        order1.setOrderItems(new ArrayList<Item>());
        order1.getOrderItems().add(new Item("Item 1", BigInteger.valueOf(1), 3.00));
        order1.getOrderItems().add(new Item("Item 2", BigInteger.valueOf(2), 6.00));

        map.put(order1.getOrderId(), order1);

        Order order2 = new Order();
        order2.setOrderId("a2");
        order2.setBillTo(new BillTo("Suresh Kalathur", "975 Comm Ave", "Boston", "MA", "02116",
                "617-353-5001"));
        order2.setOrderItems(new ArrayList<Item>());
        order2.getOrderItems().add(new Item("Item 3", BigInteger.valueOf(3), 9.00));
        order2.getOrderItems().add(new Item("Item 4", BigInteger.valueOf(4), 12.00));
        map.put(order2.getOrderId(), order2);

    }

    public String[] getOrders() {
        int size = map.size();
        String[] orders = new String[size];
        Iterator<String> iterator = map.keySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String orderId = iterator.next();
            orders[i] = getBaseURL() + "order/" + orderId;
            i++;
        }
        return orders;
    }

    public Order getOrder(String id) throws OrderNotFoundException {
        Order order = map.get(id);
        if (order == null) {
            throw new OrderNotFoundException("Details of order" + id + " cannot be found.");
        }
        return order;
    }

    public String addOrder(Order order) throws OrderAlreadyExistsException {
        String id = order.getOrderId();
        if (map.get(id) != null) {
            throw new OrderAlreadyExistsException("Cannot add details of order " + id
                    + ". This order already exists.");
        }
        map.put(id, order);
        return getBaseURL() + "order/" + id;
    }

    public String updateOrder(String id, Item item) throws OrderNotFoundException {
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
        return getBaseURL() + "order/" + id;
    }

    public void deleteOrder(String id) throws OrderNotFoundException {
        if (map.get(id) == null) {
            throw new OrderNotFoundException("Details of order " + id + " cannot be found.");
        }
        map.remove(id);
    }

    // This method attempts to get the Base URI for this service. This will be used to construct
    // the URIs for the various details returned.
    private String getBaseURL() {
        if (baseURL == null) {
            MessageContext messageContext = MessageContext.getCurrentMessageContext();
            AxisConfiguration configuration =
                    messageContext.getConfigurationContext().getAxisConfiguration();
            TransportInDescription inDescription = configuration.getTransportIn("http");
            try {
                EndpointReference[] eprs =
                        inDescription.getReceiver().getEPRsForService(
                                messageContext.getAxisService().getName(), null);
                baseURL = eprs[0].getAddress();
            } catch (AxisFault axisFault) {
            }
        }
        return baseURL;
    }
}