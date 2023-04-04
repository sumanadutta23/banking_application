package com.jwt.helper;

public class UserNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1240217253520942214L;
	public UserNotFoundException() {
		super("User with this username is not found in the Database !!");
	}
		public UserNotFoundException(String msg) { 
		super(msg); 
	}
}
