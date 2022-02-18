package com.kochasoft.opendoor.userservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kochasoft.opendoor.userservice.controller.UserController;
import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.repository.UserRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest{

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserRepository userRepository;

    @Test
    public void createUser(){
        try {
			User mockUser = new User();
			mockUser.setName("kasun");
			Mockito.when(userRepository.save(mockUser).block()).thenReturn(mockUser);
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/v1/registerUser")
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
					.content(this.mapper.writeValueAsString(mockUser));
			
			mvc.perform(mockRequest).andExpect(status().isOk())
					.andExpect(jsonPath("$.name", is("kasun")));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }



}