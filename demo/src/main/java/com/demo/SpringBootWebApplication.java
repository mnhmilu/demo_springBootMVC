package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import com.demo.utility.fileUploader.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootWebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		// https://springframework.guru/spring-boot-web-application-part-3-spring-data-jpa/

		//String test=System.getProperty("user.dir") ;
		ApplicationContext ctx = SpringApplication.run(SpringBootWebApplication.class, args);

	}
	
	
	
	
	// when need to package as war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringBootWebApplication.class);
	}


/*


	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
            //storageService.deleteAll();
            //storageService.init();
		};
	}
	*/
}
