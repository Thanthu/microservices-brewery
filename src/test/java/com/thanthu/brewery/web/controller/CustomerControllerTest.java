package com.thanthu.brewery.web.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.thanthu.brewery.services.CustomerService;
import com.thanthu.brewery.web.model.CustomerDto;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
	
	private static final UUID customerId = UUID.randomUUID();
	private static final String customerName = "name";
	private CustomerDto customerDto;
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		customerDto = CustomerDto.builder()
				.id(customerId)
				.name(customerName)
				.build();
		
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	void testGetCustomer() throws Exception {
		
		when(customerService.getCustomerById(customerId)).thenReturn(customerDto);
		
		mockMvc.perform(get(CustomerController.API_BASE_URL + "/" + customerId.toString()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(customerId.toString()));
	}
	
	@Test
	void testHandlePost() throws Exception {
		
		when(customerService.saveNewCustomer(any(CustomerDto.class))).thenReturn(customerDto);
		
		mockMvc.perform(post(CustomerController.API_BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(customerDto)))
		.andExpect(status().isCreated())
		.andExpect(header().string("Location", containsString(CustomerController.API_BASE_URL + "/")));
		
	}
	
	@Test
	void testHandleUpdate() throws Exception {
		
		mockMvc.perform(put(CustomerController.API_BASE_URL + "/" + customerId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(customerDto)))
		.andExpect(status().isNoContent());
		
	}
	
	@Test
	void testDeleteById() throws Exception {
		
		mockMvc.perform(delete(CustomerController.API_BASE_URL + "/" + customerId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(customerDto)))
		.andExpect(status().isNoContent());
		
	}

}
