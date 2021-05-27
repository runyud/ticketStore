package com.ticketStore.service;

import com.ticketStore.domain.Payment;
import com.ticketStore.domain.UserPayment;

public interface PaymentService {

	Payment setByUserPayment(UserPayment userPayment, Payment payment);

}
