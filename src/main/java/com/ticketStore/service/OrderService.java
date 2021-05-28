package com.ticketStore.service;

import com.ticketStore.domain.BillingAddress;
import com.ticketStore.domain.Order;
import com.ticketStore.domain.Payment;
import com.ticketStore.domain.ShippingAddress;
import com.ticketStore.domain.ShoppingCart;
import com.ticketStore.domain.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, User user);
	
	Order findOne(Long id);

}
