package org.sist.sist_admin_boot.notice;

import java.util.List;

import org.sist.sist_admin_boot.page.Criteria;
import org.sist.sist_admin_boot.page.PageDTO;
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
@RequestMapping("/notice")

@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	
	// 공지사항 등록하기
	@GetMapping("/create")
	public void noticeCreate(NoticeForm noticeForm) {
	}
	
	// 공지사항 등록(유효성 검사 자동)
	@PostMapping("/create")
	public String noticeCreate(
			@Valid NoticeForm noticeForm,
			BindingResult bindingResult) {
		// 1. 유효성 검사, 에러 있는지 확인
		if (bindingResult.hasErrors()) {
		    System.out.println("유효성 검사 에러!!");
		    bindingResult.getAllErrors().forEach(error -> {
		        System.out.println(error.getDefaultMessage());
		    });
		    return "notice/create";
		} // if
		// 에러 없으면
		String title = noticeForm.getTitle();
		String writer = noticeForm.getWriter();
		String content = noticeForm.getContent();
		String email = noticeForm.getEmail();
		Integer viewCount = noticeForm.getViewCount();
		Boolean fix = noticeForm.getFix();
		this.noticeService.create(title, content, writer, email, viewCount, fix);
		
		return "redirect:/notice/list";
	}
	
	// 공지사항 리스트 보기 (페이징 + 페이징블럭)
	@GetMapping("list")
	public void list(Model model, 
			@RequestParam(value="page", defaultValue = "0") int page) {
		
		Page<Notice> paging = this.noticeService.getList(page);
		model.addAttribute("paging", paging);
		
		Criteria criteria = new Criteria(page+1, 10 ); 
        int total = (int)paging.getTotalElements();
        model.addAttribute("pageMaker",  new PageDTO(criteria, total));
	}
	
	// 공지사항 상세보기
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		Notice notice = this.noticeService.getNotice(id);
		model.addAttribute("notice", notice);
		return "notice/detail"; // html
	}
	
	
	// 수정하기 버튼 클릭시
	@GetMapping("/modify/{id}")
	public String noticeModify(
			NoticeForm noticeForm,
			@PathVariable("id") Integer id
			) {
	
		Notice notice = this.noticeService.getNotice(id); // 공지사항 정보 가져오기
		
		noticeForm.setTitle(notice.getTitle());
		noticeForm.setWriter(notice.getWriter());
		noticeForm.setContent(notice.getContent());
		noticeForm.setEmail(notice.getEmail());
		noticeForm.setViewCount(notice.getViewCount());
		//noticeForm.setCreateDate(notice.getCreateDate());
		noticeForm.setFix(notice.getFix());
		
		return "/notice/create";
		
	}
	
	// 수정 처리
	@PostMapping("/modify/{id}")
	public String noticeModify(
			@Valid NoticeForm noticeForm,
			BindingResult bindingResult,
			@PathVariable("id") Integer id) {
		
		if(bindingResult.hasErrors()) {
			return "/notice/create";
		}
		Notice notice = this.noticeService.getNotice(id);
		
		//
		this.noticeService.modify(
				notice, noticeForm.getTitle(), noticeForm.getContent(), noticeForm.getFix()); // 추가
		return String.format("redirect:/notice/detail/%s", id);
	}
	
	
	// 삭제 처리
	@GetMapping("/delete/{id}")
	public String noticeDelete(
			@PathVariable("id") Integer id
			) {
		Notice notice = this.noticeService.getNotice(id);
		this.noticeService.delete(notice);
		return "redirect:/notice/list";
	}
	
	
	// 체크박스 선택항목 삭제
	@PostMapping("/delete")
	public String deleteList(@RequestParam("id") List<Integer> id, Model model ){
		List<Notice> deleteList = this.noticeService.deleteList(id);
		model.addAttribute("deleteList", deleteList);
		return "redirect:/notice/list";
	}
	
	
}
