package com.centime.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.centime.entity.Person;

@Repository
public interface PersonDao extends CrudRepository<Person, Integer>{
	
	

}
