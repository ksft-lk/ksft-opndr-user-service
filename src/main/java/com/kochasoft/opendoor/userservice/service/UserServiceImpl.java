package com.kochasoft.opendoor.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.dto.CardDTO;
import com.kochasoft.opendoor.userservice.dto.Local;
import com.kochasoft.opendoor.userservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;

	@Autowired
	CardService cardService;

	@Override
	@Transactional
	public User createUser(User user,boolean createCard, String token) {
		User savedUser=repository.save(user).block();


		if(!createCard){
			return savedUser;
		}
		if(savedUser==null){
			return null;
		}

		CardDTO cardDto=new CardDTO();

		Local userDisplayNameLocal= new Local();
		userDisplayNameLocal.setEn(user.getName());

		Local subTitle= new Local();
		subTitle.setEn("Personal");
		
		cardDto.setUserDisplayName(userDisplayNameLocal);
		cardDto.setRestrictionType("No restriction");
		cardDto.setUserId(savedUser.getId());
		cardDto.setSubTitle(subTitle);
		cardDto.setExpiration(0);
		cardDto.setAvatar(savedUser.getAvatar());
		cardService.createCard(cardDto,token);

		return savedUser;
	}

	@Override
	public User findById(String id) {
		return repository.findById(id).block();
	}

	@Override
	public List<User> findAllUsers() {
		return repository.findAll().collectList().block();

	}

	@Override
	public void deleteUserById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUserByMobileNumber(String mobileNumber,String mobileCountryCode) {
		return repository.findByMobileNumberAndMobileCountryCode(mobileNumber,mobileCountryCode).block();
		
	}

	@Override
	public User findByUuid(String uuid, Status status){

		return repository.findByUuidAndStatus(uuid, status).block();
	}

}
