package com.demo.configuration;

import org.springframework.context.annotation.Bean;

import com.test.services.PatientProfiletService;
import com.test.services.PatientServiceImpl;




public class ProductServiceTestConfig {	
	
	
	@Bean
	public PatientProfiletService patientProfiletService()
	{
		
		return new PatientServiceImpl();
		
	}	
	
	
	
	

}
