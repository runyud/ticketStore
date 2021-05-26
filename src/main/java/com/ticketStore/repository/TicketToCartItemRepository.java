package com.ticketStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketStore.domain.CartItem;
import com.ticketStore.domain.TicketToCartItem;

@Transactional
public interface TicketToCartItemRepository extends CrudRepository<TicketToCartItem, Long> {

	void deleteByCartItem(CartItem cartItem);

}
