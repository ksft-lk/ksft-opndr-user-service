package com.kochasoft.opendoor.userservice.service;

import com.kochasoft.opendoor.userservice.dto.CardDTO;

public interface CardService {
    public CardDTO createCard(CardDTO cardDto, String token);
}
