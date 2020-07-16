package com.centime.controller;

import static org.springframework.http.HttpStatus.OK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centime.modal.User;

@RestController
@RequestMapping(value = "/")
public class ServiceThreeController {
	
	private final Logger logger = LoggerFactory.getLogger(ServiceThreeController.class);
	
	@PostMapping("/concatenate")
	public ResponseEntity<String> concatenate(@RequestBody(required = true) final User user) {
		
		logger.debug("Request Json : [{}]",user.toString());
		
		String concatenatedString = user.getName() + " " + user.getSurName();
		return new ResponseEntity<>(concatenatedString, OK);

	}

}
