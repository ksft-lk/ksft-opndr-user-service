package com.kochasoft.opendoor.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    String _getUser(){
        return "Hello World";
    }
}
