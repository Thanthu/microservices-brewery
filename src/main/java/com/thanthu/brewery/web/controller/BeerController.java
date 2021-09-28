package com.thanthu.brewery.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public ResponseEntity<?> handlePost(@RequestBody @Valid BeerDto beerDto) {

		BeerDto savedDto = beerService.saveNewBeer(beerDto);

		HttpHeaders headers = new HttpHeaders();
		// TODO add hostname to url
		headers.add("Location", API_BASE_URL + "/" + savedDto.getId());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping({ "/{beerId}" })
	public ResponseEntity<?> handleUpdate(@PathVariable UUID beerId, @RequestBody @Valid BeerDto beerDto) {

		beerService.updateBeer(beerId, beerDto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping({ "/{beerId}" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable UUID beerId) {
		beerService.deleteById(beerId);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class })
	public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException e) {
		List<String> errors = new ArrayList<>(e.getFieldErrorCount());

		e.getFieldErrors().forEach(constraintViolation -> {
			errors.add(constraintViolation.getField()+ " : " + constraintViolation.getDefaultMessage());
		});

		return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
	}

}
