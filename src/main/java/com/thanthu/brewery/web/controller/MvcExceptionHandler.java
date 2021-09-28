package com.thanthu.brewery.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {

	@ExceptionHandler({MethodArgumentNotValidException.class })
	public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException e) {
		List<String> errors = new ArrayList<>(e.getFieldErrorCount());

		e.getFieldErrors().forEach(constraintViolation -> {
			errors.add(constraintViolation.getField()+ " : " + constraintViolation.getDefaultMessage());
		});

		return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
	}

}