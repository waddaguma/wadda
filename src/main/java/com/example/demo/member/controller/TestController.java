package com.example.demo.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	 @RequestMapping(value="/hello")
	  public String hellSpringBoot() {
	    return "hello";
	  }
	
}
