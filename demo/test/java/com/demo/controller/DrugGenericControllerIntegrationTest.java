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
	public void allTestsToVerifyFlow() throws Exception {

		MockMultipartFile fileWithContent = new MockMultipartFile("file", "orig", null, "bar".getBytes());

		MockMultipartFile emptyFile = new MockMultipartFile("file", "".getBytes());

		// Test 1: initial size should be 0 as no drug data available

		mockMvc.perform(get("/admin/drugGenericList")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("drugGeneric/drugsGenerics"))
				.andExpect(model().attributeExists("drugGenerics"))
				.andExpect(content().string(Matchers.containsString("Drug Generic List")));
				//.andExpect(model().attribute("drugGenerics", Matchers.hasProperty("totalElements", equalTo(totalCount))));
		
		
		/*

		// Test 2: new drug with content file after insert count will be 1

		this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/drug/saveDrug").file(fileWithContent)
				// post("/admin/saveAddContent")
				.param("genericId", "1").param("manufacturerId", "1").param("drugName", "testDrugName")
				.param("strength", "testStrength").param("dosageForm", "testDosageForm").param("storage", "testStorage")
				.param("packSize", "testSize").param("drugprice", "50.56")

		).andDo(print()).andExpect(status().isMovedTemporarily());

		totalCount = 1L;

		mockMvc.perform(get("/drugList")).andExpect(status().isOk()).andExpect(view().name("drugs/drugs"))
				.andExpect(model().attributeExists("drugs"))
				.andExpect(content().string(Matchers.containsString("Drug Search")))
				.andExpect(model().attribute("drugs", Matchers.hasProperty("totalElements", equalTo(totalCount))))
				.andDo(print());

		// Test 3 new drug with empty file after insert count will be 2

		this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/drug/saveDrug").file(emptyFile)
				// post("/admin/saveAddContent")
				.param("genericId", "1").param("manufacturerId", "1").param("drugName", "testDrugName2")
				.param("strength", "testStrength2").param("dosageForm", "testDosageForm2")
				.param("storage", "testStorage2").param("packSize", "testSize").param("drugprice", "50.56")

		).andDo(print()).andExpect(status().isMovedTemporarily());

		totalCount = 2L;

		mockMvc.perform(get("/drugList")).andExpect(status().isOk()).andExpect(view().name("drugs/drugs"))
				.andExpect(model().attributeExists("drugs"))
				.andExpect(content().string(Matchers.containsString("Drug Search")))
				.andExpect(model().attribute("drugs", Matchers.hasProperty("totalElements", equalTo(totalCount))))
				.andDo(print());

		// Test 4 After delete count should be 1

		mockMvc.perform(get("/drug/delete/2")).andExpect(status().isMovedTemporarily());

		totalCount = 1L;

		mockMvc.perform(get("/drugList")).andExpect(status().isOk()).andExpect(view().name("drugs/drugs"))
				.andExpect(model().attributeExists("drugs"))
				.andExpect(content().string(Matchers.containsString("Drug Search")))
				.andExpect(model().attribute("drugs", Matchers.hasProperty("totalElements", equalTo(totalCount))))
				.andDo(print());

		// Test 5 when called to show a drug info it should shown properly

		mockMvc.perform(get("/drug/1")).andExpect(status().isOk()).andExpect(view().name("drugs/drugshow"))
				.andExpect(content().string(Matchers.containsString("testDrugName")));

		// Test 5 when a item edited it should be reflected on show drug
		// information and count should remain 1

		this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/drug/saveDrug").file(fileWithContent).param("id", "1")
				.param("genericId", "1").param("manufacturerId", "1").param("drugName", "testDrugNameChanged part2")
				.param("strength", "testStrength").param("dosageForm", "testDosageForm").param("storage", "testStorage")
				.param("packSize", "testSize").param("drugprice", "50.56")

		).andDo(print()).andExpect(status().isMovedTemporarily());

		// show item
		mockMvc.perform(get("/drug/1")).andExpect(status().isOk()).andExpect(view().name("drugs/drugshow"))
				.andExpect(content().string(Matchers.containsString("testDrugNameChanged part2")));

		mockMvc.perform(get("/drugList")).andExpect(status().isOk()).andExpect(view().name("drugs/drugs"))
				.andExpect(model().attributeExists("drugs"))
				.andExpect(content().string(Matchers.containsString("Drug Search")))
				.andExpect(model().attribute("drugs", Matchers.hasProperty("totalElements", equalTo(totalCount))))
				.andDo(print());

		// Test 6 test the search functionalaities with partial brand name,
		// should return result to prove partial serch is working

		this.mockMvc.perform(post("/drugSearchFromIndex").param("drugName", "part2")

		).andDo(print()).andExpect(view().name("drugs/drugSearch"))
				.andExpect(content().string(Matchers.containsString("Search Results")))
				.andExpect(content().string(Matchers.containsString("part2"))).andExpect(status().isOk());

		// Test 7 test the pagination with number 30 items

		for (int a = 0; a < 29; a++) {

			this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/drug/saveDrug").file(emptyFile)
					// post("/admin/saveAddContent")
					.param("genericId", "1").param("manufacturerId", "1").param("drugName", "testDrugName2 " + a)
					.param("strength", "testStrength2").param("dosageForm", "testDosageForm2")
					.param("storage", "testStorage2").param("packSize", "testSize").param("drugprice", "50.56")

			).andDo(print()).andExpect(status().isMovedTemporarily());

		}
		
		
		
		mockMvc.perform(get("/drugList")).andExpect(status().isOk()).andExpect(view().name("drugs/drugs"))
		.andExpect(content().string(Matchers.containsString("testDrugName2 1")));

		
		mockMvc.perform(get("/drugList?page=1&size=20")).andExpect(status().isOk()).andExpect(view().name("drugs/drugs"))
		.andExpect(content().string(Matchers.containsString("testDrugName2 28")));

				
		*/
		

	}

}
