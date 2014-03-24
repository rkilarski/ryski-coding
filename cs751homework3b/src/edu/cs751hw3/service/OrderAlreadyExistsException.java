package edu.cs751hw3.service;

public class OrderAlreadyExistsException extends Exception {
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}