package com.quest.etna.controller;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quest.etna.model.User;
import com.quest.etna.model.UserDetails;
import com.quest.etna.model.UserRole;
import com.quest.etna.repositories.UserRepository;

@RestController
public class AuthenticationController {
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping(value="/register")
	public ResponseEntity<User> register(@RequestBody User uPost ) {

		User user = new User();
		try {
			user = userRepo.findByUsername(uPost.getUsername());
			
			if (user != null) {
				return new ResponseEntity( HttpStatus.CONFLICT ); //409 when the user already exists
			}
			
			//long millis = System.currentTimeMillis();
			//user = new User(username, pass, UserRole.ROLE_USER, new Date(millis), new Date(millis));
		
			user = userRepo.save(uPost);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity( HttpStatus.SERVICE_UNAVAILABLE ); //503 in case of uncatched error while creating user
		}
		 
		return new ResponseEntity( new UserDetails(user), HttpStatus.CREATED ); //201 when user is created successfully
		
		
	}

}
