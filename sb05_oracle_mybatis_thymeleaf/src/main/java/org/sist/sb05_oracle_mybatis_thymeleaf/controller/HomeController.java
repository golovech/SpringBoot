package org.sist.sb05_oracle_mybatis_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {
	
	/*
	@GetMapping("/index")
	@ResponseBody // 순수 문자열만 반환
	public String index() {
		return "hello world!";
	}
	*/
	
	// http://localhost/index
	@GetMapping("/home")
	public void home() {
		log.info("/home 컨트롤러 호출......");
	}
	
	
}
