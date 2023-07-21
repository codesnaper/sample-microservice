package com.example.authclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class AuthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthClientApplication.class, args);
	}

}
