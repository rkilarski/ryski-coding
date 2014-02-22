/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw2.dom;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.cs751hw2.model.BillTo;
import edu.cs751hw2.model.Invoice;
import edu.cs751hw2.model.Item;

/**
 * This class creates an invoice document from an invoice.
 */
public class CreateInvoiceDOM implements CreateDOM {
	/**
	 * DOM Document
	 */
	private Document document = null;

	public CreateInvoiceDOM(Invoice invoice) {
		DocumentBuilder builder = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setNamespaceAware(true);
			builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// Insert Root element
		Element root = createRootNode(invoice);
		document.appendChild(root);

		// Insert billTo
		root.appendChild(createBillToNode(invoice.getBillTo()));

		// Insert the order items
		root.appendChild(createOrderItemsNode(invoice.getOrderItems()));

		// Insert the totals: tax/shipping/total cost.
		this.insertTotals(root, invoice);

		// Normalizing the DOM
		document.getDocumentElement().normalize();
	}

	/**
	 * @return Document
	 */
	public Document getDocument() {
		return document;
	}

	private Node createBillToNode(BillTo billTo) {
		Element billToNode = document.createElement(ELEMENT_BILL_TO);
		billToNode.appendChild(this.createElement(ELEMENT_NAME, billTo.getName()));
		billToNode.appendChild(this.createElement(ELEMENT_COMPANY, billTo.getCompany()));
		billToNode.appendChild(this.createElement(ELEMENT_STREET, billTo.getStreet()));
		billToNode.appendChild(this.createElement(ELEMENT_CITY, billTo.getCity()));
		billToNode.appendChild(this.createElement(ELEMENT_STATE, billTo.getState()));
		billToNode.appendChild(this.createElement(ELEMENT_ZIP_CODE, billTo.getZipCode()));
		billToNode.appendChild(this.createElement(ELEMENT_COUNTRY, billTo.getCountry()));
		return billToNode;
	}

	private Node createElement(String elementName, String value) {
		Node child = null;
		child = document.createElement(elementName);
		child.setTextContent(value);
		return child;
	}

	private Node createItemNode(Item item) {
		Element orderItem = document.createElement(ELEMENT_ITEM);

		// Add attributes.
		orderItem.setAttribute(ATTRIBUTE_UPC, item.getUpc());
		orderItem.setAttribute(ATTRIBUTE_QUANTITY, Integer.toString(item.getQuantity()));
		orderItem.setAttribute(ATTRIBUTE_UNIT_PRICE, doubleToString(item.getUnitPrice()));

		// Add description.
		orderItem.appendChild(this.createElement(ELEMENT_DESCRIPTION, item.getDescription()));
		return orderItem;
	}

	private String doubleToString(double amount) {
		return String.format("%.2f", amount);
	}

	private Node createOrderItemsNode(List<Item> orderItems) {
		Element order = document.createElement(ELEMENT_ORDER);
		for (Item item : orderItems) {
			order.appendChild(createItemNode(item));
		}
		return order;
	}

	private Element createRootNode(Invoice invoice) {
		Element root =
				document.createElementNS("http://www.kalathur.com/invoice", ELEMENT_INV_INVOICE);
		root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", "http://www.kalathur.com/invoice ./invoice.xsd");
		// root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance","xsi:schemaLocation",
		// "http://www.kalathur.com/invoice ./invoice.xsd");
		root.setAttribute(ATTRIBUTE_INVOICE_ID, invoice.getInvoiceId());
		root.setAttribute(ATTRIBUTE_INVOICE_DATE, invoice.getInvoiceDate());
		root.setAttribute(ATTRIBUTE_ORDER_ID, invoice.getOrderId());
		root.setAttribute(ATTRIBUTE_CUSTOMER_ID, invoice.getCustomerId());
		return root;
	}

	private void insertTotals(Element parent, Invoice invoice) {
		parent.appendChild(this.createElement(ELEMENT_TAX, doubleToString(invoice.getTax())));
		parent.appendChild(this.createElement(ELEMENT_SHIPPING, doubleToString(invoice.getShipping())));
		parent.appendChild(this.createElement(ELEMENT_TOTAL_COST, doubleToString(invoice.getTotalCost())));
	}
}
