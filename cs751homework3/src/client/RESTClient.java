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

import java.math.BigInteger;
import java.util.ArrayList;

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

import edu.cs751hw3.model.BillTo;
import edu.cs751hw3.model.Item;
import edu.cs751hw3.model.Order;

/**
 * This is a Client program that accesses 'OrderService' web service
 */
public class RESTClient {

	private static String toEpr = "http://localhost:8080/axis2/services/OrderService";

	public static void main(String[] args) throws AxisFault {
		// testElementOMElement("Testing:  getOrders", testGetOrders(), false);

		// testElementOMElement("Testing:  getOrder 'a1'", testGetOrder("a1"), false);

		/*
		testElementOMElement("Testing:  addOrder", testAddOrder());
		testElementOMElement("Testing:  results of addOrder a1", testGetOrders());

		testElementOMElement("Testing:  updateOrder", testUpdateOrder("a2"));
		testElementOMElement("Testing:  results of updateOrder a1", testGetOrder("a1"));
		*/
		testElementOMElement("Testing:  deleteOrder", testDeleteOrder("a1"), true);
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
		Order order = new Order();
		order.setOrderId("b3");
		order.setBillTo(new BillTo("Sheldon Cooper", "Les Robles Drive", "Boston", "CA", "99999", "617-976-5000"));
		order.setOrderItems(new ArrayList<Item>());
		order.getOrderItems().add(new Item("Item 4", BigInteger.valueOf(5), 15.00));
		order.getOrderItems().add(new Item("Item 5", BigInteger.valueOf(6), 18.00));

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://axis2.apache.org", "ns");
		OMElement method = fac.createOMElement("addOrder", omNs);
		OMElement value = fac.createOMElement("order", omNs);
		// value.addChild(fac.createOMElement(value, order));
		method.addChild(value);
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
