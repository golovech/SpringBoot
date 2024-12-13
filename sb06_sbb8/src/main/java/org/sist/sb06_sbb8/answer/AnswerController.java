	package org.sist.sb06_sbb8.answer;

import java.security.Principal;

import org.sist.sb06_sbb8.question.Question;
import org.sist.sb06_sbb8.question.QuestionService;
import org.sist.sb06_sbb8.user.SiteUser;
import org.sist.sb06_sbb8.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	//answer/create/${question.id}
	/*
	@PostMapping("/create/{id}")
	public String createAnswer(@PathVariable("id") Integer id, @RequestParam("content") String content){
		System.out.println("@@@@@@@@@@@@일단 AnswerController에서 createAnswer들어옴");
		
		Question question = this.questionService.getQuestion(id);
		this.answerService.create(question, content);
		
		//return String.format("redirect:/question/detail/%s", id);
		return "redirect:/question/detail/"+id;
	}*/
	

	//[2] AnswerForm 사용
	@PreAuthorize("isAuthenticated()")//@EnableMethodSecurity(prePostEnabled = true)
	@PostMapping("/create/{id}")
	public String createAnswer(@PathVariable("id") Integer id, @Valid AnswerForm answerForm,
			BindingResult bindingResult, Model model, Principal principal){
		//@Valid 쓰면 걍 BindingResult 따라와야 함
		//Principal 안에 인증 정보가 담겨있어
		System.out.println("@@@@@@@@@@@@일단 AnswerController에서 createAnswer들어옴");
		
		
		
		//1. 에러가 있으면
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
	        return "/question/detail";//detail로 가려면 질문 내용들이 있어야 하니까 Question 객체 가져오자
		}
		
		//2. 
		this.answerService.create(question, answerForm.getContent(), siteUser);
		
		//return String.format("redirect:/question/detail/%s", id);
		
		
		//return "redirect:/question/detail/"+id;
		Answer answer = this.answerService.create(question, answerForm.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s#answer_%s", id, answer.getId());
		//return "/question/detail/#answer_"+ String.valueOf(answerService.getAnswer(id).getId());//detail로 가려면 질문 내용들이 있어야 하니까 Question 객체 가져오자
	}
	
	// 답변 수정
	   @PreAuthorize("isAuthenticated()")
	    @GetMapping("/modify/{id}")
	    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {
	        Answer answer = this.answerService.getAnswer(id);
	        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        answerForm.setContent(answer.getContent());
	        return "/answer/answer_form";
	    }
	   
	   // 답변 수정
	   @PreAuthorize("isAuthenticated()")
	    @PostMapping("/modify/{id}")
	    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
	            @PathVariable("id") Integer id, Principal principal) {
	        if (bindingResult.hasErrors()) {
	            return "/answer/answer_form";
	        }
	        Answer answer = this.answerService.getAnswer(id);
	        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        this.answerService.modify(answer, answerForm.getContent());
	        //return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	        return String.format("redirect:/question/detail/%s#answer_%s", 
	                answer.getQuestion().getId(), answer.getId());
	    }
	   
	   // 답변 삭제
	   @PreAuthorize("isAuthenticated()")
	    @GetMapping("/delete/{id}")
	    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
	        Answer answer = this.answerService.getAnswer(id);
	        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	        }
	        this.answerService.delete(answer);
	        //return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	        return String.format("redirect:/question/detail/%s#answer_%s", 
	                answer.getQuestion().getId(), answer.getId());
	    }
	   
	   //답변 추천
	   @PreAuthorize("isAuthenticated()")
	    @GetMapping("/vote/{id}")
	    public String answerVote(Principal principal, @PathVariable("id") Integer id) {
	        Answer answer = this.answerService.getAnswer(id);
	        SiteUser siteUser = this.userService.getUser(principal.getName());
	        this.answerService.vote(answer, siteUser);
	       // return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	        return String.format("redirect:/question/detail/%s#answer_%s", 
	                answer.getQuestion().getId(), answer.getId());
	    }
}
