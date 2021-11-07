package com.example.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.example.demo.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> { 
	
	User findByUsername(String username); 
	
}

