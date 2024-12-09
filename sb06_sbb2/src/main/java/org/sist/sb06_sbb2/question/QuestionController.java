package org.sist.sb06_sbb2.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/question/*")

@RequiredArgsConstructor // autowired 말고 final 붙일때 이게 필요함!!
public class QuestionController {
	
	// private final QuestionRepository questionRepository; // 주입
	private final QuestionService questionService;

	/*
	// 호출하는 용도로 사용.
	@GetMapping("/question/list")
	@ResponseBody
	public String list() {
		return "question list"; // localhost/question/list
	}
	*/
	
	@GetMapping("list")
	public void list(Model model) { // org.springframewokr model 추가하여 view에 전달..
		//List <Question> questionList = this.questionRepository.findAll();
		List <Question> questionList = this.questionService.getList(); // Repository 대신 서비스로 전달
		model.addAttribute("questionList", questionList); // 모델에 담기. 뷰에서 받기 가능~
		
	}
	
	// 질문 상세 보기
	// localhost/question/detail/2
	@GetMapping("detail/{id}") // Question.java 의 컬럼값.
	public String detail(@PathVariable("id") Integer id, Model model) { 
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question/detail";
		
	}
	
	
}
