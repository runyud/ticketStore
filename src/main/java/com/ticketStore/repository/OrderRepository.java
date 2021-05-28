package com.ticketStore.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticketStore.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
