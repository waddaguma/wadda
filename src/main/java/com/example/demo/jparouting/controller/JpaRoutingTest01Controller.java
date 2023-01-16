package com.example.demo.jparouting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jparouting.service.JpaRoutingTest01Service;

@RestController
@RequestMapping("/api")
public class JpaRoutingTest01Controller {
	
	@Autowired
	JpaRoutingTest01Service jpaRoutingTest01Service;

	@GetMapping("/multidb/jparouting/test01")
	public void routingTest03Call() {
		jpaRoutingTest01Service.get("xxxxxx");
	}
	
}
