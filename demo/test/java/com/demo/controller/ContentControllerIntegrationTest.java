package com.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.demo.configuration.TestConfiguration;
import com.demo.domain.Content;
import com.demo.utility.StandaloneMvcTestViewResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class,StandaloneMvcTestViewResolver.class})
@WebAppConfiguration

public class ContentControllerIntegrationTest {
	
	
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	

	
	@Before
	public void setup()
	{
	
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();		
	}	
	
	@Test
	public void newAddvertisementTest() throws Exception
	{
		MockMultipartFile fileWithContent = new MockMultipartFile("file", "orig", null, "bar".getBytes());
		
		MockMultipartFile emptyFile =new MockMultipartFile("file","".getBytes());
		
		
		//advertisement with empty file
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.fileUpload("/admin/saveAddContent")
				.file(emptyFile)
				//post("/admin/saveAddContent")
				.param("header", "sampleHeader")
				.param("content_summary", "samplesummary")
				.param("contentType", "add")
				.param("content_details", "sampleContentDetails")
				.param("add_section", "add1")
				.param("contentPage", "index")				
				.param("insertDate","11/21/2016")	
				.param("expireDate","11/21/2016")	
				
				)
				.andExpect(status().isMovedTemporarily()).andDo(print());		
		
		
		//advertisement with content file
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.fileUpload("/admin/saveAddContent")
				.file(fileWithContent)
				//post("/admin/saveAddContent")
				.param("header", "sampleHeader")
				.param("content_summary", "samplesummary")
				.param("contentType", "add")
				.param("content_details", "sampleContentDetails")
				.param("add_section", "add1")
				.param("contentPage", "index")				
				.param("insertDate","11/21/2016")	
				.param("expireDate","11/21/2016")	
				
				)
				.andExpect(status().isMovedTemporarily()).andDo(print());		
		
		
		
		
	}
	
	@Test
	public void advertisementIndexTest() throws Exception
	{		

		mockMvc.perform(get("/admin/addList")).
		andExpect(status().isOk()).
		andExpect(view().name("contents/addcontents")).
		andExpect(model().attributeExists("contents")).
		andExpect(content().string(Matchers.containsString("Advertisement List"))).
		andExpect(model().attribute("contents", Matchers.hasProperty("totalElements", equalTo(0L)))).
		andDo(print());  
	    
		
	}
	
	
    	
	
	
	

	
	
	
	
	

}
