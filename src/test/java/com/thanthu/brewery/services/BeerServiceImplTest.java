package com.thanthu.brewery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thanthu.brewery.web.model.BeerDto;

@ExtendWith(MockitoExtension.class)
class BeerServiceImplTest {
	
	private static final UUID beerId = UUID.randomUUID();
	private static final String beerName = "beerName";
	private static final String beerStyle = "beerStyle";
	private BeerDto beerDto;

	@InjectMocks
	private BeerServiceImpl beerService;
	
	@BeforeEach
	void setup() {
		beerDto = BeerDto.builder()
				.id(beerId)
				.beerName(beerName)
				.beerStyle(beerStyle)
				.build();
	}

	@Test
	void testGetBeerById() {
		BeerDto savedBeer = beerService.getBeerById(beerId);

		assertNotNull(savedBeer);
		assertEquals(beerId, savedBeer.getId());
	}

	@Test
	void testSaveNewBeer() {
		BeerDto savedBeer = beerService.saveNewBeer(beerDto);
		
		assertNotNull(savedBeer);
		assertNotNull(savedBeer.getId());
		assertEquals(beerName, savedBeer.getBeerName());
		assertEquals(beerStyle, savedBeer.getBeerStyle());
	}

}
