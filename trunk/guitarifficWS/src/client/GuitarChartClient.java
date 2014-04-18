/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package client;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.util.XMLUtils;
import org.w3c.dom.Element;

/**
 * This is a Client program that accesses 'OrderService' web service
 */
public class GuitarChartClient {

	private static String toEpr = "http://localhost:8080/axis2/services/GuitarChartService";

	public static void main(String[] args) throws AxisFault {
		System.out.println("**************************************Testing getOrders");
		testElementOMElement("Running:  getOrders", testGetOrders(), false);
		System.out.println("**************************************End getOrders");
		System.out.println(" ");

		System.out.println("**************************************Testing getOrder");
		testElementOMElement("Running:  getOrder a1", testGetOrder("a1"), false);
		System.out.println("**************************************End getOrder");
		System.out.println(" ");

		System.out.println("**************************************Testing addOrder");
		testElementOMElement("Running:  addOrder b3", testAddOrder(), false);
		testElementOMElement("Running:  results of addOrder b3", testGetOrders(), false);
		testElementOMElement("Running:  getOrder b3", testGetOrder("b3"), false);
		System.out.println("**************************************End addOrder");
		System.out.println(" ");

		System.out.println("**************************************Testing updateOrder");
		testElementOMElement("Running:  updateOrder", testUpdateOrder("a2"), false);
		testElementOMElement("Running:  results of updateOrder a2", testGetOrder("a2"), false);
		System.out.println("**************************************End updateOrder");
		System.out.println(" ");

		System.out.println("**************************************Testing deleteOrder");
		testElementOMElement("Running:  deleteOrder", testDeleteOrder("a1"), true);
		testElementOMElement("Running:  results of deleteOrder a1", testGetOrders(), false);
		System.out.println("**************************************End deleteOrder");
	}

	private static OMElement testAddOrder() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.cs751hw3.edu", "ns");
		OMElement method = fac.createOMElement("addOrder", omNs);

		OMElement order;
		OMElement billTo;
		OMElement element;
		OMElement items;
		OMElement item;

		order = fac.createOMElement("order", omNs);
		method.addChild(order);

		element = fac.createOMElement("orderId", omNs);
		element.addChild(fac.createOMText(element, "b3"));
		order.addChild(element);

		// Create BillTo element.
		billTo = fac.createOMElement("billTo", omNs);
		order.addChild(billTo);

		element = fac.createOMElement("name", omNs);
		element.addChild(fac.createOMText(element, "Sheldon Cooper"));
		billTo.addChild(element);

		element = fac.createOMElement("street", omNs);
		element.addChild(fac.createOMText(element, "Les Robles Drive"));
		billTo.addChild(element);

		element = fac.createOMElement("city", omNs);
		element.addChild(fac.createOMText(element, "Boston"));
		billTo.addChild(element);

		element = fac.createOMElement("state", omNs);
		element.addChild(fac.createOMText(element, "CA"));
		billTo.addChild(element);

		element = fac.createOMElement("zipCode", omNs);
		element.addChild(fac.createOMText(element, "99999"));
		billTo.addChild(element);

		element = fac.createOMElement("phone", omNs);
		element.addChild(fac.createOMText(element, "617-353-5000"));
		billTo.addChild(element);

		// Create list of elements.
		items = fac.createOMElement("orderItems", omNs);
		order.addChild(items);

		// Create one item.
		item = fac.createOMElement("item", omNs);
		items.addChild(item);

		element = fac.createOMElement("productName", omNs);
		element.addChild(fac.createOMText(element, "Item 4"));
		item.addChild(element);

		element = fac.createOMElement("quantity", omNs);
		element.addChild(fac.createOMText(element, "5"));
		item.addChild(element);

		element = fac.createOMElement("price", omNs);
		element.addChild(fac.createOMText(element, "15.00"));
		item.addChild(element);

		// Create a second item.
		item = fac.createOMElement("item", omNs);
		items.addChild(item);

		element = fac.createOMElement("productName", omNs);
		element.addChild(fac.createOMText(element, "Item 5"));
		item.addChild(element);

		element = fac.createOMElement("quantity", omNs);
		element.addChild(fac.createOMText(element, "6"));
		item.addChild(element);

		element = fac.createOMElement("price", omNs);
		element.addChild(fac.createOMText(element, "18.00"));
		item.addChild(element);

		return method;
	}

	private static OMElement testDeleteOrder(String id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.cs751hw3.edu", "ns");
		OMElement method = fac.createOMElement("deleteOrder", omNs);
		OMElement value = fac.createOMElement("id", omNs);
		value.addChild(fac.createOMText(value, id));
		method.addChild(value);
		return method;
	}

	private static synchronized void testElementOMElement(String testTitle, OMElement element, boolean robust) throws AxisFault {
		if (element != null) {
			Options options = new Options();
			options.setTo(new EndpointReference(toEpr));
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

			options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			System.out.println("==========================" + testTitle
					+ "==========================");
			// Output the request.
			try {
				Element requestDOM = null;
				requestDOM = XMLUtils.toDOM(element);
				System.out.println("-----------Pretty Request Start-----------");
				DOMUtil.printDOM(requestDOM, "");
				System.out.println("-----------Pretty Request End-----------");
				System.out.println(" ");
			} catch (Exception e1) {
			}

			if (robust) {
				sender.sendRobust(element);
			} else {
				OMElement result = sender.sendReceive(element);

				try {
					XMLStreamWriter writer =
							XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);

					System.out.println("-----------Raw Response Start-----------");
					result.serialize(writer);
					writer.flush();
					System.out.println("-----------Raw Response End-----------");
					System.out.println(" ");

					// Convert to DOM and pretty print
					Element resultDOM = XMLUtils.toDOM(result);
					System.out.println("-----------Pretty Response Start-----------");
					DOMUtil.printDOM(resultDOM, "");
					System.out.println("-----------Pretty Response End-----------");

				} catch (XMLStreamException e) {
					e.printStackTrace();
				} catch (FactoryConfigurationError e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("==========================" + testTitle
					+ " not implemented yet==========================");
		}
	}

	private static OMElement testGetOrder(String id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.cs751hw3.edu", "ns");
		OMElement method = fac.createOMElement("getOrder", omNs);
		OMElement value = fac.createOMElement("id", omNs);
		value.addChild(fac.createOMText(value, id));
		method.addChild(value);
		return method;
	}

	private static OMElement testGetOrders() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.cs751hw3.edu", "ns");
		OMElement method = fac.createOMElement("getOrders", omNs);
		return method;
	}

	private static OMElement testUpdateOrder(String id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.cs751hw3.edu", "ns");
		OMElement method = fac.createOMElement("updateOrder", omNs);

		OMElement element;
		element = fac.createOMElement("id", omNs);
		element.addChild(fac.createOMText(element, id));
		method.addChild(element);

		// Create one item.
		OMElement item;
		item = fac.createOMElement("item", omNs);
		method.addChild(item);

		element = fac.createOMElement("productName", omNs);
		element.addChild(fac.createOMText(element, "Item 3"));
		item.addChild(element);

		element = fac.createOMElement("quantity", omNs);
		element.addChild(fac.createOMText(element, "15"));
		item.addChild(element);

		element = fac.createOMElement("price", omNs);
		element.addChild(fac.createOMText(element, "28.99"));
		item.addChild(element);

		return method;
	}
}
