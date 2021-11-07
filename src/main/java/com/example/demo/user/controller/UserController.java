package com.example.demo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.domain.User;
import com.example.demo.user.service.UserService;

@RestController 
@RequestMapping("/users") 
public class UserController { 
	
	@Autowired 
	private UserService userService; 
	
	@GetMapping("/user") 
	public List<User> listUser(){ 
		return userService.findAll(); 
		} 
	@PostMapping("/user") 
	public User create(@RequestBody User user){ 
		return userService.save(user);
		} 
	}

