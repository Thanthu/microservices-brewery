package com.thanthu.brewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.thanthu.brewery.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		return CustomerDto.builder()
				.id(customerId)
				.name("Joe Buck")
				.build();
	}

}
