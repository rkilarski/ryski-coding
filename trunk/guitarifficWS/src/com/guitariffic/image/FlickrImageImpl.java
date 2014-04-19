/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.image;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FlickrImageImpl extends ImageImpl {

	private static String scheme = "http";
	private static String authority = "api.flickr.com";
	private static String path = "/services/feeds/photos_public.gne";
	private static String query = "format=rss2&tags=";
	private static String fragment = null;

	@Override
	public List<String> getImages(String search) throws AxisFault {
		List<String> urlList = new ArrayList<String>();
		try {
			URI uri = new URI(scheme, authority, path, query + search, fragment);
			URL url = new URL(uri.toASCIIString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ connection.getResponseCode());
			}

			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(connection.getInputStream());

				doc.getDocumentElement().normalize();

				NodeList nodes = doc.getElementsByTagName("item");
				for (int temp = 0; temp < nodes.getLength(); temp++) {
					Node node = nodes.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						String urlString =
								element.getElementsByTagName("enclosure").item(0).getAttributes().getNamedItem("url").getTextContent();
						urlList.add(urlString);
					}
				}
			} catch (ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
			connection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return urlList;
	}
}
