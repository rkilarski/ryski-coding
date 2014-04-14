/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751homework4.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

public class UpdateOrderHandler implements LogicalHandler<LogicalMessageContext> {

	@Override
	public boolean handleMessage(LogicalMessageContext context) {

		Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		System.out.println("Server : In handleMessage UpdateOrderHandler : Outbound " + isResponse);

		if (!isResponse) {
			try {
				LogicalMessage lm = context.getMessage();
				JAXBContext jc = JAXBContext.newInstance("edu.cs751homework4.service");
				// JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
				JAXBElement jaxbObject = (JAXBElement) lm.getPayload(jc);
				System.out.println(jaxbObject.getValue());

				// On UpdateOrder, check that you're sending at least a quantity of 10.
				Object object = jaxbObject.getValue();
				/*
				if (object instanceof edu.cs751homework4.service.UpdateOrder) {
					edu.cs751homework4.service.UpdateOrder order =
							(edu.cs751homework4.service.UpdateOrder) jaxbObject.getValue();
					Item item = order.getArg1();
					if (item.getQuantity().compareTo(BigInteger.valueOf(10)) == -1) {
						throw new WebServiceException("No quantities < 10 allowed");
					}
				}
				*/
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean handleFault(LogicalMessageContext context) {

		System.out.println("Server : In handleFault  UpdateOrderHandler......");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

}
