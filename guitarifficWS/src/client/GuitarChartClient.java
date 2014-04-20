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
		String id = null;
		System.out.println("**************************************Testing getList");
		testElementOMElement("Running:  getCharts", testGetList(), false);
		System.out.println("**************************************End getList");
		System.out.println(" ");

		System.out.println("**************************************Testing get");
		testElementOMElement("Running:  get 1", testGet("1"), false);
		testElementOMElement("Running:  get 15", testGet("15"), false);
		System.out.println("**************************************End get");
		System.out.println(" ");

		System.out.println("**************************************Testing add");
		id = testElementOMElement("Running:  add ", testAdd(), false);
		// testElementOMElement("Running:  results of addChart", testGetList(), false);
		testElementOMElement("Running:  get  after add", testGet(id), false);
		System.out.println("**************************************End add");
		System.out.println(" ");

		System.out.println("**************************************Testing update");
		testElementOMElement("Running:  get 15 before", testGet("15"), false);
		testElementOMElement("Running:  update", testUpdate("15"), false);
		testElementOMElement("Running:  get 15 after", testGet("15"), false);
		System.out.println("**************************************End update");
		System.out.println(" ");

		System.out.println("**************************************Testing delete");
		testElementOMElement("Running:  get 15 before deleting it", testGet("15"), false);
		testElementOMElement("Running:  delete", testDelete("15"), true);
		testElementOMElement("Running:  get 15 after deleting it (should result in a GuitarChartNotFound fault)", testGet("15"), false);
		System.out.println("**************************************End delete");
	}

	private static OMElement testAdd() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://service.guitariffic.com", "ns");
		OMElement method = fac.createOMElement("add", omNs);

		OMElement chart;
		OMElement element;

		chart = fac.createOMElement("chart", omNs);
		method.addChild(chart);

		element = fac.createOMElement("chordName", omNs);
		element.addChild(fac.createOMText(element, "R#"));
		chart.addChild(element);

		element = fac.createOMElement("chordFingering", omNs);
		element.addChild(fac.createOMText(element, "123456"));
		chart.addChild(element);

		element = fac.createOMElement("chordFrets", omNs);
		element.addChild(fac.createOMText(element, "654321"));
		chart.addChild(element);

		element = fac.createOMElement("chordPosition", omNs);
		element.addChild(fac.createOMText(element, "3"));
		chart.addChild(element);

		element = fac.createOMElement("isLeftHandedFrets", omNs);
		element.addChild(fac.createOMText(element, "false"));
		chart.addChild(element);

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

		OMElement element, chart;

		chart = fac.createOMElement("chart", omNs);
		method.addChild(chart);

		element = fac.createOMElement("id", omNs);
		element.addChild(fac.createOMText(element, id));
		chart.addChild(element);

		element = fac.createOMElement("chordName", omNs);
		element.addChild(fac.createOMText(element, "A#m7"));
		chart.addChild(element);

		element = fac.createOMElement("chordFingering", omNs);
		element.addChild(fac.createOMText(element, "113121"));
		chart.addChild(element);

		element = fac.createOMElement("chordFrets", omNs);
		element.addChild(fac.createOMText(element, "123456"));
		chart.addChild(element);

		element = fac.createOMElement("chordPosition", omNs);
		element.addChild(fac.createOMText(element, "13"));
		chart.addChild(element);

		element = fac.createOMElement("isLeftHandedFrets", omNs);
		element.addChild(fac.createOMText(element, "true"));
		chart.addChild(element);

		element = fac.createOMElement("id", omNs);
		element.addChild(fac.createOMText(element, id));
		method.addChild(element);

		return method;
	}
}
