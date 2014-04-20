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

public class SongClient {
	private static String toEpr = "http://localhost:8080/axis2/services/SongService";

	public static void main(String[] args) throws AxisFault {
		String id = null;

		System.out.println("**************************************Testing getList");
		testElementOMElement("Running:  getSongs", testGetList(), false);
		System.out.println("**************************************End getList");
		System.out.println(" ");

		System.out.println("**************************************Testing get");
		testElementOMElement("Running:  get 4", testGet("4"), false);
		System.out.println("**************************************End get");
		System.out.println(" ");

		System.out.println("**************************************Testing add");
		id = testElementOMElement("Running:  add ", testAdd(), false);
		testElementOMElement("Running:  get  after add", testGet(id), false);
		System.out.println("**************************************End add");
		System.out.println(" ");

		System.out.println("**************************************Testing update");
		testElementOMElement("Running:  get before update", testGet(id), false);
		testElementOMElement("Running:  update", testUpdate(id), false);
		testElementOMElement("Running:  get after update", testGet(id), false);
		System.out.println("**************************************End update");
		System.out.println(" ");

		System.out.println("**************************************Testing delete");
		testElementOMElement("Running:  get 2 before deleting it", testGet("2"), false);
		testElementOMElement("Running:  delete", testDelete("2"), true);
		testElementOMElement("Running:  get 2 after deleting it (should result in a SongNotFound fault)", testGet("2"), false);
		System.out.println("**************************************End delete");
	}

	private static OMElement testAdd() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.guitariffic.com", "ns");
		OMElement method = fac.createOMElement("add", omNs);

		OMElement song;
		OMElement element;

		song = fac.createOMElement("song", omNs);
		method.addChild(song);

		element = fac.createOMElement("songName", omNs);
		element.addChild(fac.createOMText(element, "Made In England"));
		song.addChild(element);

		element = fac.createOMElement("artistName", omNs);
		element.addChild(fac.createOMText(element, "Elton John"));
		song.addChild(element);

		return method;
	}

	private static OMElement testDelete(String id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.guitariffic.com", "ns");
		OMElement method = fac.createOMElement("delete", omNs);
		OMElement value = fac.createOMElement("id", omNs);
		value.addChild(fac.createOMText(value, id));
		method.addChild(value);
		return method;
	}

	private static synchronized String testElementOMElement(String testTitle, OMElement element, boolean robust) throws AxisFault {
		String returnString = null;
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
					try {
						returnString =
								resultDOM.getElementsByTagName("ns:return").item(0).getTextContent();
						returnString = returnString.substring(returnString.lastIndexOf("=") + 1);
					} catch (Exception ex) {
					}
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
		return returnString;
	}

	private static OMElement testGet(String id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.guitariffic.com", "ns");
		OMElement method = fac.createOMElement("get", omNs);
		OMElement value = fac.createOMElement("id", omNs);
		value.addChild(fac.createOMText(value, id));
		method.addChild(value);
		return method;
	}

	private static OMElement testGetList() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.guitariffic.com", "ns");
		OMElement method = fac.createOMElement("getList", omNs);
		return method;
	}

	private static OMElement testUpdate(String id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.guitariffic.com", "ns");
		OMElement method = fac.createOMElement("update", omNs);

		OMElement element, song;

		song = fac.createOMElement("song", omNs);
		method.addChild(song);

		element = fac.createOMElement("id", omNs);
		element.addChild(fac.createOMText(element, id));
		song.addChild(element);

		element = fac.createOMElement("songName", omNs);
		element.addChild(fac.createOMText(element, "Made In England"));
		song.addChild(element);

		element = fac.createOMElement("artistName", omNs);
		element.addChild(fac.createOMText(element, "Elton John"));
		song.addChild(element);

		element = fac.createOMElement("lyrics", omNs);
		element.addChild(fac.createOMText(element, "I was made in England"));
		song.addChild(element);

		element = fac.createOMElement("lyrics", omNs);
		element.addChild(fac.createOMText(element, "Out of Cadillac muscle"));
		song.addChild(element);

		element = fac.createOMElement("id", omNs);
		element.addChild(fac.createOMText(element, id));
		method.addChild(element);

		return method;
	}

}
