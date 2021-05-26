package com.ticketStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketStore.domain.UserPayment;
import com.ticketStore.repository.UserPaymentRepository;
import com.ticketStore.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

	@Autowired
	UserPaymentRepository userPaymentRepository;
	
	@Override
	public UserPayment findById(Long id) {
		return userPaymentRepository.findOne(id);
	}

}
