package org.sist.sist_admin_boot.notice;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.sist.sist_admin_boot.page.Criteria;
import org.sist.sist_admin_boot.page.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notice")

@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	// 첨부파일 업로드
	@Value("${file.upload-dir}")
    private String uploadDir;
	
	// 공지사항 등록하기
	@GetMapping("/create")
	public void noticeCreate(NoticeForm noticeForm) {
	}
	
	
	// 첨부파일 작업
	@PostMapping("/create")
	public String noticeCreate(
	        @Valid NoticeForm noticeForm,
	        BindingResult bindingResult,
	        HttpServletRequest request
	) {
	    if (bindingResult.hasErrors()) {
	        return "notice/create";
	    }

	    MultipartFile file = noticeForm.getUploadFile();
	    String savedFilePath = null;

	    if (file != null && !file.isEmpty()) {
	        try {
	            // 실제 파일 저장 경로 가져오기
	        	String uploadDir = request.getServletContext().getRealPath("/upload/notice/");
	            // String uploadDir = "C:/Users/User/AppData/Local/Temp/tomcat-docbase/static/upload/notice/";
	            File dir = new File(uploadDir);
	            if (!dir.exists()) {
	                dir.mkdirs(); // 경로가 없으면 생성
	            }

	            // 저장 파일 이름 생성
	            String originalFileName = file.getOriginalFilename();
	            String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

	            // 파일 저장
	            File savedFile = new File(dir, savedFileName);
	            file.transferTo(savedFile);

	            // 브라우저 접근 가능한 URL 경로 설정
	            savedFilePath = "/upload/notice/" + savedFileName;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "notice/create";
	        }
	    }

	    this.noticeService.create(
	            noticeForm.getTitle(),
	            noticeForm.getContent(),
	            noticeForm.getWriter(),
	            noticeForm.getEmail(),
	            noticeForm.getViewCount(),
	            noticeForm.getFix(),
	            savedFilePath
	    );

	    return "redirect:/notice/list";
	}

		
		/*
		MultipartFile file = noticeForm.getUploadFile();
		String savedFilePath = null;
		
		if(file != null & !file.isEmpty()) {
			try {
				 String uploadPath  = request.getServletContext().getRealPath("/static/upload/notice");
		            File dir = new File(uploadPath );
		            if (!dir.exists()) {
		                dir.mkdirs(); // 디렉토리가 없으면 생성
		            }
				String originalFileName = file.getOriginalFilename();
				String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
				
				// 파일 저장경로
				File savedFile = new File(uploadPath , savedFileName);
				file.transferTo(savedFile);
				
				savedFilePath = "/upload/notice/" + savedFileName; // 저장경로
				System.out.println("savedFilePath : " + uploadPath);
				
			} catch (Exception e) {
				e.printStackTrace();
				return "notice/create";
			}
		}
		// 에러 없으면
		String title = noticeForm.getTitle();
		String writer = noticeForm.getWriter();
		String content = noticeForm.getContent();
		String email = noticeForm.getEmail();
		Integer viewCount = noticeForm.getViewCount();
		Boolean fix = noticeForm.getFix();
		MultipartFile filePath = noticeForm.getUploadFile(); // 파일
		
		this.noticeService.create(title, content, writer, email, viewCount, fix, savedFilePath);
		
		return "redirect:/notice/list";
	}
	*/
	
	
	// 공지사항 리스트 + 검색기능 (페이징 + 페이징블럭)
	@GetMapping("list")
	public void list(Model model, 
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="type", defaultValue = "s") String type,
			@RequestParam(value="kw", defaultValue = "") String keyword) {
		
		Page<Notice> paging = this.noticeService.getList(page, type, keyword);
		List<Notice> fixedNotices = this.noticeService.getFixedNotices();
		
		int startPage = (page / 10) * 10;
		int endPage =  Math.min(startPage + 9, paging.getTotalPages() - 1);
		
		model.addAttribute("fixedNotices", fixedNotices); // 고정된 공지사항
	    model.addAttribute("paging", paging); // 일반 공지사항
	    model.addAttribute("kw", keyword);
	    
		Criteria criteria = new Criteria(page+1, 10 ); 
        int total = (int)paging.getTotalElements();
        model.addAttribute("pageMaker",  new PageDTO(criteria, total));
	}
	
	
	// 공지사항 상세보기
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
	    Notice notice = this.noticeService.getNotice(id);
	    model.addAttribute("notice", notice);
	    model.addAttribute("fileUrl", notice.getFilePath()); // 저장된 URL 그대로 전달
	    return "notice/detail";
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
