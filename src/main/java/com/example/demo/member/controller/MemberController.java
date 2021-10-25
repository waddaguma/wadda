package com.example.demo.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.domain.Member;
import com.example.demo.member.service.MemberService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j

//@RequestMapping(value="/members")
@RequestMapping("/api")
@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@ApiOperation("TEST")
	@RequestMapping("/posttest01")
	public Member postTest01(@RequestBody Member member) {
   
//		memberService.processMemberOracle();
//		return "http://www.naver.com";
		
		return member;
	}

	@ApiOperation("TEST")
	@RequestMapping("/posttest02")
	public String postTest02() {
   
//		memberService.processMemberOracle();
		return "http://www.naver.com=================";
	}

	@ApiOperation("TEST")
	@RequestMapping("/multidb01")
	public void multiDb01() {
   
		memberService.processMemberOracle();
	}
	
	@ApiOperation("TEST")
	@RequestMapping("/mysql01")
	public void mysql01() {
   
		memberService.processMysql();
	}
}
