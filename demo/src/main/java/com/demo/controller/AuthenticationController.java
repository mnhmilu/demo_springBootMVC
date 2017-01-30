package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.domain.PatientProfile;


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
	

	
	
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	    //You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}


}
