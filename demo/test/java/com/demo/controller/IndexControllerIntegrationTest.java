package com.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.demo.configuration.TestConfiguration;
import com.demo.utility.StandaloneMvcTestViewResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class, StandaloneMvcTestViewResolver.class })

@WebAppConfiguration
@Sql({ "test.sql" })
@ActiveProfiles("dev")

public class IndexControllerIntegrationTest {
	
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	Long totalCount = 0L;	
	
	
	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
	}
	

	
	@Test
	public void allTestsToVerifyIndexFlow() throws Exception {
		
		
		
		MockMultipartFile fileWithContent = new MockMultipartFile("file", "orig", null, "bar".getBytes());

		MockMultipartFile emptyFile = new MockMultipartFile("file", "".getBytes());

		// Test 1: Index should be shown properly

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(model().attributeExists("newsList"))
				.andExpect(content().string(Matchers.containsString("Important Addresses")));	
			
	
	  // Test 2 : Index search should be shown properly 
		
		for (int a = 0; a < 29; a++) {

			this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/drug/saveDrug").file(emptyFile)
					// post("/admin/saveAddContent")
					.param("genericId", "1").param("manufacturerId", "1").param("drugName", "testDrugName2 " + a)
					.param("strength", "testStrength2").param("dosageForm", "testDosageForm2")
					.param("storage", "testStorage2").param("packSize", "testSize").param("drugprice", "50.56")

			).andDo(print()).andExpect(status().isMovedTemporarily());

		}
		
		
		this.mockMvc.perform(get("/drugSearchFromIndex?page=0&size=10")
				.param("drugName", "test"))
    	        .andExpect(content().string(Matchers.containsString("test")))
		        .andDo(print()).andExpect(status().isOk())
		        .andExpect(view().name("drugs/drugSearch"));
		
		
		
		Map<String, Object> sessionattrs = new HashMap<>();
		sessionattrs.put("searhKeyByDurgName", "test");

		
		
		this.mockMvc.perform(get("/drugSearchFromIndex?page=1&size=10").sessionAttrs(sessionattrs))
		        .andDo(print()).andExpect(status().isOk())
		    	.andExpect(content().string(Matchers.containsString("test")))
		        .andExpect(view().name("drugs/drugSearch"));
		
		
	
	}
	
	
	
	
	
	
	
	
	
	

}
