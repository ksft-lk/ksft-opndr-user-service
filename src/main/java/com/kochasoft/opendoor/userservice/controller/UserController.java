package com.kochasoft.opendoor.userservice.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.model.ResponseModel;
import com.kochasoft.opendoor.userservice.service.UserService;



@RestController
@RequestMapping("/user/v1")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/registerUser")
	ResponseEntity<ResponseModel> registerUser(@RequestBody User user) {
		try {
			long epochMilli = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			user.setCreatedAt(epochMilli);
			user.setCreatedBy("APP");
			
			userService.createUser(user);
			return ResponseEntity.ok(ResponseModel.SUCCESS());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(ResponseModel.FAILED());
		}
	}

    @GetMapping("/")
    String _getUser(){
        return "Hello World ";
    }
}
