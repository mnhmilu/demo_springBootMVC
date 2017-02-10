package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(DrugController.class);

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String index(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); 

		slf4jLogger.info("ManagerController ::index:: Home page accessed by :" + name);

		return "manager/manager_home";

	}

}
