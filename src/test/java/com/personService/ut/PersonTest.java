package com.personService.ut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.personService.ut.domain.Person;
import com.personService.ut.repository.PersonRepository;


@WebMvcTest
@ComponentScan
class PersonTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PersonRepository personRepository ;


	@Test
	public void testCreatePersonAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/person/add/3001/ahmed/ali/323232")
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.personId").exists())
	      .andExpect(content().json("{\"personId\":3001,\"firstName\":\"ahmed\"}"));
		
		
	    Person personEntry = personRepository.getPesonById(Integer.valueOf(3001));
	    assertThat(personEntry.getFirstName()).isEqualTo("ahmed");
		
	}
	
	
	@Test
	public void testGetPersonByIdAPI() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/person/{id}", 3001)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.personId").value(3001));
	}
	


}
