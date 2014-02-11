package edu.cs751hw1.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.cs751hw1.model.BillTo;
import edu.cs751hw1.model.Item;
import edu.cs751hw1.model.Order;

public class CreateOrderDOM {
    /**
     * DOM Document
     */
    private Document document = null;

    public CreateOrderDOM(Document document) {
        this.document = document;
    }

    public Order getOrder() {
        Order order = new Order();

        Node list = document.getElementsByTagName("po:purchaseOrder").item(0);
        order.setCustomerId(getAttribute(list, "customerId"));
        order.setSubmitted(getAttribute(list, "submitted"));
        order.setOrderId(getAttribute(list, "orderId"));

        order.setBillTo(createBillTo(document));
        order.setOrder(createOrders(document));
        return order;
    }

    public Document getDocument() {
        return document;
    }

    private BillTo createBillTo(Document document) {
        BillTo billTo = new BillTo();
        billTo.setName(document.getElementsByTagName("name").item(0).getTextContent());
        billTo.setCompany(document.getElementsByTagName("company").item(0).getTextContent());
        billTo.setStreet(document.getElementsByTagName("street").item(0).getTextContent());
        billTo.setCity(document.getElementsByTagName("city").item(0).getTextContent());
        billTo.setState(document.getElementsByTagName("state").item(0).getTextContent());
        billTo.setZipCode(document.getElementsByTagName("zipCode").item(0).getTextContent());
        billTo.setCountry(document.getElementsByTagName("country").item(0).getTextContent());
        return billTo;
    }

    private List<Item> createOrders(Document document) {
        List<Item> order = new ArrayList<Item>();
        NodeList list = document.getElementsByTagName("item");

        for (int i = 0; i < list.getLength(); i++) {
            order.add(createItem(list.item(i)));
        }

        return order;
    }

    private String getAttribute(Node node, String attribute) {
        NamedNodeMap attributes = node.getAttributes();
        return attributes.getNamedItem(attribute).getNodeValue();
    }

    private Item createItem(Node node) {
        Item item = new Item();
        String upc = getAttribute(node, "upc");
        item.setUpc(upc);

        item.setQuantity(getAttribute(node, "quantity"));
        item.setUnitPrice(getPrice(upc));

        // Get the description.
        String description = node.getChildNodes().item(0).getTextContent();
        item.setDescription(description);
        return item;
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
