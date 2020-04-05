package com.personService.ut.controller;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @DeleteMapping(value = "/delete/{id}")
    public  ResponseEntity<?>  removePerson(@PathVariable Integer id ) {
        return new ResponseEntity(personService.remove(id),HttpStatus.OK);
    }

	
	@PutMapping("/addOrUpdate")
	public ResponseEntity<?> updatePerson(@RequestBody @NotNull Person person) {
		personService.updatePerson(person);
		return new ResponseEntity<Person>(person,HttpStatus.CREATED);
	}
	
	
    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "allPerson";
    }
     
}
