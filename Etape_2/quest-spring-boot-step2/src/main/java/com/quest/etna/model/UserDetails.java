package com.quest.etna.model;

public class UserDetails {
	
	private String username;
	private UserRole role;
	
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDetails(User user) {
		this.username = user.getUsername();
		this.role = user.getRole();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}

}
