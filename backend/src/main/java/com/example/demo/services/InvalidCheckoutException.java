package com.example.demo.services;

// Custom exception class for handling invalid cart or empty cart items
public class InvalidCheckoutException extends Exception {
    public InvalidCheckoutException(String message) {
        super(message);
    }
}
