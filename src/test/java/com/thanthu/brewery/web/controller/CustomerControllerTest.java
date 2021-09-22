package com.thanthu.brewery.web.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.thanthu.brewery.services.CustomerService;
import com.thanthu.brewery.web.model.CustomerDto;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	void testGetCustomer() throws Exception {
		UUID customerId = UUID.randomUUID();
		CustomerDto customerDto = CustomerDto.builder().id(customerId).build();
		
		when(customerService.getCustomerById(customerId)).thenReturn(customerDto);
		
		mockMvc.perform(get(CustomerController.API_BASE_URL + "/" + customerId.toString()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(customerId.toString()));
	}

}
