package com.kochasoft.opendoor.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;

	@Override
	public User createUser(User user) {
		System.out.println("user creation service method $$$$$$$$$$$$$");
		return repository.save(user).block();
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
	public User findByUuid(String uuid, Status status) {
		
		return repository.findByUuidAndStatus(uuid,status).block();
	}

}
