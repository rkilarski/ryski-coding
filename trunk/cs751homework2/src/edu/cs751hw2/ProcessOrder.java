package edu.cs751hw2;

/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */

import org.w3c.dom.Document;

import edu.cs751hw2.dom.CreateInvoiceDOM;
import edu.cs751hw2.dom.CreateOrderDOM;
import edu.cs751hw2.dom.DOMUtil;
import edu.cs751hw2.model.Invoice;
import edu.cs751hw2.model.Order;

/**
 * Main program for everything. This reads in an xml order file and produces an xml invoice file.
 */
public class ProcessOrder {

	static String orderFileName = "res/inputOrder.xml";
	static String invoiceFileName = "res/outputInvoice.xml";

	public static void main(String[] args) {

		// Read in the document.
		Document orderDocument = DOMUtil.parse(orderFileName);
		out("Order document:");
		DOMUtil.printDOM(orderDocument);

		// Create an order out of the document.
		CreateOrderDOM orderDOM = new CreateOrderDOM(orderDocument);
		Order order = orderDOM.getOrder();

		// Create an invoice from the order.
		Invoice invoice = new Invoice(order);

		// Create a document from the invoice.
		CreateInvoiceDOM invoiceDOM = new CreateInvoiceDOM(invoice);
		Document invoiceDocument = invoiceDOM.getDocument();

		// Write the invoice.
		out("\n\nInvoice document:");
		DOMUtil.printDOM(invoiceDocument);
		DOMUtil.writeXmlToFile(invoiceFileName, invoiceDocument);
	}

	/**
	 * Prints to the System output a message
	 * 
	 * @param message
	 *            String
	 */
	private static void out(String message) {
		System.out.println(message);
	}
}
