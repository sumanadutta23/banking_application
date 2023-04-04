package com.jwt.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 931347234991659270L;
	private String authority;
	
	public Authority (String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {

		return this.authority;
	}
	
}
