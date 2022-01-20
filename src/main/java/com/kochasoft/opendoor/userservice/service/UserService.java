package com.kochasoft.opendoor.userservice.service;

import com.kochasoft.opendoor.userservice.domain.User;

public interface UserService {
	public void createUser(User user);
	public void findUserUserById(String id);
	public User findUserByMobileNumber(String mobileNumber);
	public void findAllUsers();
	public void deleteUserById(String id);
}
