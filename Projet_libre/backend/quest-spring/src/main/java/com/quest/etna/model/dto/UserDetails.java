package com.quest.etna.model.dto;

import com.quest.etna.model.User;
import com.quest.etna.model.User.UserRole;

public class UserDetails {
	
	private String username;
	private UserRole userRole;
	
	public UserDetails() {
	}
	
	public UserDetails(User user) {
		this.username = user.getUsername();
		this.userRole = user.getRole();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	

}
