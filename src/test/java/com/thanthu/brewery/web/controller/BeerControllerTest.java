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

import com.thanthu.brewery.services.BeerService;
import com.thanthu.brewery.web.model.BeerDto;

@ExtendWith(MockitoExtension.class)
class BeerControllerTest {
	
	private static final UUID beerId = UUID.randomUUID();
	private static final String beerName = "beerName";
	private static final String beerStyle = "beerStyle";
	private BeerDto beerDto;
	
	@Mock
	private BeerService beerService;
	
	@InjectMocks
	private BeerController beerController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		beerDto = BeerDto.builder()
				.id(beerId)
				.beerName(beerName)
				.beerStyle(beerStyle)
				.build();
		
		mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();
	}

	@Test
	void testGetBeer() throws Exception {
		
		when(beerService.getBeerById(beerId)).thenReturn(beerDto);
		
		mockMvc.perform(get(BeerController.API_BASE_URL + "/" + beerId.toString()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(beerId.toString()));
	}
	
	@Test
	void testHandlePost() throws Exception {
		
		when(beerService.saveNewBeer(any(BeerDto.class))).thenReturn(beerDto);
		
		mockMvc.perform(post(BeerController.API_BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(beerDto)))
		.andExpect(status().isCreated())
		.andExpect(header().string("Location", containsString(BeerController.API_BASE_URL + "/")));
		
	}
	
	@Test
	void testHandleUpdate() throws Exception {
		
		mockMvc.perform(put(BeerController.API_BASE_URL + "/" + beerId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(beerDto)))
		.andExpect(status().isNoContent());
		
	}
	
	@Test
	void testDeleteBeer() throws Exception {
		
		mockMvc.perform(delete(BeerController.API_BASE_URL + "/" + beerId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(beerDto)))
		.andExpect(status().isNoContent());
		
	}

}
