package com.ticketStore.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticketStore.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
