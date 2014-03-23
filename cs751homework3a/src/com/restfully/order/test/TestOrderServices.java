/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */

package com.restfully.order.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.restfully.order.util.DOMUtil;

/**
 * This is a client program that accesses the 'OrderService' web service
 */
public class TestOrderServices {

	private static String BASE_URI = "http://localhost:8888/cs751homework3/services";

	@Test
	public void testAddEditOrder() throws Exception {
		try {
			writeComment("BEGIN Testing: addOrder b3");
			testAddOrderService();
			writeComment("Testing: result of addOrder, b3");
			testGetOrdersService();
			testGetOrderService("b3");
			writeComment("END Testing: addOrder");
		} catch (Exception ex) {
			throw new Exception("FAILED Testing: addOrder", ex);
		}

		try {
			writeComment("BEGIN Testing: updateOrder b3");
			testUpdateOrderService("b3");
			writeComment("Testing: results of updateOrder b3");
			testGetOrderService("b3");
			writeComment("END Testing: updateOrder b3");
		} catch (Exception ex) {
			throw new Exception("FAILED Testing: updateOrder b3", ex);
		}

	}

	@Test
	public void testDelete() throws Exception {

		try {
			writeComment("BEGIN Testing: deleteOrder a2, before:");
			testGetOrdersService();
			testDeleteOrderService("a2");
			writeComment("Results of deleteOrder deleting a2, after:");
			testGetOrdersService();
			writeComment("END Testing: deleteOrder");
		} catch (Exception ex) {
			throw new Exception("FAILED Testing: deleteOrder", ex);
		}
	}

	@Test
	public void testGetOrders() throws Exception {
		// Test the getOrders service.
		try {
			writeComment("BEGIN Testing: getOrders");
			testGetOrdersService();
			writeComment("END Testing: getOrders");
		} catch (Exception ex) {
			throw new Exception("FAILED Testing: getOrders", ex);
		}
	}

	@Test
	public void testGetSingleOrder() throws Exception {
		// Test the getOrder service.
		try {
			writeComment("BEGIN Testing: getOrder a1");
			testGetOrderService("a1");
			writeComment("END Testing: getOrder a1");
		} catch (Exception ex) {
			throw new Exception("FAILED Testing: getOrders", ex);
		}
	}

	// Send a new order to be added to the server database.
	private void testAddOrderService() throws Exception {
		// Create a new order
		String newOrder =
				"<order>" + "<orderId>b3</orderId>" + "<billTo>" + " <name>Sheldon Cooper</name>"
						+ "<address>Les Robles Drive</address>" + "<city>Boston</city>"
						+ "<state>CA</state>" + "<zipCode>99999</zipCode>"
						+ "<phone>617-976-5000</phone>" + "</billTo>" + "<items>" + "<item>"
						+ "<productName>Item 4</productName>" + "<quantity>5</quantity>"
						+ "<price>15.00</price>" + "</item>" + "<item>"
						+ "<productName>Item 5</productName>" + "<quantity>6</quantity>"
						+ "<price>18.00</price>" + "</item>" + "</items>" + "</order>";

		URL postUrl = new URL(BASE_URI + "/order");
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/xml");
		OutputStream os = connection.getOutputStream();
		os.write(newOrder.getBytes());
		os.flush();
		Assert.assertEquals(HttpURLConnection.HTTP_CREATED, connection.getResponseCode());
		System.out.println("Location: " + connection.getHeaderField("Location"));
		connection.disconnect();
	}

	// Delete an existing order.
	private void testDeleteOrderService(String id) throws Exception {
		URL deleteUrl = new URL(BASE_URI + "/order/" + id);
		HttpURLConnection connection = (HttpURLConnection) deleteUrl.openConnection();
		connection.setRequestMethod("DELETE");
		System.out.println("Content-Type: " + connection.getContentType());

		BufferedReader reader =
				new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
		Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, connection.getResponseCode());
		connection.disconnect();
	}

	// Get an order.
	private void testGetOrderService(String id) throws Exception {
		URL getUrl = new URL(BASE_URI + "/order/" + id);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestMethod("GET");
		System.out.println("Content-Type: " + connection.getContentType());

		BufferedReader reader =
				new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String line = reader.readLine();
		String xml = null;
		while (line != null) {
			if (xml == null) {
				xml = line;
			} else {
				xml += line;
			}
			line = reader.readLine();
		}
		Document doc = convertStringToDocument(xml);
		System.out.println("Results of getting order " + id);
		DOMUtil.printDOM(doc, "");

		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
		connection.disconnect();
	}

	// Get the URIs to all orders.
	private void testGetOrdersService() throws Exception {
		URL getUrl = new URL(BASE_URI + "/order/getall/");
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestMethod("GET");
		System.out.println("Content-Type: " + connection.getContentType());

		BufferedReader reader =
				new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String line = reader.readLine();
		System.out.println("Results of getOrders:");
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}

		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
		connection.disconnect();
	}

	// Update an item on an existing order.
	private void testUpdateOrderService(String id) throws Exception {
		// Create a new item
		String updateItem =
				"<item>" + "<productName>Item 4</productName>" + "<quantity>15</quantity>"
						+ "<price>28.99</price>" + "</item>";

		URL putUrl = new URL(BASE_URI + "/order/" + id);
		HttpURLConnection connection = (HttpURLConnection) putUrl.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("PUT");
		connection.setRequestProperty("Content-Type", "application/xml");
		OutputStream os = connection.getOutputStream();
		os.write(updateItem.getBytes());
		os.flush();
		Assert.assertEquals(HttpURLConnection.HTTP_CREATED, connection.getResponseCode());
		System.out.println("Location: " + connection.getHeaderField("Location"));
		connection.disconnect();
	}

	private static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void writeComment(String text) {
		System.out.println("\n\n\n==========================" + text
				+ "==========================\n");
	}

}
