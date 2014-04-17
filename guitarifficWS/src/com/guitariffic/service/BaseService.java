/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.service;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.TransportInDescription;
import org.apache.axis2.engine.AxisConfiguration;

public class BaseService {
	private String baseURL;

	// This method attempts to get the Base URI for this service. This will be used to construct
	// the URIs for the various details returned.
	protected String getBaseURL() {
		if (baseURL == null) {
			try {
				MessageContext messageContext = MessageContext.getCurrentMessageContext();
				AxisConfiguration configuration =
						messageContext.getConfigurationContext().getAxisConfiguration();
				TransportInDescription inDescription = configuration.getTransportIn("http");
				try {
					EndpointReference[] eprs =
							inDescription.getReceiver().getEPRsForService(messageContext.getAxisService().getName(), null);
					baseURL = eprs[0].getAddress();
				} catch (AxisFault axisFault) {
				}
			} catch (Exception ex) {
				baseURL = "";
			}
		}
		return baseURL;
	}

}
