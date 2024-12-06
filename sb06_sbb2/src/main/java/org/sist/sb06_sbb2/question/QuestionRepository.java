package org.sist.sb06_sbb2.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//														   객체, 그 클래스 PK의 자료형
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	// crud메서드가 이미 내장되어있는 상태임.
	Question findBySubject(String subject);
	
	// 1. Query method
	//  find + 검색어 + Containing
	List<Question> findBySubjectContaining(String subject);
	
	// 2. @query 어노테이션 사용~
	/*
	@Query("select q from question q where q.subject like %:subject%")
	List<Question> findBySubjectLike(@Param("subject") String subject);
	*/
	
	// 2-2 쿼리 메서드 사용~
	List<Question> findBySubjectLike(String subject);
	
	// 3. 쿼리 메서드   where subject =? AND content=?    OR도 가능하고...
	Question findBySubjectAndContent(String subject, String content);
	
	
}
