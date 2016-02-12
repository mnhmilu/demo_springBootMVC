package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

public class SpringBootWebApplication {

	public static void main(String[] args) {

		// https://springframework.guru/spring-boot-web-application-part-3-spring-data-jpa/

		ApplicationContext ctx = SpringApplication.run(SpringBootWebApplication.class, args);

	}
}
