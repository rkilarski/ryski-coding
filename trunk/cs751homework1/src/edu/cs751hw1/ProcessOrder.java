/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw1;

import org.w3c.dom.Document;

import edu.cs751hw1.dom.CreateInvoiceDOM;
import edu.cs751hw1.dom.CreateOrderDOM;
import edu.cs751hw1.dom.DOMUtil;
import edu.cs751hw1.model.Invoice;
import edu.cs751hw1.model.Order;

public class ProcessOrder {

    static String invoiceFileName = "res/outputInvoice.xml";
    static String orderFileName = "res/inputOrder.xml";

    public static void main(String[] args) {

        Document document = null;
        Order order = null;
        Invoice invoice = null;

        // Parse in document.
        document = DOMUtil.parse(orderFileName);
        out("Order document:");
        DOMUtil.printDOM(document);

        // Create an order out of the document.
        CreateOrderDOM orderDOM = new CreateOrderDOM(document);
        order = orderDOM.getOrder();

        // Create an invoice from the order.
        invoice = new Invoice(order);

        // Create an invoice DOM from the invoice.
        CreateInvoiceDOM invoiceDOM = new CreateInvoiceDOM(invoice);

        // Write the invoice.
        out("\n\nInvoice document:");
        DOMUtil.printDOM(invoiceDOM.getDocument());
        DOMUtil.writeXmlToFile(invoiceFileName, invoiceDOM.getDocument());
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
