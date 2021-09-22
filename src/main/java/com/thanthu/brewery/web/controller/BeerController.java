package com.thanthu.brewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanthu.brewery.services.BeerService;
import com.thanthu.brewery.web.model.BeerDto;

@RequestMapping(BeerController.API_BASE_URL)
@RestController
public class BeerController {

	public static final String API_BASE_URL = "/api/v1/beer";

	private final BeerService beerService;

	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping({ "/{beerId}" })
	public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping // POST - create new beer
	public ResponseEntity<?> handlePost(@RequestBody BeerDto beerDto) {

		BeerDto savedDto = beerService.saveNewBeer(beerDto);

		HttpHeaders headers = new HttpHeaders();
		// todo add hostname to url
		headers.add("Location", API_BASE_URL + "/" + savedDto.getId().toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

}