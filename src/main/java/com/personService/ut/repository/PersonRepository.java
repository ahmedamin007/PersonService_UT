package com.personService.ut.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
	
	public void saveOrUpdate(Person person) {
		if (person.getPersonId()==null) {
			person.setPersonId(personList.size() + 1);
			personList.add(person);
		}
		else {
			int index=-1;
			for (int i=0 ; i< personList.size() ; i++ ) {
				if (personList.get(i).getPersonId().equals(person.getPersonId())) {
					index=i;
					break;
				}
			}
			if (index > -1)
				personList.set(index, person);	
		}
		
	}
	
	public boolean delete(Integer id) {
		if (id != null) {
			int index=-1;
			for (int i=0 ; i< personList.size() ; i++ ) {
				if (personList.get(i).getPersonId().equals(id)) {
					index=i;
					break;
				}
			}
			if (index > -1) {
				personList.remove(index);	
				return true;
			}
			
		}
		return false;
	}
	public List<Person> getAllPersons(){
		return personList;
	}
	
	public Person  getPesonById(Integer id) {
		return  personList.stream().filter(e-> e.getPersonId().equals(id))
		.collect(Collectors.toMap(Person::getPersonId, Function.identity()))
		.get(id);
		
	}
	
}
