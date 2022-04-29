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
    public CardDTO createCard(CardDTO cardDto,String token)  throws Exception{

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper=new ObjectMapper();
        String convertValue = mapper.writeValueAsString(cardDto);
  
        final HttpEntity<String> entity = new HttpEntity<>(convertValue, headers);
  
  
        // ResponseEntity<ResponseDTO> responseDto =
        // new RestTemplate().exchange(interactionServiceUrl+"/v1/cards", HttpMethod.POST, entity, ResponseDTO.class, cardDto);

        ResponseEntity<ResponseDTO> responseDto = new RestTemplate().postForEntity(interactionServiceUrl+"/v1/cards", entity, ResponseDTO.class, cardDto);

       return (CardDTO)responseDto.getBody().getResult();
    }
    
}
