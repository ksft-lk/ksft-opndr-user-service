package com.kochasoft.opendoor.userservice.service;

import com.kochasoft.opendoor.userservice.dto.CardDTO;
import com.kochasoft.opendoor.userservice.dto.ResponseDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CardServiceImpl implements CardService {

    @Value("${opndr.interaction.service.url}")
    String interactionServiceUrl;
    
    @Override
    public CardDTO createCard(CardDTO cardDto,String token) {

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
  
        final HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
  
  
        // ResponseEntity<ResponseDTO> responseDto =
        // new RestTemplate().exchange(interactionServiceUrl+"/v1/cards", HttpMethod.POST, entity, ResponseDTO.class, cardDto);

        ResponseEntity<ResponseDTO> responseDto = new RestTemplate().postForEntity(interactionServiceUrl+"/v1/cards", entity, ResponseDTO.class);

       return (CardDTO)responseDto.getBody().getResult();
    }
    
}
