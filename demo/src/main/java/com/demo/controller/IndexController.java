package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "index";
		
	}
	
//	@RequestMapping(value = "/logout", method = RequestMethod.POST)
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "index";
//		
//	}
	
	

}
