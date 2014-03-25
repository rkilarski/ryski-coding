/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
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
public class RESTClient {

	private static String toEpr = "http://localhost:8080/axis2/services/OrderService";

	public static void main(String[] args) throws AxisFault {
		// testElementOMElement("Testing:  getOrders", testGetOrders(), false);

		// testElementOMElement("Testing:  getOrder 'a1'", testGetOrder("a1"), false);

		testElementOMElement("Testing:  addOrder", testAddOrder(), false);
		// testElementOMElement("Testing:  results of addOrder a1", testGetOrders());

		// testElementOMElement("Testing:  updateOrder", testUpdateOrder("a2"));
		// testElementOMElement("Testing:  results of updateOrder a1", testGetOrder("a1"));

		// testElementOMElement("Testing:  deleteOrder", testDeleteOrder("a1"), true);
		// testElementOMElement("Testing:  results of deleteOrder a1", testGetOrders(), false);
	}

	private static synchronized void testElementOMElement(String testTitle, OMElement element, boolean robust) throws AxisFault {
		if (element != null) {
			Options options = new Options();
			options.setTo(new EndpointReference(toEpr));
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

			options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			if (robust) {
				sender.sendRobust(element);
			} else {
				OMElement result = sender.sendReceive(element);

				try {
					System.out.println("\n\n\n==========================" + testTitle
							+ "==========================\n");
					XMLStreamWriter writer =
							XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);

					System.out.println("\n-----------Raw Response Start\n");
					result.serialize(writer);
					writer.flush();
					System.out.println("\n-----------Raw Response End\n");

					// Convert to DOM and pretty print
					Element resultDOM = XMLUtils.toDOM(result);
					System.out.println("\n\n-----------Pretty Response Start\n");
					DOMUtil.printDOM(resultDOM, "");
					System.out.println("\n-----------Pretty Response End\n");

				} catch (XMLStreamException e) {
					e.printStackTrace();
				} catch (FactoryConfigurationError e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("\n\n\n==========================" + testTitle
					+ " not implemented yet==========================\n");
		}
	}

	private static OMElement testGetOrders() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://axis2.apache.org", "ns");
		OMElement method = fac.createOMElement("getOrders", omNs);
		return method;
	}

	private static OMElement testGetOrder(String id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://axis2.apache.org", "ns");
		OMElement method = fac.createOMElement("getOrder", omNs);
		OMElement value = fac.createOMElement("id", omNs);
		value.addChild(fac.createOMText(value, id));
		method.addChild(value);
		return method;
	}

	private static OMElement testAddOrder() {

		// Convert the POJO into an OMElement
		// OMElement omOrder = convertToOM(order, new QName("order"), new TypeTable());
		// Element resultDOM = XMLUtils.toDOM(omOrder);
		// DOMUtil.printDOM(resultDOM, "");

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://axis2.apache.org", "ns");
		OMElement method = fac.createOMElement("addOrder", omNs);

		OMElement order;
		OMElement billTo;
		OMElement element;
		OMElement items;
		OMElement item;

		order = fac.createOMElement("orderId", omNs);
		order.addChild(fac.createOMText(order, "b3"));
		method.addChild(order);

		//Add BillTo.
		billTo = fac.createOMElement("billTo", omNs);
		order.addChild(billTo);
		
		element = fac.createOMElement("name", omNs);
		element.addChild(fac.createOMText(element, "Sheldon Cooper"));
		billTo.addChild(element);

		element = fac.createOMElement("address", omNs);
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

		//Create list of elements.
		items = fac.createOMElement("orderItems", omNs);
		order.addChild(items);
		
		//Create one item.
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

        //Create one item.
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

	private static OMElement testUpdateOrder(String id) {
		return null;
	}

	private static OMElement testDeleteOrder(String id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://axis2.apache.org", "ns");
		OMElement method = fac.createOMElement("deleteOrder", omNs);
		OMElement value = fac.createOMElement("id", omNs);
		value.addChild(fac.createOMText(value, id));
		method.addChild(value);
		return method;
	}
}
