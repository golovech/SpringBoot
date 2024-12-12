package org.sist.sb06_sbb7.question;

import java.security.Principal;

import org.sist.sb06_sbb7.answer.AnswerForm;
import org.sist.sb06_sbb7.page.Criteria;
import org.sist.sb06_sbb7.page.PageDTO;
import org.sist.sb06_sbb7.user.SiteUser;
import org.sist.sb06_sbb7.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor//final로 자동 주입 마치 autowired
public class QuestionController {
	
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final UserService userService;
	
	/*
	  @GetMapping("/question/list")
	  @ResponseBody
		  public String list() {
			  System.out.println("@@@@@@@@@@@QuestionController 들어옴~");
			  return "question list임"; 
	  }
	 */
	
	/*@GetMapping("/list")
	  public void list(Model model) {
		System.out.println("@@@@@@@@@@@QuestionController 들어옴~");
		List <Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList", questionList);
	  }*/
	
	
	/* 목록 보기 1번
	@GetMapping("/list")
	  public void list(Model model) {
		System.out.println("@@@@@@@@@@@QuestionController list method 들어옴~");
		//List <Question> questionList = this.questionRepository.findAll();
		List <Question> questionList = this.questionService.getList();
		model.addAttribute("qList", questionList);
	  }*/
	
	//목록 보기 2번(페이징 처리)
	/*
	@GetMapping("/list")
	  public void list(Model model, @RequestParam(value = "page", defaultValue = "0")int page) {
		System.out.println("@@@@@@@@@@@QuestionController list method 들어옴~");
		//paging 처리가 된 객체를 paging이라고 하자
		Page<Question> paging = this.questionService.getList(page);
		//int startPage = (page / 10) * 10;  // 현재 페이지의 시작점
		//int endPage = Math.min(startPage + 9, paging.getTotalPages() - 1);  // 시작점 + 9가 전체 페이지수보다 크면 전체 페이지수를 끝점으로

		model.addAttribute("paging", paging);
		//model.addAttribute("startPage", startPage);
		//model.addAttribute("endPage", endPage);
		
		
		Criteria criteria = new Criteria(page+1, 10 ); 
	    int total = (int)paging.getTotalElements();
	    model.addAttribute("pageMaker",  new PageDTO(criteria, total));
	    
	  }
	  */
	
	//목록 보기 3번(페이징 처리 + 검색)
		@GetMapping("/list")
		  public void list(Model model, @RequestParam(value = "page", defaultValue = "0")int page, @RequestParam(value = "kw", defaultValue = "")String kw) {
			System.out.println("@@@@@@@@@@@QuestionController list method 들어옴~");
			//paging 처리가 된 객체를 paging이라고 하자
			Page<Question> paging = this.questionService.getList(page, kw);
			//int startPage = (page / 10) * 10;  // 현재 페이지의 시작점
			//int endPage = Math.min(startPage + 9, paging.getTotalPages() - 1);  // 시작점 + 9가 전체 페이지수보다 크면 전체 페이지수를 끝점으로

			model.addAttribute("paging", paging);
			model.addAttribute("kw", kw);
			//model.addAttribute("startPage", startPage);
			//model.addAttribute("endPage", endPage);
			
			
			Criteria criteria = new Criteria(page+1, 10 ); 
		    int total = (int)paging.getTotalElements();
		    model.addAttribute("pageMaker",  new PageDTO(criteria, total));
		    
		  }
	
	// 질문 상세 보기
	// /question/detail/2
	@GetMapping("/detail/{id}")
	  public String detail(@PathVariable("id") Integer id, Model model, AnswerForm answerForm) {
		System.out.println("@@@@@@@@@@@QuestionController detail method  들어옴~");
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		
		return "/question/detail";
	  }
	
	//질문 등록하기
	@PreAuthorize("isAuthenticated()")//글 쓸 권한 있어? 없으면 로그인 페이지로 가렴 @EnableMethodSecurity(prePostEnabled = true)
	@GetMapping("/create")
	public void create(QuestionForm questionForm) {//필요없어도 동기화 된 객체가 필요하니까 그냥 넣어... 없으면 view단에서 연결할 게 없어서 오류 나(create.html 확인)
		System.out.println("@@@@@@@@@@@QuestionController create method  들어옴~");
		
		
	}
	/*
	@PostMapping("/create")
	public String questionCreate(@RequestParam(value = "subject") String subject, @RequestParam(value = "content") String content) {
		System.out.println("@@@@@@@@@@@QuestionController post로 questionCreate method  들어옴~");
		//질문 등록
		this.questionService.create(subject, content);
		//질문 목록으로 redirect
		return "redirect:/question/list";
	}*/
	//질문 등록 + 유효성 검사
	@PreAuthorize("isAuthenticated()")//@EnableMethodSecurity(prePostEnabled = true)
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
		System.out.println("@@@@@@@@@@@QuestionController post로 questionCreate method  들어옴~");
		
		if (bindingResult.hasErrors()) {
	        return "/question/create";
		}
		
		String subject = questionForm.getSubject();
		String content = questionForm.getContent();
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		//질문 등록
		this.questionService.create(subject, content, siteUser);
		
		//질문 목록으로 redirect
		return "redirect:/question/list";
	}
	
	//수정 클릭
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, 
    										@PathVariable("id") Integer id, Principal principal) {
		
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        //entity -> dto
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "/question/create";
    }
	
	//수정 처리
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "/question/create";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
	
	/* /delete/${question.id}| */
	//삭제 처리
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }//사실 권한 확인은 필요없어
        
        this.questionService.delete(question);
        return "redirect:/question/list";
    }
	
	@PreAuthorize("isAuthenticated()")//로그인 안했으면 로그인으로
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.vote(question, siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
}//class
