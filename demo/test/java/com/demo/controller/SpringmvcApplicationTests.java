package com.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.demo.SpringBootWebApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootWebApplication.class)
@WebAppConfiguration
public class SpringmvcApplicationTests {

	@Test
	public void contextLoads() {
	}

}
