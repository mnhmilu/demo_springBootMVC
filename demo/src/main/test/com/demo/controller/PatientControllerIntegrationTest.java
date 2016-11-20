package com.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.demo.configuration.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfiguration.class)
@WebAppConfiguration

public class PatientControllerIntegrationTest {

	@Autowired
    private WebApplicationContext wac;
	
   
	
 
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	//this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).setViewResolvers(new StandaloneMvcTestViewResolver()).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	
	@Test
	//@Sql({"/test-schema.sql", "/test-user-data.sql"})
	public void testList() throws Exception
	{
		
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
		
	}

}
