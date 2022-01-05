package com.kochasoft.opendoor.userservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import org.springframework.context.annotation.ComponentScan;

@OpenAPIDefinition
@SpringBootApplication
@ComponentScan("com.kochasoft.opendoor.userservice")
public class UserserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
}
