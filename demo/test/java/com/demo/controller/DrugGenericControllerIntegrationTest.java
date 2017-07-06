package com.demo.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import static org.hamcrest.Matchers.hasSize;

import com.demo.configuration.TestConfiguration;
import com.demo.utility.StandaloneMvcTestViewResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class, StandaloneMvcTestViewResolver.class })

@WebAppConfiguration
@Sql({ "test.sql" })
@ActiveProfiles("dev")
public class DrugGenericControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	Long totalCount = 2L;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
	}

	@Test
	public void allTestsToVerifyDrugGenericFlow() throws Exception {

		MockMultipartFile fileWithContent = new MockMultipartFile("file", "orig", null, "bar".getBytes());

		MockMultipartFile emptyFile = new MockMultipartFile("file", "".getBytes());

		// Test 1: initial size should be 2 as drug generic data available with bootstrap data loader

		mockMvc.perform(get("/admin/drugGenericList")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugsGenerics"))
				.andExpect(model().attributeExists("drugGenerics"))
				.andExpect(content().string(Matchers.containsString("Drug Generic List")))			
				.andExpect(model().attribute("drugGenerics", hasSize(2)));    // ????????
				//.andExpect(model().attribute("drugGenerics", Matchers.hasProperty("totalElements", equalTo(totalCount))));  //TODO: find why it is not working
		


		// Test 2: new generic drug , after insert count will be 3

		this.mockMvc.perform(
				 post("/admin/generics")
				.param("genericName", "genericNameTest")
				.param("classification", "classificationTest")
				.param("safetyRemarks", "safetyRemarksTest")
				.param("indicationDosages", "indicationDosagesTest")
				.param("contraindication", "contraindicationTest")
				.param("advanceDrugReaction", "advanceDrugReactionTest")
				.param("interAction", "interActionTest")
				.param("specialPrecaution", "specialPrecautionTest")

		).andDo(print()).andExpect(status().isMovedTemporarily());

		totalCount = 3L;

		mockMvc.perform(get("/admin/drugGenericList")).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugsGenerics"))
				.andExpect(model().attributeExists("drugGenerics"))
				.andExpect(content().string(Matchers.containsString("Drug Generic List")))
				.andExpect(model().attribute("drugGenerics", hasSize(3)))
				.andDo(print());
		
		
		
		// Test 3 when called to show a drug generic info it should shown properly

				mockMvc.perform(get("/drugGenericDetails/3")).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugGenericDetails"))
						.andExpect(content().string(Matchers.containsString("genericNameTest")));

	    // Test 4 when a item edited it should be reflected on show drug  information and count should remain 3

				this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/admin/generics").file(fileWithContent).param("idGeneric", "3")
						.param("genericName", "genericNameChanged")

				).andDo(print()).andExpect(status().isMovedTemporarily());

				// show item
				mockMvc.perform(get("/drugGenericDetails/3")).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugGenericDetails"))
						.andExpect(content().string(Matchers.containsString("genericNameChanged")));

				
				 // count that no new entry added after edit mistakenly 
				mockMvc.perform(get("/admin/drugGenericList")).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugsGenerics"))
				.andExpect(model().attributeExists("drugGenerics"))
				.andExpect(content().string(Matchers.containsString("Drug Generic List")))
				.andExpect(model().attribute("drugGenerics", hasSize(3)))
				.andDo(print());
			


		// Test 4 After delete count should be 1

		mockMvc.perform(get("/admin/generic/delete/3")).andExpect(status().isMovedTemporarily());
	

		mockMvc.perform(get("/admin/drugGenericList")).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugsGenerics"))
		.andExpect(model().attributeExists("drugGenerics"))
		.andExpect(content().string(Matchers.containsString("Drug Generic List")))
		.andExpect(model().attribute("drugGenerics", hasSize(2)))
		.andDo(print());
		
		
		// Test 5 test the pagination with number 30 items

				for (int a = 0; a < 29; a++) {

					this.mockMvc.perform(
							 post("/admin/generics")
							.param("genericName", "genericNameTest "+a)
							.param("classification", "classificationTest")
							.param("safetyRemarks", "safetyRemarksTest")
							

					).andDo(print()).andExpect(status().isMovedTemporarily());

				}
				
				
				
				mockMvc.perform(get("/admin/drugGenericList")).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugsGenerics"))
				.andExpect(content().string(Matchers.containsString("genericNameTest 1")));

				
				mockMvc.perform(get("/admin/drugGenericList?page=1&size=20")).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugsGenerics"))
				.andExpect(content().string(Matchers.containsString("genericNameTest 28")));

		

		// Test 6 test the search functionalaities with partial brand name,
		// should return result to prove partial search is working

		this.mockMvc.perform(post("/admin/drugGenericSearch").param("genericName", "genericNameTest 28"))
		.andDo(print()).andExpect(view().name("drugGeneric/drugsGenerics"))
				.andExpect(content().string(Matchers.containsString("Drug Generic Search")))
				.andExpect(content().string(Matchers.containsString("28"))).andExpect(status().isOk());
		
		

	}

}
