package com.example.demo.services;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase) throws InvalidCheckoutException;

}
