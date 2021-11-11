package com.example.demo.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.test01.service.Test01Service;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j

//@RequestMapping(value="/members")
@RequestMapping("/api")
@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Test01Service test01Service;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@ApiOperation("LIST input Test")
	@RequestMapping("/posttest01")
//	public List<Member> postTest01(@RequestBody List<Member> member) {
	public void postTest01(@RequestBody List<Member> member) {
//		public Member postTest01(@RequestBody Member member) {
		
//		memberRepository.deleteAll();
		memberService.memberStart(member);
   
//		memberRepository.save(member);
//		return "http://www.naver.com";
		
//		return member;
	}

	@ApiOperation("Truncate & Delete Test")
	@RequestMapping("/posttest02")
	public String postTest02() {
   
//		test01Service.truncateTgt();
		test01Service.processMemberOracle2();
		return "http://www.naver.com=================";
	}

	@ApiOperation("TEST")
	@RequestMapping("/multidb01")
	public void multiDb01() {
   
//		memberService.processMemberOracle();
	}
	
	@ApiOperation("TEST")
	@RequestMapping("/mysql01")
	public void mysql01() {
   
		memberService.processMysql();
	}
}
