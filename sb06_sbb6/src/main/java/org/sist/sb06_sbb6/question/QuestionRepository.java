package org.sist.sb06_sbb6.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//												(어떤 엔티티와 연관되어있는지 명시)
//														   객체, 그 클래스 PK의 자료형
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	// crud메서드가 이미 내장되어있는 상태임. 
	//   ==> create작업은 이미 내장되어있으니까 구현할 필요 xx
	// findAll 등등.. 도 crud임. 바로쓸수있는것임.
	
	Question findBySubject(String subject);
	
	// 1. Query method
	//  find + 검색어 + Containing
	List<Question> findBySubjectContaining(String subject);
	
	// 2. @query 어노테이션 사용~
	/*
	@Query("select q from Question q where q.subject like %:subject%") // 객체를 사용하는 쿼리임. (jpql == 엔티티 객체로 쿼리날리는.)
					// 대소문자 구별 필수!!
	List<Question> findBySubjectLike(@Param("subject") String subject);
	*/
	
	// 2-2 쿼리 메서드 사용~
	List<Question> findBySubjectLike(String subject);
	
	// where subject =? AND content=?    OR도 가능하고...
	Question findBySubjectAndContent(String subject, String content);
	
	// 3. 쿼리 DSL - 동적쿼리 작업할때 사용해야함.
	
	// 페이징 처리 (암기)
	Page<Question> findAll(Pageable pageable); // Pageable 객체만들기
	
}
