package com.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.hasSize;
import com.demo.configuration.TestConfiguration;
import com.demo.configuration.StandaloneMvcTestViewResolver;

/*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class,StandaloneMvcTestViewResolver.class})
@WebAppConfiguration

public class PatientControllerIntegrationTest {

	@Autowired
    private WebApplicationContext wac;   
	
 
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }	

	
	@Test
	public void testNewPatient() throws Exception
	{	
		// check before new patient entry
		this.mockMvc.perform(get("/patients")).andExpect(status().isOk()).andExpect(view().name("patients"))
		.andExpect(model().attribute("patients", hasSize(2)));		
		
		
		// enter new patient
		
		this.mockMvc.perform(post("/patient")
		.param("name", "tipu")
		.param("mobile", "01733400888")
		.param("age", "45")
		.param("serialDate","11/21/2016"))
		.andExpect(status().isMovedTemporarily());
		
		
		//check count after successful entry of a new patient
		
		this.mockMvc.perform(get("/patients")).andExpect(status().isOk()).andExpect(view().name("patients"))
		.andExpect(model().attribute("patients", hasSize(3)));		
	
		
	}	

	
	

}
