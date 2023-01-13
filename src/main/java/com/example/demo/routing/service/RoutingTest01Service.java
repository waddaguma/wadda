package com.example.demo.routing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.routing.repository.RoutingTest01Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = false)
public class RoutingTest01Service {
	
	@Autowired
	RoutingTest01Repository routingTest01Repository;

	public void routingTest01Svc() {
		
		String aa = routingTest01Repository.selectTest01();
		
		log.info("============false :{}",aa);
		
	}
//
//	@Transactional(readOnly = true)
//	public void routingTest02Svc() {
//		
//		String aa = routingTest01Repository.selectTest01();
//		
//		log.info("============aa :{}",aa);
//		
//	}`
}
