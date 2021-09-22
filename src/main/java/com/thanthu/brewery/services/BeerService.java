package com.thanthu.brewery.services;

import java.util.UUID;

import com.thanthu.brewery.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);

}
