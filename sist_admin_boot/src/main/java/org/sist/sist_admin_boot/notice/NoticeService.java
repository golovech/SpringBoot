package org.sist.sist_admin_boot.notice;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final
public class NoticeService {

	private final NoticeRepository noticeRepository;
	
	// 질문 등록
	public void create(
			String title, 
			String content,
			String writer, 
			String email
			) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setWriter(writer);
		notice.setEmail(email);
		notice.setCreateDate(LocalDateTime.now());
		this.noticeRepository.save(notice);
		
	}
	
}
