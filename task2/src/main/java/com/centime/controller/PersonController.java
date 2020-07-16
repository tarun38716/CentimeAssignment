package com.centime.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.centime.entity.Person;
import com.centime.modal.Node;
import com.centime.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/people")
	public ResponseEntity<List<Node>> getPeople(){
		List<Node> nestedRelation = personService.getPeople();
		return new ResponseEntity<List<Node>>(nestedRelation, OK);
		
	}
	
	@GetMapping("/people/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Integer id){
		return new ResponseEntity<Person>(personService.getPersonByID(id), OK);
	}

}
