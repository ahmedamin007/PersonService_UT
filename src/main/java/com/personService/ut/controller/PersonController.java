package com.personService.ut.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.personService.ut.domain.Person;
import com.personService.ut.service.PersonService;


@Controller
@RequestMapping("/person")

public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPerson(  @PathVariable Integer id){
		Person person = personService.getPesonById(id);
		return new ResponseEntity<Person>(person,HttpStatus.OK);
	}
	
	
    @GetMapping(value = "/allPerson")
    public  ResponseEntity<List<Person>>  getAllPerson() {
        return new ResponseEntity(personService.getAllPersons(),HttpStatus.OK);
    }

	@PostMapping("/add/{id}/{firstName}/{lastName}/{phone}")
	public ResponseEntity<?> addPerson(@PathVariable Integer id ,@PathVariable String firstName ,
									   @PathVariable String lastName,@PathVariable String phone){
		Person person = new Person();
		person.setPersonId(id);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhone(phone);
		personService.addPerson(person);
		return new ResponseEntity<Person>(person,HttpStatus.CREATED);
	}
	
	
    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "allPerson";
    }
    
    
    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        
        return "createPerson";
   
    }

    @PostMapping(value = "/save")
    public String saveBooks(@ModelAttribute Person form, Model model) {
    	personService.addPerson(form);

        model.addAttribute("persons", personService.getAllPersons());

        return "redirect:/person/all";
    }
	

	    
	    



    
}
