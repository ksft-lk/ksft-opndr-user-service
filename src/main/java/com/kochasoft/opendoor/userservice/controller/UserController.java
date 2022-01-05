package com.kochasoft.opendoor.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.ByteString;


@RestController
@RequestMapping("/user/v1")
public class UserController {

//	@Autowired
//	UserService userService;
//	
	@Value("${firebase-app-key}")
	ByteString firebaseAppKey;
	
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";
	
//	@PostMapping("/registerUser")
//	String registerUser(User user) {
//		try {
//			userService.createUser(user);
//			return SUCCESS;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return FAILED;
//		}
//	}

    @GetMapping("/")
    String _getUser(){
        return "Hello World "+firebaseAppKey;
    }
}
