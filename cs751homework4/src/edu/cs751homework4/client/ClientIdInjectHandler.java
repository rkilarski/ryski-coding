/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751homework4.client;

import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class ClientIdInjectHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		System.out.println("Client : handleMessage : Outbound " + isRequest);

		// if this is a request, true for outbound messages, false for inbound
		if (isRequest) {
			try {
				SOAPMessage soapMsg = context.getMessage();
				soapMsg.writeTo(System.out);
				System.out.println();

				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				// if no header, add one
				if (soapHeader == null) {
					soapHeader = soapEnv.addHeader();
				}

				// get random clientId
				String ip = getClientId();

				// add a soap header, name as "clientId"
				QName qname = new QName("http://edu.cs751homework4/", "clientId");
				SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(qname);

				soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
				soapHeaderElement.addTextNode(ip);
				soapMsg.saveChanges();

				soapMsg.writeTo(System.out);
				System.out.println();

			} catch (SOAPException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			} catch (Exception e) {
				System.err.println(e);
			}

		}

		// continue other handler chain
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("Client : handleFault()......");
		SOAPMessage soapMsg = context.getMessage();

		try {
			soapMsg.writeTo(System.out);
		} catch (SOAPException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}

		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("Client : close()......");
	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("Client : getHeaders()......");
		return null;
	}

	/**
	 * Return a client id. Currently hardcode returning "100".
	 */
	private String getClientId() {
		/*
		InetAddress ip;
		StringBuffer sb = new StringBuffer();

		try {

			ip = InetAddress.getLocalHost();
			sb.append(ip.getHostAddress());
			System.out.println("Current IP address : " + ip.getHostAddress());

		} catch (UnknownHostException e) {

			e.printStackTrace();

		}
		return sb.toString();
		*/
		return "100";
	}

}