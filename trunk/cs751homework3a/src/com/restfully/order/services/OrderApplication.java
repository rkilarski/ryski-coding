/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.restfully.order.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/services")
public class OrderApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public OrderApplication() {
		singletons.add(new OrderResource());

	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
