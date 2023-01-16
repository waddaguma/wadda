package com.example.demo.jparouting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.jparouting.domain.PcmTest01;
import com.example.demo.jparouting.repository.JpaRoutingTest01Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JpaRoutingTest01Service {
	
	@Autowired
	private JpaRoutingTest01Repository jpaRoutingTest01Repository;
	
	@Transactional
	public PcmTest01 get(String id) {
		
		PcmTest01 output = jpaRoutingTest01Repository.getById(id);
		
		log.info("============pcmtest01 : {}", output.toString());
		
		return output;
		
		
	}

}
