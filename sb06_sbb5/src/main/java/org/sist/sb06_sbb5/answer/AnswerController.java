package org.sist.sb06_sbb5.answer;

import java.security.Principal;

import org.sist.sb06_sbb5.question.Question;
import org.sist.sb06_sbb5.question.QuestionService;
import org.sist.sb06_sbb5.user.SiteUser;
import org.sist.sb06_sbb5.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer/*")
public class AnswerController {
	
	
	private final QuestionService questionService;	// 어떤 질문의 답인지 알기위함.
	private final AnswerService answerService;
	private final UserService userService;
	
	
	/*
	// 1. 답변 작성
	// <form method="post" th:action="@{|/answer/create/${question.id}|}">  이걸로 경로 줄거임!!!
	@PostMapping("create/{id}")
	public String createAnswer(@PathVariable("id") Integer id, @RequestParam("content") String content) {
		
		Question question = this.questionService.getQuestion(id);
		this.answerService.create(question, content);
		
		return String.format("redirect:/question/detail/%s",id); 
		// 딱히 ajax 필요없이 리다이렉트로 답글작업 하면 됨.
	}
	*/
	

	
	// 2. 답변 작성
	@PreAuthorize("isAuthenticated()") // 글쓰기 전 권한을 물어보는 어노테이션. 로그인 안하면 로그인페이지로 이동.
	@PostMapping("create/{id}")
	public String createAnswer(@PathVariable("id") Integer id, 
							   @Valid AnswerForm answerForm, 
							   BindingResult bindingResult,
							   Model model,
							   Principal principal // 유저 정보 담긴 principal
							   ) { // 유효성 검사에 BindingResult 필수
		
		// 1. 유효성검사에 에러 있는지 확인
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName()); // 작성자 추가
		if(bindingResult.hasErrors()) { // 에러 있으면?
			model.addAttribute("question", question);
			return "question/detail"; // 다시 포워딩
		}
		// 2.
		this.answerService.create(question, answerForm.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s",id); 
	}

}
