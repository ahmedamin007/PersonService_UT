package com.personService.ut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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
		mockMvc.perform(MockMvcRequestBuilders.post("/person/add/1/ahmed/ali/323232")
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.personId").exists())
	      .andExpect(content().json("{\"personId\":1,\"firstName\":\"ahmed\"}"));
		
	    Person personEntry = personRepository.getPesonById(Integer.valueOf(1));
	    assertThat(personEntry.getFirstName()).isEqualTo("ahmed");
		
	}

    @Test
    public void testCreateOrUpdate () throws Exception {

        
        Person person= new Person(null, "Ahmed", "Ali", "323232");

        MockHttpServletRequestBuilder builder =
                   MockMvcRequestBuilders.put("/person/addOrUpdate")
                                         .contentType(MediaType.APPLICATION_JSON)
                                         .content(asJsonString(person))
                                         .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status().isCreated())
	          	    .andExpect(MockMvcResultMatchers.jsonPath("$.personId").exists())
	        	    .andExpect(content().json("{\"personId\":2,\"firstName\":\"Ahmed\",\"lastName\":\"Ali\"}"));
    }
	
    public  String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	@Test
	public void testGetPersonByIdAPI() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/person/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.personId").value(1));
	}
	


}
