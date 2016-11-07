package com.demo.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.domain.Product;
import com.demo.services.ProductService;
import com.demo.utility.StandaloneMvcTestViewResolver;

public class ProductControllerTest {

	// http://www.codeproject.com/Articles/763928/MVC-Unit-Testing-Unleashed

	private MockMvc mockMvc;

	@InjectMocks // setups up controller, and injects mock objects into it.
	private ProductController productController;

	@Mock // Mockito Mock object
	private ProductService productService;

	@Before
	public void setup() {
		// indexController = new IndexController();

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).setViewResolvers(new StandaloneMvcTestViewResolver()).build();

	}

	@Test
	public void testList() throws Exception {
		
		List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        //specific Mockito interaction, tell stub to return list of products
        when(productService.listAllProducts()).thenReturn((List) products);
  

		mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(view().name("products"))
		.andExpect(model().attribute("products", hasSize(2)));

	}

}
