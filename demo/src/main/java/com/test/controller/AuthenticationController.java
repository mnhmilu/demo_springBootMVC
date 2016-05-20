package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AuthenticationController {
	
	@RequestMapping("/")
	public String person(Model model){		
		
		return "index";	
		
	}
	@ResponseBody
	@RequestMapping("/test")
	String entry(){		
		return "My Spring Bood App(auth)";		
	}

}
