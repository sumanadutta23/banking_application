package com.jwt.services;

import java.util.Set;

import com.jwt.model.User;
import com.jwt.model.UserRole;

public interface UserService {
	
	//creating User
	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	// get user by username
	
	public User getUser(String username);
	
	//delete user by id
	
	public void deleteUser(Long id);
	
	public User updateUser(User user, Long id);

}
