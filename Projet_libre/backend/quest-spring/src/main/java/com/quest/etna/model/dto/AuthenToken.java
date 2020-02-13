package com.quest.etna.model.dto;

public class AuthenToken {
	
	private String token;
	private String role;
	
	public AuthenToken(){};

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
