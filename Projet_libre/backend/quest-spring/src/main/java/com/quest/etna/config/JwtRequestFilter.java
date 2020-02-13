package com.quest.etna.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quest.etna.controller.AuthenticationController;
import com.quest.etna.model.JwtUserDetails;
import com.quest.etna.model.User;
import com.quest.etna.repositories.UserRepository;

import io.jsonwebtoken.Claims;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String EMPTY_STRING = "";
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUserDetailsService jwtUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getHeader(HEADER) != null && request.getHeader(HEADER).startsWith(PREFIX)) {
			String tokenBearer = request.getHeader(HEADER).replace(PREFIX, EMPTY_STRING);
			String userName = jwtTokenUtil.getUsernameFromToken(tokenBearer);
			if(userName != null) {
				JwtUserDetails userDetail = jwtUserDetailsService.loadUserByUsername(userName);
				String credentialUnHashed = !AuthenticationController.users.isEmpty() ?
											AuthenticationController.users.get(userName) : userDetail.getPassword();
				UsernamePasswordAuthenticationToken auteh = new UsernamePasswordAuthenticationToken(userDetail.getUsername(), 
						credentialUnHashed, userDetail.getAuthorities());
				//authenticate the user
            	Authentication authentication = authenticationManager.authenticate(auteh);
            	SecurityContext securityContext = SecurityContextHolder.getContext();
            	securityContext.setAuthentication(authentication);
				
			}else
				return;
		}else {
			SecurityContextHolder.clearContext();
		}
		filterChain.doFilter(request, response);
	}

}
