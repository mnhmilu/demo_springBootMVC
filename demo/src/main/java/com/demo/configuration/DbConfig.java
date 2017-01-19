package com.demo.configuration;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

	@Bean
	public Connection getConnection() throws URISyntaxException, SQLException {
		String urlDB = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(urlDB);
	}

}
