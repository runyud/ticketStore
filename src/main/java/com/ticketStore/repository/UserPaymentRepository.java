package com.ticketStore.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticketStore.domain.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long>{

}
