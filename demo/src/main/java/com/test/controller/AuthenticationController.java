package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.domain.PatientProfile;


@Controller
public class AuthenticationController {
//	
//	@RequestMapping("/")
//	public String index(Model model){		
//		
//		return "index";	
//		
//	}	
	
	
	@ResponseBody
	@RequestMapping("/test")
	String entry(){		
		return "My Spring Bood App(auth)";		
	}

}
