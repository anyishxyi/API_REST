package com.quest.etna.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.quest.etna.model.JwtUserDetails;
import com.quest.etna.repositories.UserRepository;

public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserRepository userRepo = null;
		return new JwtUserDetails(userRepo.findByUsername(username));
	}

}
