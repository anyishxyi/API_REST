package com.quest.etna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quest.etna.customexception.AuthenticateException;
import com.quest.etna.model.Comment;
import com.quest.etna.model.Post;
import com.quest.etna.model.User;
import com.quest.etna.model.dto.UserInfoDto;
import com.quest.etna.repositories.CommentRepository;
import com.quest.etna.repositories.PostRepository;
import com.quest.etna.repositories.UserRepository;
import com.quest.etna.utils.Userutils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	
	
	 @GetMapping("/user_infos")
	 @ResponseStatus(HttpStatus.OK)
	 public UserInfoDto getUserInfo() {
		 if(!Userutils.isConnected())
			   throw new AuthenticateException();
		 else {
			 User user = Userutils.getUserFromUserdetails(userRepository);
			 List<Post> userPosts = new ArrayList<Post>();
			 List<Comment> userComments = new ArrayList<Comment>();
			 postRepository.findByUserId(user.getId()).forEach(userPosts::add);
			 commentRepository.findByUserId(user.getId()).forEach(userComments::add);
			 
			 UserInfoDto userInfoDto = new UserInfoDto();
			 user.setComments(userComments);
			 user.setPosts(userPosts);
			 userInfoDto.setUser(user);
			 
			return userInfoDto;
		 }
	 }

}
