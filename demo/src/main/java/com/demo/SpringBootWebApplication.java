package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.demo.utility.fileUploader.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootWebApplication {

	public static void main(String[] args) {

		// https://springframework.guru/spring-boot-web-application-part-3-spring-data-jpa/

		ApplicationContext ctx = SpringApplication.run(SpringBootWebApplication.class, args);

	}
}
