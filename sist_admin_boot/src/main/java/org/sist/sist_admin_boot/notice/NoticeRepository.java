package org.sist.sist_admin_boot.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface NoticeRepository extends JpaRepository<Notice, Integer>, NoticeSearch {
	
	// 공지사항 목록 페이징
	Page<Notice> findAll(Pageable pageable);
	
	// 조회수 업데이트
	@Transactional
	@Modifying
	@Query("UPDATE Notice n SET n.viewCount = n.viewCount + 1 WHERE n.id = :id")
	void updateView( @Param("id") Integer id);
	
	// 고정된 공지사항 (fix = true)
    @Query("SELECT n FROM Notice n WHERE n.fix = true ORDER BY n.createDate DESC")
    List<Notice> findFixedNotices();

	
	
}
