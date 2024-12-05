package org.sist.sb02_mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.java.Log;

@Controller
@Log
public class IndexController {
	
	/*
	@GetMapping("/index")
	@ResponseBody // 순수 문자열만 반환
	public String index() {
		return "hello world!";
	}
	*/
	@GetMapping("/index")
	public void index() {
		System.out.println("/index 컨트롤러 호출......");
	}
	
	
}
