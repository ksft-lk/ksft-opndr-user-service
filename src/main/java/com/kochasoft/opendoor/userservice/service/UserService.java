package com.kochasoft.opendoor.userservice.service;

import java.util.List;

import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;

public interface UserService {
	public User createUser(User user,boolean createCard)throws Exception;
	public User findById(String id);
	public User findUserByMobileNumber(String mobileNumber,String mobileCountryCode);
	public User findByUuid(String uuid,Status status);
	public List<User> findAllUsers();
	public void deleteUserById(String id);
	public String generateNewToken(String uid) throws Exception;
}
