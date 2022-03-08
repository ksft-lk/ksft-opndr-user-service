package com.kochasoft.opendoor.userservice.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.dto.UserDTO;
import com.kochasoft.opendoor.userservice.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

public class SecurityFilter extends OncePerRequestFilter {
    UserService userService=null;

    public SecurityFilter (UserService userService){
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

        String uid = decodedToken.getUid();
        User u = userService.findByUuid(uid, Status.ACTIVE);

        if(u==null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User not found!");
        }
       
        ObjectMapper mapper=new ObjectMapper();
        UserDTO user = mapper.convertValue(u, UserDTO.class);
        
        log.info("interceptor user : {}", user.getId());
        request.setAttribute("user", user);
    }
    
}
