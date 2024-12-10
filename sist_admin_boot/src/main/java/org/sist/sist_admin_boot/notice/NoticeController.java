package org.sist.sist_admin_boot.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notice/*")

@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	
	// 질문등록하기 (유효성검사 추가하기)
	
	@PostMapping("/create")
	public String noticeCreate() {
		
	}
	
	
}
