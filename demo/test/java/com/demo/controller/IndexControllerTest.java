package com.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IndexControllerTest {
	
	
	//http://www.codeproject.com/Articles/763928/MVC-Unit-Testing-Unleashed
	
	
	private MockMvc mockMvc;

	private IndexController indexController;
	
	
	
	@Before
	public void setup()
	{
		indexController = new IndexController();
		mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();	
		
	}
	
	@Test
	public void testIndex() throws Exception{				
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).
		andExpect(view().name("index"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
