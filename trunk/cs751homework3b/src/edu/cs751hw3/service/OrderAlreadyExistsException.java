/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw3.service;

public class OrderAlreadyExistsException extends Exception {
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}