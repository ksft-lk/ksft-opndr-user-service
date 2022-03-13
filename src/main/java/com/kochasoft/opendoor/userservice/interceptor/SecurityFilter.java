package com.kochasoft.opendoor.userservice.interceptor;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.dto.ResponseDTO;
import com.kochasoft.opendoor.userservice.dto.UserDTO;
import com.kochasoft.opendoor.userservice.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

public class SecurityFilter extends OncePerRequestFilter {

    UserService userService=null;

    public SecurityFilter(UserService userService){
        this.userService=userService;
    }
    
    Logger log=LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        verifyToken(request);
        filterChain.doFilter(request, response);
        
    }


    void verifyToken(HttpServletRequest request){
        String authenticationHeader = request.getHeader("Authorization");

        //checks if token is there
        if (authenticationHeader == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Missing token!");

        FirebaseToken decodedToken = null;
        try {
            //Extracts token from header
            String token = authenticationHeader.replace("Bearer ", "");
            //verifies token to firebase server
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        }
        catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Error! "+e.toString());
        }

        //if token is invalid
        if (decodedToken==null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid token!");
        }

        String uid = decodedToken.getUid().trim();

        log.info("logged user uid : {}",uid);
        
        User user = userService.findByUuid(uid, Status.ACTIVE);

        if(user==null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User not found!");

        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UserDTO userDto = mapper.convertValue(user, UserDTO.class);
        log.info("user dto {}",userDto);
        
        request.setAttribute("user", userDto);
    }
    
}
