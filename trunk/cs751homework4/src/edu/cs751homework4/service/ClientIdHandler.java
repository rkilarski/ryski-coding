/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751homework4.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

public class ClientIdHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		System.out.println("Server : In handleMessage ClientIdHandler : Outbound " + isResponse);

		if (!isResponse) {
			try {
				SOAPMessage soapMsg = context.getMessage();

				// tracking
				soapMsg.writeTo(System.out);

				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();

				SOAPHeader soapHeader = soapEnv.getHeader();

				// if no header
				if (soapHeader == null) {
					// throw exception
					generateSOAPErrMessage(soapMsg, "No SOAP header.");
					return false;
				}

				// Get client ip address from SOAP header
				Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);

				// if no header block for next actor found? throw exception
				if (it == null || !it.hasNext()) {
					generateSOAPErrMessage(soapMsg, "No header block for next actor.");
					return false;
				}

				// if no client id found? throw exception
				Node node = (Node) it.next();
				String clientId = (node == null) ? null : node.getValue();

				if (node == null) {
					generateSOAPErrMessage(soapMsg, "No client id in header block.");
					return false;
				}

				// if client id is not in range, throw exception
				int clientIdInt = Integer.parseInt(clientId);
				if ((clientIdInt < 100) || (clientIdInt > 200)) {
					generateSOAPErrMessage(soapMsg, "Invalid client id " + clientId
							+ ", access is denied.");
					return false;
				}

			} catch (SOAPException e) {
				System.err.println(e);
				return false;

			} catch (IOException e) {
				System.err.println(e);
				return false;

			}
		}

		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {

		System.out.println("Server : In handleFault  ClientIdHandler......");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	private void generateSOAPErrMessage(SOAPMessage msg, String reason) {

		try {
			SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();

			QName qname = new QName(SOAPConstants.URI_NS_SOAP_ENVELOPE, "Server");
			SOAPFault soapFault = soapBody.addFault(qname, reason);

			throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
			System.err.println(e);
		}

	}

}
