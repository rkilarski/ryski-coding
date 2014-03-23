/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.restfully.order.services;

public class OrderAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public OrderAlreadyExistsException(String message) {
		super(message);
	}
}