/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.image;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import client.DOMUtil;

public class FlickrImageImpl extends ImageImpl {

	private static String toEpr = "http://api.flickr.com/services/feeds/photos_public.gne?tags=";

	private static OMElement flickrElement() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://SongService.guitariffic.com", "ns");
		OMElement method = fac.createOMElement("getOrders", omNs);
		return method;
	}

	/*
	 * 
	<entry>
	<title>Classic Albums Live: Elton John - Greatest Hits</title>
	<link rel="alternate" type="text/html" href="http://www.flickr.com/photos/brockuniversity/13898482674/"/>
	<id>tag:flickr.com,2005:/photo/13898482674</id>
	<published>2014-04-16T17:05:51Z</published>
	<updated>2014-04-16T17:05:51Z</updated>
	<flickr:date_taken>2014-04-03T01:47:18-08:00</flickr:date_taken>
	<dc:date.Taken>2014-04-03T01:47:18-08:00</dc:date.Taken>
	
	<content type="html">			&lt;p&gt;&lt;a href=&quot;http://www.flickr.com/people/brockuniversity/&quot;&gt;Brock University&lt;/a&gt; posted a photo:&lt;/p&gt;
	
	&lt;p&gt;&lt;a href=&quot;http://www.flickr.com/photos/brockuniversity/13898482674/&quot; title=&quot;Classic Albums Live: Elton John - Greatest Hits&quot;&gt;&lt;
	
	img src=&quot;http://farm4.staticflickr.com/3817/13898482674_a3e489d961_m.jpg&quot; 
	
	width=&quot;240&quot; height=&quot;160&quot; alt=&quot;Classic Albums Live: Elton John - Greatest Hits&quot; /&gt;&lt;/a&gt;&lt;/p&gt;

	&lt;p&gt;April 3/14&lt;br /&gt;
	Photo credit: Michael Chess&lt;/p&gt;</content>
	
	<author>
	  <name>Brock University</name>
	  <uri>http://www.flickr.com/people/brockuniversity/</uri>
	  <flickr:nsid>34294159@N07</flickr:nsid>
	  <flickr:buddyicon>http://farm4.staticflickr.com/3815/buddyicons/34294159@N07.jpg?1369402023#34294159@N07</flickr:buddyicon>
	</author>
	<link rel="enclosure" type="image/jpeg" href="http://farm4.staticflickr.com/3817/13898482674_a3e489d961_b.jpg" />
	<category term="eltonjohn" scheme="http://www.flickr.com/photos/tags/" />
	<category term="classicalbumslive" scheme="http://www.flickr.com/photos/tags/" />
	<category term="centrefortheartsbrockuniversity" scheme="http://www.flickr.com/photos/tags/" />
	<displaycategories>
	        </displaycategories>
	</entry>


	 *
	 */
	public List<String> callFlickr(String search) throws IOException {
		List<String> urlList = new ArrayList<String>();
		try {

			URL url =
					new URL("http://api.flickr.com/services/feeds/photos_public.gne?tags=" + search);
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

				NodeList nodes = doc.getElementsByTagName("entry");
				for (int temp = 0; temp < nodes.getLength(); temp++) {
					Node node = nodes.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						String urlString =
								element.getElementsByTagName("link").item(0).getAttributes().getNamedItem("href").getTextContent();
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
		}
		return urlList;
	}

	@Override
	public List<String> getImages(String search) throws AxisFault {
		Options options = new Options();
		options.setTo(new EndpointReference(toEpr + search));
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

		options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);

		ServiceClient sender = new ServiceClient();
		sender.setOptions(options);

		OMElement element = flickrElement();
		OMElement result = sender.sendReceive(element);
		try {
			XMLStreamWriter writer =
					XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);

			result.serialize(writer);
			writer.flush();

			// Convert to DOM and pretty print
			Element resultDOM = XMLUtils.toDOM(result);
			DOMUtil.printDOM(resultDOM, "");

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
