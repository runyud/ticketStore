package com.ticketStore.service;

import java.util.List;

import com.ticketStore.domain.CartItem;
import com.ticketStore.domain.Order;
import com.ticketStore.domain.ShoppingCart;
import com.ticketStore.domain.Ticket;
import com.ticketStore.domain.User;

public interface CartItemService {

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	CartItem updateCartItem(CartItem cartItem);

	CartItem addTicketToCartItem(Ticket ticket, User user, int parseInt);

	CartItem findById(Long cartItemId);

	void removeCartItem(CartItem findById);

	void save(CartItem item);

	List<CartItem> findByOrder(Order order);

}
