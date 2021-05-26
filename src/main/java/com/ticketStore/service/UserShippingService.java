package com.ticketStore.service;

import com.ticketStore.domain.UserShipping;

public interface UserShippingService {
	UserShipping findById(Long id);
	
	void removeById(Long id);
}
