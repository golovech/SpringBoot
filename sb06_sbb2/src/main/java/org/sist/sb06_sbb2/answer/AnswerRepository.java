package org.sist.sb06_sbb2.answer;

import java.util.List;

import org.sist.sb06_sbb2.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{

	// 내가한거
	//List<Answer> findByIdContaining(Integer id);
	
	
	//@Query("SELECT a FROM Answer a WHERE question.id=:qid")  // :subject 가
	//List<Answer> findByQuestionId(@Param("qid") Integer question_id);// @Param 안에 "subject"와 맵핑됨
	
	List<Answer> findByQuestion(Question question);
	
	/*
	select *
	from answer a join question  q
	where a.id = q.id and q.id=2;
	*/
}
