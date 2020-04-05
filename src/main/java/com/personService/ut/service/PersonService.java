package com.personService.ut.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personService.ut.domain.Person;
import com.personService.ut.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	public void addPerson(Person person) {
		personRepository.saveOrUpdate(person);
	}
	
	public void updatePerson(Person person) {
		personRepository.saveOrUpdate(person);
	}
	
	
	public List<Person> getAllPersons(){
		return personRepository.getAllPersons();
	}
	
	public boolean remove(Integer id) {
		return personRepository.delete(id);
	}
	
	public Person  getPesonById(Integer id) {
		return personRepository.getPesonById(id);
	}
}
