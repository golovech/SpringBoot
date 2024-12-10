package org.sist.sb06_sbb4.question;

import org.sist.sb06_sbb4.answer.AnswerForm;
import org.sist.sb06_sbb4.page.Criteria;
import org.sist.sb06_sbb4.page.PageDTO;
import org.springframework.data.domain.Page;
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
@RequestMapping("/question/*")

@RequiredArgsConstructor // final
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
	
	/*
	// 1. 질문 리스트 보기(페이징X)
	@GetMapping("list")
	public void list(Model model) { // org.springframewokr model 추가하여 view에 전달..
		//List <Question> questionList = this.questionRepository.findAll();
		List <Question> questionList = this.questionService.getList(); // Repository 대신 서비스로 전달
		model.addAttribute("questionList", questionList); // 모델에 담기. 뷰에서 받기 가능~
		
	}
	*/
	
	/*
	// 2. 질문 리스트 보기 (페이징OO)
	@GetMapping("list")
	public void list(Model model, 
					@RequestParam(value="page", defaultValue = "0") int page
					) { // 0 주면 1페이지 됨.
		// 페이징 처리가 된 객체 : paging
		Page<Question> paging = this.questionService.getList(page); 
		model.addAttribute("paging", paging); // paging 안에 페이징관련정보가 다 들어있다..?
	}
	*/
	
	// 2. 질문 리스트 보기 (페이징OO + 페이징블럭)
	@GetMapping("list")
	public void list(Model model, 
			@RequestParam(value="page", defaultValue = "0") int page
			) { // 0 주면 1페이지 됨.
		// 페이징 처리가 된 객체 : paging
		Page<Question> paging = this.questionService.getList(page); 
		model.addAttribute("paging", paging); // paging 안에 페이징관련정보가 다 들어있다..?
		
		Criteria criteria = new Criteria(page+1, 10 ); 
        int total = (int)paging.getTotalElements();
        model.addAttribute("pageMaker",  new PageDTO(criteria, total));
	}
	
	
	
	// 질문 상세 보기
	// localhost/question/detail/2
	@GetMapping("detail/{id}") // Question.java 의 컬럼값.
	public String detail(@PathVariable("id") Integer id, Model model, 
						AnswerForm answerForm) {  // 여기에도 AnswerForm 넣어줘야함.
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question/detail";
		
	}

	
	/*
	 * 1.
	// 질문 등록하기     컨트롤러 - 서비스 - 리포지토리
	@PostMapping("/create") 
	public String questionCreate(
			@RequestParam(value="subject") String subject, 
			@RequestParam(value="content") String content
			) { 
		// 1. 질문 등록 작업
		this.questionService.create(subject, content);
		// 2. 질문 목록으로 리다이렉트.
		
		return "redirect:/question/list";
	}
	*/
	
	
	// 질문 등록하기
	//  <a th:href="@{/question/create}" class="btn btn-primary">질문 등록하기</a>
	@GetMapping("/create") 
	public void questionCreate(QuestionForm questionForm) {  
		// 유효성 검사할때 여기에도 매개변수값 넣어줘야함.
		
	}
	
	// 2.
	// 질문 등록하기 (유효성 검사 자동)
	@PostMapping("/create") 
	public String questionCreate(
			@Valid QuestionForm questionForm, BindingResult bindingResult  // @Valid + BindingResult는 짝꿍이다.
			) { 
		// 1. 유효성검사에 에러 있는지 확인
		if(bindingResult.hasErrors()) { // 에러 있으면?
			return "question/create"; // 다시 포워딩
		}
		// 에러 없으면
		String subject = questionForm.getSubject();
		String content = questionForm.getContent();
		this.questionService.create(subject, content);
		
		// 2. 질문 목록으로 리다이렉트.
		return "redirect:/question/list";
	}
	
	
	
}
