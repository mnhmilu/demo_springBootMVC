package com.test;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan({"demo","controller","configuration","bootstrap","repositories","domain"})
//@ComponentScan({"configuration"})
public class SpringBootWebApplication {

    public static void main(String[] args) {
    	
	ApplicationContext ctx =SpringApplication.run(SpringBootWebApplication.class, args);
		
		//for(String s : ctx.getBeanDefinitionNames())
		//{
		//	System.out.println(s);
		//}
 
		System.out.println("Bean count:" +ctx.getBeanDefinitionCount());
		//https://springframework.guru/spring-boot-web-application-part-3-spring-data-jpa/
		
		
    }
}
