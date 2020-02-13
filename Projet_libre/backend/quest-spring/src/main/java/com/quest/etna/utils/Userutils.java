package com.quest.etna.utils;

import java.util.List;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.quest.etna.model.User;
import com.quest.etna.repositories.UserRepository;

public class Userutils {
	
	public static User getUserFromCrediential(List<User> users, String passwordUnEncoded) {
		User userResult = null;
		for(User user : users) {
			if(StringUtils.verifyHash(passwordUnEncoded, user.getPassword())) {
				userResult = user;	
				break;
			}
		}
		return userResult;
	}
	
	  public static boolean isConnected() {
	        SecurityContext securityContext = SecurityContextHolder.getContext();
	        return securityContext.getAuthentication().isAuthenticated();
	    }
	  
	  public static org.springframework.security.core.userdetails.UserDetails getCurrentUser(){
	        SecurityContext securityContext = SecurityContextHolder.getContext();
	        Object details = securityContext.getAuthentication().getPrincipal();
	    	if(details instanceof org.springframework.security.core.userdetails.UserDetails)
	    	    return (org.springframework.security.core.userdetails.UserDetails) details;
	    	else
	    	    return null;
	    }
	  
	  public static User getUserFromUserdetails(UserRepository userRepository) {
		  return userRepository.findByUsername(getCurrentUser().getUsername());
	  }
}
