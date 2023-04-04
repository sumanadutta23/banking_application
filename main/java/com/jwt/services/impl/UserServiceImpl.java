package com.jwt.services.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.helper.UserFoundException;
import com.jwt.model.User;
import com.jwt.model.UserRole;
import com.jwt.repo.RoleRepository;
import com.jwt.repo.UserRepository;
import com.jwt.services.UserService;


@Service
public class UserServiceImpl implements UserService{
 
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		if(local != null) {
			System.out.println("User is already there !!");
			throw new UserFoundException();
		}
		else
		{
			for(UserRole ur:userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		
		return local;
	}

	
	//getting user by username
	
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}


	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User updateUser(User user, Long id) {
		// TODO Auto-generated method stub
		return null;
	}


////	@Override
//	public User updateUser(User user, Long id) {
//		// TODO Auto-generated method stub
//		Optional<User> user2 = userRepository.findById(id);
//		user.setUsername(user.getUsername());
//		user.setPassword(user.getPassword());
//		user.setFirstName(user.getFirstName());
//		user.setLastName(user.);
//	}
	
//	   public User updateUser(User user, Long id) {
//		   
//	        User existingUser = userRepository.findById(id);
//	   
//	        user.setId(existingUser.getId());
//	        user.setUsername(existingUser.getUsername());
//	        user.setPassword(existingUser.getPassword());
//	        user.setFirstName(existingUser.getFirstName());
//	        user.setLastName(existingUser.getLastName());
//	        user.setEmail(existingUser.getEmail());
//	        user.setPhone(existingUser.getPhone());
//	        user.setProfile(existingUser.getProfile());
//	        
//	        return userRepository.save(user);
//	        
//	    }
	
	




}
