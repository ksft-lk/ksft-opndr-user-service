package com.kochasoft.opendoor.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;

	@Override
	public void createUser(User user) {
		repository.save(user);

	}

	@Override
	public void findUserUserById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findAllUsers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserById(String id) {
		// TODO Auto-generated method stub

	}

}
