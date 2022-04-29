package com.kochasoft.opendoor.userservice.service;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kochasoft.opendoor.userservice.dto.CardDTO;
import com.kochasoft.opendoor.userservice.dto.ResponseDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CardServiceImpl implements CardService {

    @Value("${opndr.interaction.service.url}")
    String interactionServiceUrl;
    
    @Override
    public void createCard(CardDTO cardDto,String token)  throws Exception{

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

  
        final HttpEntity<CardDTO> entity = new HttpEntity<>(cardDto, headers);

        ResponseEntity<ResponseDTO> responseDto = new RestTemplate().postForEntity(interactionServiceUrl+"/v1/cards", entity, ResponseDTO.class, cardDto);
    }
    
}
