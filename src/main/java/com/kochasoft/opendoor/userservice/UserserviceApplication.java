package com.kochasoft.opendoor.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
@SpringBootApplication
@ComponentScan("com.kochasoft.opendoor.userservice")
public class UserserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
}
