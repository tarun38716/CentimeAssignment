package com.centime.controller;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centime.client.ClientService;
import com.centime.exception.CentimeException;
import com.centime.modal.User;

@RestController
@RequestMapping(value = "/serviceone")
public class ServiceOneController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/check")
	public ResponseEntity<String> isServiceOneUp() {
		return new ResponseEntity<>("Up", OK);
	}
	
	@PostMapping("/concatenate")
	public ResponseEntity<String> concatenateResponse(@Valid @RequestBody(required = true) User user) throws CentimeException {
		String response = clientService.callServiceTwo() + " " +clientService.callServiceThree(user);
		return new ResponseEntity<>(response, OK);
	}
	
	

	
}
