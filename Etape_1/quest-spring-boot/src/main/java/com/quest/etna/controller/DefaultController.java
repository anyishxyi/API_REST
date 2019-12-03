package com.quest.etna.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
	
	//testSuccess() qui répond sur /testSuccess un code HTTP 200 et un body "success"
	//testNotFound() qui répond sur /testNotFound un code HTTP 404 et un body "not found"
	//testError() qui répond sur /testError un code HTTP 500 et un body "error"
	
	@GetMapping(value="/testSuccess")
	@ResponseStatus(HttpStatus.OK)
	public String testSuccess() {
		return "success";
	}

	@GetMapping(value="/testNotFound")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String testNotFound() {
		return "not found";
	}

	@GetMapping(value="/testError")
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String testError() {
		return "error";
	}

}
