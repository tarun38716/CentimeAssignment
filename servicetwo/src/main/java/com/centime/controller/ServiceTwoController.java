package com.centime.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ServiceTwoController {
	
	@GetMapping("/hello")
	public ResponseEntity<String> getHello(){
		return new ResponseEntity<>("Hello", OK);
	}

}
