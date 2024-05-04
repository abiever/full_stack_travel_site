package com.example.demo.services;

import com.example.demo.dao.CartInterface;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;


@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CartInterface cartInterface;
    public CheckoutServiceImpl(CartInterface cartInterface) {

        this.cartInterface = cartInterface;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) throws InvalidCheckoutException {
        //retrieve the order from the purchase obj above
        Cart cart = purchase.getCart();
        if (cart == null) {
            throw new InvalidCheckoutException("Cart is null. Order cannot be completed.");
        }

        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        //populate cart with cartItems
        Set<CartItem> cartItems = purchase.getCartItems();
        if (cartItems.isEmpty()) {
            throw new InvalidCheckoutException("Cart items are empty. Order cannot be completed.");
        }
        cartItems.forEach(item -> cart.add(item));

        //get customer from cart (from purchase), set 'cart' customer to this customer
        Customer customer = purchase.getCustomer();
        if (customer == null) {
            throw new InvalidCheckoutException("Customer is null. Order cannot be completed.");
        }
        cart.setCustomer(customer);

        //set status (ordered)
        cart.setStatus(StatusType.ordered);

        //save cart to the database (not customer?)
        cartInterface.save(cart);

        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() { //don't need to pass anything here

        //generate a random UUID number
        return UUID.randomUUID().toString();
    }
}
