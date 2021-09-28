package com.thanthu.brewery.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.thanthu.brewery.services.CustomerService;
import com.thanthu.brewery.web.model.CustomerDto;

@RequestMapping(CustomerController.API_BASE_URL)
@RestController
public class CustomerController {

	public static final String API_BASE_URL = "/api/v1/customer";

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> handlePost(@RequestBody @Validated CustomerDto customerDto) {
		CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", API_BASE_URL + "/" + savedDto.getId().toString());

		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleUpdate(@PathVariable UUID customerId, @RequestBody @Validated CustomerDto customerDto) {
		customerService.updateCustomer(customerId, customerDto);
	}

	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable UUID customerId) {
		customerService.deleteById(customerId);
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
