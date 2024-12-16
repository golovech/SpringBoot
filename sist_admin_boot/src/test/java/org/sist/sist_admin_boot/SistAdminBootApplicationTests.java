package org.sist.sist_admin_boot;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.sist.sist_admin_boot.notice.Notice;
import org.sist.sist_admin_boot.notice.NoticeRepository;
import org.sist.sist_admin_boot.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



@SpringBootTest
class SistAdminBootApplicationTests {

	@Autowired
	private NoticeRepository noticeRepository;
	
	/*
	//@Transactional // spring 트랜잭셔널로 선택하기
	@Test
	void testJpa() {
		// 모든 공지사항을 조회하는 작업
		List<Notice> list = this.noticeRepository.findAll(); // 모든걸 찾음
		System.out.println("> " + list.size()); // 레코드 갯수
		
		list.stream().forEach(q->System.out.println(q.getTitle()));
	}
	*/
	
	@Autowired
	private NoticeService noticeService;
	
	// 공지사항 insert
	
	
	@Test
	void testJpa() {
		
		for (int i = 1; i <= 500; i++) {
			String title = "공지사항 : " + i;
			String writer = "관리자";
			String content = "공지사항 테스트!!";
			String email = "admin@naver.com";
			Integer viewCount = 0;
			Boolean fix = false;
			String filePath = null;
			
			this.noticeService.create(title, writer, content, email, viewCount, fix, filePath);
		} // for
	}
	
	
	

}
