package org.sist.sist_admin_boot.notice;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer>{

	// 공지사항 목록보기 - 이건만들필요없다? crud 자동이라?
	
	
	// 공지사항 목록 페이징
	Page<Notice> findAll(Pageable pageable);
	
	
	
	
}
