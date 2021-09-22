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

import com.thanthu.brewery.services.BeerService;
import com.thanthu.brewery.web.model.BeerDto;

@ExtendWith(MockitoExtension.class)
class BeerControllerTest {
	
	@Mock
	private BeerService beerService;
	
	@InjectMocks
	private BeerController beerController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();
	}

	@Test
	void testGetBeer() throws Exception {
		UUID beerId = UUID.randomUUID();
		BeerDto beerDto = BeerDto.builder().id(beerId).build();
		
		when(beerService.getBeerById(beerId)).thenReturn(beerDto);
		
		mockMvc.perform(get(BeerController.API_BASE_URL + "/" + beerId.toString()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(beerId.toString()));
	}

}
