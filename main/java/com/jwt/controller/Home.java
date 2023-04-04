  package com.jwt.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.UserFoundException;
//import com.jwt.helper.UserNotFoundException;
import com.jwt.model.Role;
import com.jwt.model.User;
import com.jwt.model.UserRole;
import com.jwt.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class Home {

//	@GetMapping("/welcome")
//	public String welcome() {
//		String text="this is private page ";
//		text += "this page is not allowed to unauthenticated users";
//		return text;
//	}
//	
//	@GetMapping("/getusers")
//	public String getuser() {
//		
//		return "{\"name\":\"sumana\"}";
//	}
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//creating user
	
	@PostMapping("/addUser")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleId(47L);
		role.setRoleName("USER");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		
		return this.userService.createUser(user, roles);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	//delete user by id
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
		return ResponseEntity.ok("User found");
	}

}
