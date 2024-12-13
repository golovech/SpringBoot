package org.sist.sist_admin_boot.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeSearch {

	// 검색 (QueryDSL)
	Page<Notice> searchAll(String [] types, String keyword, Pageable pageable);
	
}
