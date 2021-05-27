package com.ticketStore.service;

import com.ticketStore.domain.BillingAddress;
import com.ticketStore.domain.UserBilling;

public interface BillingAddressService {

	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);

}
