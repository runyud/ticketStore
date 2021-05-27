package com.ticketStore.service;

import com.ticketStore.domain.ShippingAddress;
import com.ticketStore.domain.UserShipping;

public interface ShippingAddressService {

	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
	
}
