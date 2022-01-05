package com.kochasoft.opendoor.userservice.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/user/v1")
public class UserController {

	@Autowired
	UserService userService;
	
	@Value("${firebase-app-key}")
    String firebaseAppKey;
	
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";
	
	@PostMapping("/registerUser")
	String registerUser(@RequestBody User user) {
		try {
			user.setCreatedAt(LocalDateTime.now());
			user.setCreatedBy("APP");
			System.out.println("user in the request : "+user.toString());
			userService.createUser(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILED;
		}
	}

    @GetMapping("/")
    String _getUser(){
        return "Hello World "+firebaseAppKey;
    }
}
