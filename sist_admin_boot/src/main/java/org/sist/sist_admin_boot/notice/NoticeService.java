package org.sist.sist_admin_boot.notice;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.DataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final
public class NoticeService {

	private final NoticeRepository noticeRepository;
	
	/* create */
	
	// 공지사항 등록
	public void create(
			String title, 
			String content,
			String writer, 
			String email,
			Boolean fix
			) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setWriter(writer);
		notice.setEmail(email);
		notice.setFix(false);
		notice.setCreateDate(LocalDateTime.now());
		this.noticeRepository.save(notice);
	}
	
	
	
	/* List */
	
	// 공지사항 목록 보기 (페이징 X)
	public List<Notice> getList(){
		return this.noticeRepository.findAll();
	}
	
	// 공지사항 목록 보기 (페이징 O)
	public Page<Notice> getList(int page){
		
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.noticeRepository.findAll(pageable);
	}
	
	
	
	
	/* detail */
	
	// 공지사항 상세보기
	public Notice getNotice(Integer id) {
		Optional <Notice> op = this.noticeRepository.findById(id);
		return op.get();
	}
	
	
	
	/* modify */
	
	// 공지사항 수정
	public void modify(
			Notice notice, String title, String content, Boolean fix
			) {
		notice.setTitle(title);
		notice.setContent(content);
		notice.setFix(fix);
		this.noticeRepository.save(notice);
	}
	
	
	
	/* delete */
	public void delete(Notice notice) {
		this.noticeRepository.delete(notice);
	}
	
	
	
	
}
