package com.ticketStore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketStore.domain.CartItem;
import com.ticketStore.domain.ShoppingCart;
import com.ticketStore.domain.Ticket;
import com.ticketStore.domain.TicketToCartItem;
import com.ticketStore.domain.User;
import com.ticketStore.repository.CartItemRepository;
import com.ticketStore.repository.TicketToCartItemRepository;
import com.ticketStore.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private TicketToCartItemRepository ticketToCartItemRepository;
	
	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getTicket().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);		
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	}

	@Override
	public CartItem addTicketToCartItem(Ticket ticket, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		for(CartItem item : cartItemList) {
			if(item.getTicket().getId() == ticket.getId()) {
				item.setQty(item.getQty() + qty);
				item.setSubtotal(new BigDecimal(ticket.getOurPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(item);
				return item;
			}
		}
		
		CartItem item = new CartItem();
		item.setShoppingCart(user.getShoppingCart());
		item.setTicket(ticket);
		item.setQty(qty);
		item.setSubtotal(new BigDecimal(ticket.getOurPrice()).multiply(new BigDecimal(qty)));
		item = cartItemRepository.save(item);
		
		TicketToCartItem ticketToCartItem = new TicketToCartItem();
		ticketToCartItem.setTicket(ticket);
		ticketToCartItem.setCartItem(item);
		ticketToCartItemRepository.save(ticketToCartItem);
		
		return item;
	}

	@Override
	public CartItem findById(Long cartItemId) {
		return cartItemRepository.findOne(cartItemId);
	}

	@Override
	public void removeCartItem(CartItem cartItem) {
		ticketToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}
	

}
