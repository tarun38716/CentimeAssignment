package com.centime.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.centime.config.LogMethodParam;
import com.centime.dao.PersonDao;
import com.centime.entity.Person;
import com.centime.modal.Node;

@Service
public class PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	public List<Node> getPeople(){
		return this.nestedData((List<Person>) personDao.findAll());
	}
	
	@LogMethodParam
	public Person getPersonByID(Integer id) {
		Optional<Person> p = personDao.findById(id);
		if(p.isPresent())
			return p.get();
		return null;
	}
	
	private List<Node> nestedData(List<Person> people) {
		
		Map<Integer, Node> lookUpMap = new HashMap<>();
		
		people.forEach(p -> lookUpMap.put(p.getId(), new Node(p.getName(), null, null)));
		
		people.forEach(p -> {
			Node parentNode = lookUpMap.get(p.getParentId());
			Node childNode = lookUpMap.get(p.getId());
			if(null != parentNode) {
				childNode.setParent(parentNode);
				if(CollectionUtils.isEmpty(parentNode.getChilds())) 
					parentNode.setChilds(new ArrayList<>());
				parentNode.getChilds().add(childNode);
			} 
		});
		
		
		return lookUpMap.entrySet() 
        .stream()
        .filter(e -> (e.getValue().getParent()==null && e.getValue().getChilds()!=null)) 
        .map(Entry::getValue)
        .collect(Collectors.toCollection(ArrayList::new));
		
	}

}
