package com.jwt.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.config.JwtUtils;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.model.User;
import com.jwt.services.impl.UserDetailsServiceImpl;


@RestController
@CrossOrigin(origins = "*")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
//	@Autowired
//	private userService UserServiceImpl;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try 
		{
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		} 
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
		// authenticate

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);		
		return ResponseEntity.ok(new JwtResponse(token));
		
		
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch(DisabledException e) {
			throw new Exception("USER DISABLED" +e.getMessage());
		}
		catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials " +e.getMessage());
		}
				
	}
	
	@GetMapping("/currentuser")
	public User getCurrentUser(Principal principal) {
		
		return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
	}
	
//	@PutMapping("/updateUser")
//    public ResponseEntity<User> updateUser(@RequestBody User user) {
//       return new ResponseEntity<User>(userService.)
//    }


}
