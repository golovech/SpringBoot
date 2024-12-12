package org.sist.sb06_sbb7.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//																				<CRUD할 Entity, 그 테이블의 pk 타입>																	
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	//CRUD하는 method가 이미 내장되어 있는거야

	//필요한 함수 만들어
	Question findBySubject(String subject);
	
	
	//1. Query Method(내부적으로는 JPQL로 날아가)
	//method 이름으로 query 자동 생성하는 기능
	//find컬럼명Like
	//find컬럼명Containing
	List<Question> findBySubjectContaining(String subject);
	
	//2. @Query(그냥 JPQL, 객체를 사용하는 거 바로 쿼리를 날려)
	/*
	@Query("SELECT q 	FROM Question q WHERE q.subject LIKE %:subject%")
	List<Question> findBySubjectLike(@Param("subject") String subject);
	*/
	
	
	//Query Method 사용
	List<Question> findBySubjectLike(String subject);
	
	
	//WHERE subject=? AND content=?
	Question findBySubjectAndContent(String subject, String content);
	
	//3. QueryDSL
	
	
	//페이징 처리 (암기)
	Page<Question> findAll(Pageable pageable);
	
	//페이징 처리 (암기) + 검색 Specification<Question> spec
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);
	
	//페이징 처리 (암기) + 검색 @Query
	@Query("select "
            + "distinct q "
            + "from Question q " 
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")//여기 :kw과 @Param과 매핑
	Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
	//controller로부터 키워드를 매개변수로 받아
	
	//@Query를 사용한 게 쿼리가 결국 Specification에 있어서 갔던 것... 그리고 이거 JPQL아니여도
	//각 DB 별 Query도 되겠지만 그럼 DB에 종속적이잖아? 그걸 피하려고 JPA 쓰는건데 의미가 있나 싶음...
}//interface
