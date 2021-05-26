package com.ticketStore.service;

import com.ticketStore.domain.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);

	void removeById(Long creditCardId);
}
