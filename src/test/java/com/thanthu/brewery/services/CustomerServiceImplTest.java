package com.thanthu.brewery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thanthu.brewery.web.model.CustomerDto;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
	
	private static final UUID customerId = UUID.randomUUID();
	private static final String customerName = "name";
	private CustomerDto customerDto;

	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@BeforeEach
	void setup() {
		customerDto = CustomerDto.builder()
				.id(customerId)
				.name(customerName)
				.build();
	}

	@Test
	void testGetCustomerById() {
		CustomerDto savedCustomer = customerService.getCustomerById(customerId);
		
		assertNotNull(savedCustomer);
		assertEquals(customerId, savedCustomer.getId());
	}
	
	@Test
	void testSaveNewBeer() {
		CustomerDto savedCustomer = customerService.saveNewCustomer(customerDto);
		
		assertNotNull(savedCustomer);
		assertNotNull(savedCustomer.getId());
		assertEquals(customerName, savedCustomer.getName());
	}

}
