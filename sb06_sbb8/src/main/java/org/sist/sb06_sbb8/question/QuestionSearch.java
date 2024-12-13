package org.sist.sb06_sbb8.question;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionSearch {
	
	//				스플릿 된 검색조건을 배열로 받기
	Page<Question> searchAll(String [] types, String keyword, Pageable pageable);

}
