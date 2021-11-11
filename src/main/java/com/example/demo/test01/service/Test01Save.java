package com.example.demo.test01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.service.MemberSave;
import com.example.demo.test01.domain.Test01;
import com.example.demo.test01.repository.Test01Repository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW,noRollbackFor = Exception.class)
public class Test01Save {
	
	@Autowired
	private Test01Repository test01Repository;
	
	public void oneSave(Test01 i) {
		
//		Test01 targetMember = new Test01();
		try {
			
			System.out.println(i.getId());
		
//			test01Repository.save(i);
			test01Repository.insertTgt(i);
			
			
		}catch (Exception e) {
			System.out.println("Error");
						System.out.println(e.toString());
			
		}
}
}
