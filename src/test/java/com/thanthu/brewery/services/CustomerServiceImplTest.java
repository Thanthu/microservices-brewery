package com.thanthu.brewery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thanthu.brewery.web.model.CustomerDto;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
	
	@InjectMocks
	private CustomerServiceImpl customerService;

	@Test
	void testGetCustomerById() {
		UUID customerId = UUID.randomUUID();
		CustomerDto customerDto = customerService.getCustomerById(customerId);
		
		assertNotNull(customerDto);
		assertEquals(customerId, customerDto.getId());
	}

}
