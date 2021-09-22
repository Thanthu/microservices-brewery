package com.thanthu.brewery.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thanthu.brewery.web.model.BeerDto;

@ExtendWith(MockitoExtension.class)
class BeerServiceImplTest {

	@InjectMocks
	private BeerServiceImpl beerService;

	@Test
	void testGetBeerById() {
		UUID beerId = UUID.randomUUID();
		BeerDto beerDto = beerService.getBeerById(beerId);
		
		assertNotNull(beerDto);
		assertEquals(beerId, beerDto.getId());
	}

}
