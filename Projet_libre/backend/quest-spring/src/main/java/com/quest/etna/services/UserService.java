package com.quest.etna.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.quest.etna.model.User;
import com.quest.etna.repositories.UserRepository;
import com.quest.etna.utils.StringUtils;
import com.quest.etna.utils.Userutils;
import com.quest.etna.controller.AuthenticationController;;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User getCurrentUserFromUserdetail(){
		UserDetails userDetail = Userutils.getCurrentUser();
		List<User> allUsers = new ArrayList<User>();
		userRepository.findAll().iterator().forEachRemaining(allUsers::add);
		return allUsers.stream().filter(u -> StringUtils.verifyHash(AuthenticationController.users.get(userDetail.getUsername())
				, u.getPassword())).reduce((u1, u2)-> {
		            throw new IllegalStateException("Multiple elements: " + u1 + ", " + u2);
				}).get();
	}

}
