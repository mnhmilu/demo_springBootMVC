package com.demo.configuration;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DbConfig {

	//@Bean
	public Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
		String urlDB = System.getenv("JDBC_DATABASE_URL");
		Class.forName("org.postgresql.Driver");	
		urlDB="jdbc:postgres://ec2-54-235-240-92.compute-1.amazonaws.com:5432/d3qgc1dvqq0ilh";
		System.out.println("nahid:"+ urlDB);
		return DriverManager.getConnection(urlDB);
	}

}
