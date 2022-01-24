package com.kochasoft.opendoor.userservice.service;

import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;

public interface UserService {
	public void createUser(User user);
	public void findUserUserById(String id);
	public User findUserByMobileNumber(String mobileNumber,String mobileCountryCode);
	public User findByUuid(String uuid,Status status);
	public void findAllUsers();
	public void deleteUserById(String id);
}
