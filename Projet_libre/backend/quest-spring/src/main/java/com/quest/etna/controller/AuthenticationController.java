package com.quest.etna.controller;

import com.quest.etna.config.JwtTokenUtil;
import com.quest.etna.customexception.ParametersNotFound;
import com.quest.etna.customexception.ResourceAlreadyExist;
import com.quest.etna.model.User;
import com.quest.etna.model.dto.AuthenToken;
import com.quest.etna.model.dto.UserAuthen;
import com.quest.etna.model.dto.UserDetails;
import com.quest.etna.repositories.UserRepository;
import com.quest.etna.utils.StringUtils;
import com.quest.etna.utils.Userutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
	public static HashMap<String, String> users = new HashMap<String, String>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserDetails registerUser(@RequestBody UserAuthen userAuthen) {
        UserDetails userDetailsToRegister;
        if (userAuthen == null || userAuthen.getPassword() == null || userAuthen.getUsername() == null 
        		|| userAuthen.getEmail() == null) {
            throw new ParametersNotFound();
        } else {
            boolean isAlreadyExist = userRepository.findByUsername(userAuthen.getUsername()) != null;
            if (isAlreadyExist) {
                throw new ResourceAlreadyExist();
            } else {
                User usertoRegister = new User();
                usertoRegister.setCreationDate(new Date());
                usertoRegister.setUsername(userAuthen.getUsername());
                usertoRegister.setPassword(StringUtils.hashBcrypt(userAuthen.getPassword()));
                usertoRegister.setEmail(userAuthen.getEmail());
                //usertoRegister.setRole();
                usertoRegister = userRepository.save(usertoRegister);
                userDetailsToRegister = new UserDetails(usertoRegister);
            }
        }
        return userDetailsToRegister;
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    AuthenToken authenticate(@RequestBody UserAuthen userAuthen) {
    	AuthenToken token = null;
    	if(userAuthen == null || userAuthen.getPassword() == null || userAuthen.getUsername() == null)
    		throw new ParametersNotFound();
    		else {
    			 User user = userRepository.findByUsername(userAuthen.getUsername());
    	            if (user == null || !StringUtils.verifyHash(userAuthen.getPassword(), user.getPassword()))
    	                throw new ResourceNotFoundException();
    	                else {
    	                	UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userAuthen.getUsername(), userAuthen.getPassword(), Collections.emptyList());
    	                	//authenticate the user
    	                	Authentication authentication = authenticationManager.authenticate(authRequest);
    	                	SecurityContext securityContext = SecurityContextHolder.getContext();
    	                	securityContext.setAuthentication(authentication);
    	                	//generating token
    	                	token = new AuthenToken();
    	                	token.setToken(jwtTokenUtil.generateToken(user));
    	                	token.setRole(Userutils.getCurrentUser().getAuthorities().toString());
    	                	System.out.print(token.getToken());
    	                	//save user unhash credentials
    	                	users.put(userAuthen.getUsername(), userAuthen.getPassword());
    	                }
    		}
    	return token;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public static org.springframework.security.core.userdetails.UserDetails testMe(){
    	return Userutils.getCurrentUser();
    }
    
  
}
