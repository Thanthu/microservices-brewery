package com.thanthu.brewery.services;

import java.util.UUID;

import com.thanthu.brewery.web.model.CustomerDto;

public interface CustomerService {
	
	CustomerDto getCustomerById(UUID customerId);

}
