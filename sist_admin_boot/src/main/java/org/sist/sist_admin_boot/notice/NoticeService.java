package org.sist.sist_admin_boot.notice;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.DataException;
import org.sist.sist_admin_boot.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
//@Transactional // 자동트랜잭션 
@RequiredArgsConstructor // final
public class NoticeService {

	private final NoticeRepository noticeRepository;
	
	// 공지사항 등록
	public void create(
			String title, 
			String content,
			String writer, 
			String email,
			Integer viewCount,
			Boolean fix,
			String filePath
			) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setWriter(writer);
		notice.setEmail(email);
		notice.setViewCount(viewCount);
		notice.setFix(fix != null && fix);
		notice.setCreateDate(LocalDateTime.now());
		notice.setFilePath(filePath);
		this.noticeRepository.save(notice);
	}
	
	
	// 공지사항 목록 보기 (페이징 X)
	public List<Notice> getList(){
		return this.noticeRepository.findAll();
	}
	
	// 공지사항 목록 보기 (검색 + 페이징 O)
	public Page<Notice> getList(int page, String type, String keyword){
		
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		
		String [] types = type.split("");
 		return this.noticeRepository.searchAll(types, keyword, pageable);
	}
	
	
	// 공지사항 상세보기
	public Notice getNotice(Integer id) {
		Optional <Notice> op = this.noticeRepository.findById(id);
		if(op.isPresent()) {
			this.noticeRepository.updateView(id); // 조회수 업뎃
			return op.get();
		} else {
			throw new DataNotFoundException("DATA NOT FoundCAKE!!");
		}
	}
	
	// 공지사항 수정
	public void modify(
			Notice notice, String title, String content, Boolean fix
			) {
		notice.setTitle(title);
		notice.setContent(content);
		notice.setFix(fix);
		this.noticeRepository.save(notice);
	}
	
	// 공지 삭제 (상세보기에서 삭제)
	public void delete(Notice notice) {
		this.noticeRepository.delete(notice);
	}
	
	// 공지 삭제 (id로 찾기)  되나???
	public List<Notice> deleteList(List<Integer> id) {
		List<Notice> deleteList = this.noticeRepository.findAllById(id); // 삭제할 공지 찾기
		noticeRepository.deleteAllById(id); // 삭제 실행
		return deleteList;
	}
	
	
	
	
}
