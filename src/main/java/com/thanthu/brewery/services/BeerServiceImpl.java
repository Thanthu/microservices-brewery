package com.thanthu.brewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.thanthu.brewery.web.model.BeerDto;

@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerById(UUID beerId) {
		return BeerDto.builder()
				.id(beerId)
				.beerName("Galaxy Cat")
				.beerStyle("Pale Ale")
				.build();
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName(beerDto.getBeerName())
				.beerStyle(beerDto.getBeerStyle())
				.build();
	}

}
