package com.kochasoft.opendoor.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class UserController {

    @Value("${firebase-app-key}")
    String firebaseAppKey;

    @GetMapping("/")
    String _getUser(){
        return "Hello World "+firebaseAppKey;
    }
}
