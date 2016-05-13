package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.model.Person;


@Controller
public class PersonController {
	
	@RequestMapping("/person")
	public String person(Model model){
		
		Person p = new Person();
		p.setFirstName("Nahid");
		p.setLastName("Hossain");
		
		model.addAttribute("person",p);
		return "personview";		
	}	

}
