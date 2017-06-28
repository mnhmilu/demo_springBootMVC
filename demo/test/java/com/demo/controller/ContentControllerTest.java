package com.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ContentControllerTest {
	
	
	private MockMvc mockMvc;
	
	private ContentController contentController;
	
	
	@Before
	public void setup()
	{
		contentController = new ContentController();
		mockMvc = MockMvcBuilders.standaloneSetup(contentController).build();	
		
	}
	
	
	@Test
	public void sampleTest() throws Exception
	{
		
		mockMvc.perform(get("/admin/newsList")).
		andExpect(status().isOk()).
		andExpect(view().name("index"));
		
	}
	
	
	

}
