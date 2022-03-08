package com.kochasoft.opendoor.userservice.service;

import com.kochasoft.opendoor.userservice.dto.CardDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CardServiceImpl implements CardService {

    @Value("${opndr.interaction.service.url}")
    String interactionServiceUrl;
    
    @Override
    public CardDTO createCard(CardDTO cardDto) {
       return new RestTemplate().postForObject(interactionServiceUrl+"/v1/cards", cardDto, CardDTO.class);
    }
    
}
