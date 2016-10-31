package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerController {	
	
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "manager/manager_home";
		
	}

}
