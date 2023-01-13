package com.example.demo.routing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.routing.service.RoutingTest01Service;
import com.example.demo.routing.service.RoutingTest02Service;

@RestController
@RequestMapping("/api")
public class RoutingTest01Controller {
	

	@Autowired
	RoutingTest01Service routingTest01Service;
	
	@Autowired
	RoutingTest02Service routingTest02Service;
	
	@GetMapping("/multidb/routing/test01")
	public void routingTest01Call() {
		routingTest01Service.routingTest01Svc();
	}
	
//	@GetMapping("/multidb/routing/test02")
//	public void routingTest02Call() {
//		routingTest01Service.routingTest02Svc();
//	}
	
	@GetMapping("/multidb/routing/test03")
	public void routingTest03Call() {
		routingTest02Service.routingTest01Svc();
	}
}
