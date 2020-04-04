package com.personService.ut.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.personService.ut.domain.Person;

@Component
public class PersonRepository {

	private List<Person> personList;
	
	
	public PersonRepository() {
		personList = new ArrayList<Person>();
	}
	
	public void save(Person person) {
		personList.add(person);
	}
	
	public List<Person> getAllPersons(){
		return personList;
	}
	
	public Person getPesonById(Integer id) {
		
		return personList.stream().filter(e-> e.getPersonId().equals(id))
		.collect(Collectors.toMap(Person::getPersonId, Function.identity()))
		.get(id);
		
	}
	
}
