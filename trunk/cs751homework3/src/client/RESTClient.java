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
 * This is a Client progam that accesses 'StudentService' web service
 */
public class RESTClient {

    private static String toEpr = "http://localhost:8080/axis2/services/StudentService";

    public static void main(String[] args) throws AxisFault {

        Options options = new Options();
        options.setTo(new EndpointReference(toEpr));
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);

        ServiceClient sender = new ServiceClient();
        sender.setOptions(options);
        OMElement result = sender.sendReceive(getPayload2());

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
    }

    private static OMElement getPayload1() {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://axis2.apache.org", "ns");
        OMElement method = fac.createOMElement("getStudents", omNs);

        return method;
    }

    private static OMElement getPayload2() {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://axis2.apache.org", "ns");
        OMElement method = fac.createOMElement("getStudent", omNs);
        OMElement value = fac.createOMElement("name", omNs);
        value.addChild(fac.createOMText(value, "Suresh"));
        method.addChild(value);

        return method;
    }
}
