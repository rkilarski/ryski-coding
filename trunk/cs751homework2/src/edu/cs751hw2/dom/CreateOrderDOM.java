/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw2.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.cs751hw2.model.BillTo;
import edu.cs751hw2.model.Item;
import edu.cs751hw2.model.Order;

/**
 * This class creates an Order object from the document.
 */
public class CreateOrderDOM implements CreateDOM {
	private Document document = null;

	/**
	 * Public constructor.
	 */
	public CreateOrderDOM(Document document) {
		this.document = document;
	}

	public Document getDocument() {
		return document;
	}

	/**
	 * Method to do the actual building of the order object.
	 */
	public Order getOrder() {
		Order order = new Order();

		Node list = document.getElementsByTagName(ELEMENT_PO_PURCHASE_ORDER).item(0);
		order.setCustomerId(getAttribute(list, ATTRIBUTE_CUSTOMER_ID));
		order.setSubmitted(getAttribute(list, ATTRIBUTE_SUBMITTED));
		order.setOrderId(getAttribute(list, ATTRIBUTE_ORDER_ID));

		order.setBillTo(createBillTo(document));
		order.setOrderItems(createOrderItems(document));
		return order;
	}

	/**
	 * Private method to create the billTo object. This method scans the document and builds the
	 * order object.
	 */
	private BillTo createBillTo(Document document) {
		BillTo billTo = new BillTo();
		billTo.setName(document.getElementsByTagName(ELEMENT_NAME).item(0).getTextContent());
		billTo.setCompany(document.getElementsByTagName(ELEMENT_COMPANY).item(0).getTextContent());
		billTo.setStreet(document.getElementsByTagName(ELEMENT_STREET).item(0).getTextContent());
		billTo.setCity(document.getElementsByTagName(ELEMENT_CITY).item(0).getTextContent());
		billTo.setState(document.getElementsByTagName(ELEMENT_STATE).item(0).getTextContent());
		billTo.setZipCode(document.getElementsByTagName(ELEMENT_ZIP_CODE).item(0).getTextContent());
		billTo.setCountry(document.getElementsByTagName(ELEMENT_COUNTRY).item(0).getTextContent());
		return billTo;
	}

	/**
	 * Helper method to create an item element.
	 */
	private Item createItem(Node node) {
		Item item = new Item();
		String upc = getAttribute(node, ATTRIBUTE_UPC);
		item.setUpc(upc);

		item.setQuantity(Integer.parseInt(getAttribute(node, ATTRIBUTE_QUANTITY)));

		// Get the description.
		item.setDescription(findDescriptionInNode(node));
		return item;
	}

	private String findDescriptionInNode(Node node) {
		NodeList list = node.getChildNodes();
		String text = "";
		for (int i = 0; i < list.getLength(); i++) {
			Node newNode = list.item(i);
			if ((newNode.getNodeType() == Node.ELEMENT_NODE)
					&& (newNode.getNodeName().equals(ELEMENT_DESCRIPTION))) {
				text = newNode.getTextContent();
			}
		}
		return text;
	}

	/**
	 * Private method to create the item orders.
	 */
	private List<Item> createOrderItems(Document document) {
		List<Item> orderItems = new ArrayList<Item>();
		NodeList list = document.getElementsByTagName(ELEMENT_ITEM);

		for (int i = 0; i < list.getLength(); i++) {
			orderItems.add(createItem(list.item(i)));
		}

		return orderItems;
	}

	/**
	 * Private helper method to get an attribute out of a node.
	 */
	private String getAttribute(Node node, String attribute) {
		NamedNodeMap attributes = node.getAttributes();
		return attributes.getNamedItem(attribute).getNodeValue();
	}
}
