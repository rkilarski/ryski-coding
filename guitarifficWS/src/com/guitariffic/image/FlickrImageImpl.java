/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

import client.DOMUtil;

public class FlickrImageImpl extends ImageImpl {

    private static String toEpr = "http://api.flickr.com/services/feeds/photos_public.gne?tags=";

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

    private static OMElement flickrElement() {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://SongService.guitariffic.com", "ns");
        OMElement method = fac.createOMElement("getOrders", omNs);
        return method;
    }

    public void callFlickr(String search) throws IOException {
        //URL url = new URL("http://api.flickr.com/services/feeds/photos_public.gne?tags=" + search);
        try {
            
            URL url = new URL("http://api.flickr.com/services/feeds/photos_public.gne?tags=" + search);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
     
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
     
            BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
     
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
     
            conn.disconnect();
     
          } catch (MalformedURLException e) {
     
            e.printStackTrace();
     
          } catch (IOException e) {
     
            e.printStackTrace();
     
          }
    }
}
