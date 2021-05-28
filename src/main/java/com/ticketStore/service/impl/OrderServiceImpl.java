package com.ticketStore.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketStore.domain.BillingAddress;
import com.ticketStore.domain.CartItem;
import com.ticketStore.domain.Order;
import com.ticketStore.domain.Payment;
import com.ticketStore.domain.ShippingAddress;
import com.ticketStore.domain.ShoppingCart;
import com.ticketStore.domain.Ticket;
import com.ticketStore.domain.User;
import com.ticketStore.repository.OrderRepository;
import com.ticketStore.service.CartItemService;
import com.ticketStore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Override
	public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, User user) {
		// Make this method synchronized since there might be concurrent operations
		// of checking out and placing orders
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Ticket ticket = cartItem.getTicket();
			cartItem.setOrder(order);
			ticket.setInStockNumber(ticket.getInStockNumber() - cartItem.getQty());
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}

	@Override
	public Order findOne(Long id) {
		return orderRepository.findOne(id);
	}

}
