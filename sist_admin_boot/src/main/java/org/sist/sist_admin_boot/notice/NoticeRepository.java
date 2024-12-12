package org.sist.sist_admin_boot.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface NoticeRepository extends JpaRepository<Notice, Integer>{
	
	// 공지사항 목록 페이징
	Page<Notice> findAll(Pageable pageable);
	
	// 조회수 업데이트
	@Transactional
	@Modifying
	@Query("UPDATE Notice n SET n.viewCount = n.viewCount + 1 WHERE n.id = :id")
	void updateView( @Param("id") Integer id);
	
	
	
	
}
