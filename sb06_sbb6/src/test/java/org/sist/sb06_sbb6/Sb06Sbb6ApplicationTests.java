package org.sist.sb06_sbb6;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.sist.sb06_sbb6.answer.Answer;
import org.sist.sb06_sbb6.answer.AnswerRepository;
import org.sist.sb06_sbb6.question.Question;
import org.sist.sb06_sbb6.question.QuestionRepository;
import org.sist.sb06_sbb6.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
class Sb06Sbb6ApplicationTests {

	
	
	// 1. 2번글의 답변 조회
	@Autowired
	private AnswerRepository answerRepository; // 주입
	
	// 데이터를 가져오는 방식
	// 1. 즉시 방식 (Eager)
	// 2. 지연 방식 (Lazy)
	// 지금 이건 지연방식이ㅏㄹ 데이터 못가져오는것.
	
	/*
	@Transactional // spring 트랜잭셔널로 선택하기
	@Test
	void testJpa() {

		Optional<Question> op = this.questionRepository.findById(2);
		if(op.isPresent()) {
			Question q = op.get();
			List<Answer> anList = q.getAnswerList(); // 오류 : 여기에선 세션이랑 연결이 끊겨서 못가져온다?
			anList.stream().forEach(a -> System.out.println("answer : " + a.getContent() ) );
			
		// 내가한거
		//List<Answer> list = this.answerRepository.findByIdContaining(2);
		//System.out.println(">>" + list.size());
			
			
		// 준용
		//List<Answer> list = answerRepository.findByQuestionId(2);
	    //list.stream().forEach(a -> System.out.println("answer : " + a.getContent() ) );
			 
			
		}
		
	}
	*/
	

	/*
	@Test
	void testJpa() {
		
		Optional<Question> op = this.questionRepository.findById(2);
		
		if(op.isPresent()) { // 존재하니?
			
			Question q = op.get(); // 얻어와!
			
			// 답변을 생성하고, 
			Answer a = new Answer(); // 객체 생성
			a.setContent("집에간다니깐~!!!!");
			a.setCreateDate(LocalDateTime.now());
			a.setQuestion(q);
			// 답변
			this.answerRepository.save(a); // 답변 a를 저장.
			
		}
		
	}
	*/
	/*
	@Test
	void testJpa() {
		
		Optional<Question> op = this.questionRepository.findById(2);
		
		if(op.isPresent()) { // 존재하니?
			
			Question q = op.get(); // 얻어와!
			
			// 답변을 생성하고, 
			Answer a = new Answer(); // 객체 생성
		a.setContent("집에간다니깐~!!!!");
			a.setCreateDate(LocalDateTime.now());
			a.setQuestion(q);
			// 답변
			this.answerRepository.save(a); // 답변 a를 저장.
			
		}
		
	
	}
	*/
	
	
	
	/////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private QuestionRepository questionRepository; // 주입
	
	/*
	// 질문 삭제
	@Test
	void testJpa() {
		System.out.println("> 총 질문 수 : " + this.questionRepository.count());
		
		// void this.questionRepository.deleteAllById(1);
		Optional<Question> oq = this.questionRepository.findById(1);
		Question q1 = oq.get();
		this.questionRepository.delete(q1);
		
		System.out.println("> 삭제 후 총 질문 수 : " + this.questionRepository.count());
	
	}
	*/
	
	/*
	// 질문 수정(제목만)
	// update set subject = 'want' where id = 1; 을 어케 바꾸나?
	@Test
	void testJpa() {
		Optional<Question> optional = this.questionRepository.findById(1);
		if(optional.isPresent()) {
			Question q1 = optional.get(); // 얻어와서 수정가능
			q1.setSubject("수정된 제목");
			this.questionRepository.save(q1);
			
		} // if
	}
	*/
	/*
	@Test
	void testJpa() {
		Question q = this.questionRepository.findBySubjectAndContent("sbb", "sbb");
		System.out.println(">> id : " + q.getId());
	}
	*/
	/*
	@Test
	void testJpa() {
		// Repository
		// CrudRepository
		// PagingAndSortRepository
		// RpaRepository
		
		// 1.
		
		  List<Question> list = this.questionRepository.findBySubjectContaining("sb");
		  System.out.println(">>" + list.size());
		 
		
		// 2.
		List<Question> list2 = this.questionRepository.findBySubjectLike("sb"); // 검색어 넣기.
		System.out.println(">>" + list2.size());
		
		// 2-2.sb%, %sb,  %sb%  오라클이랑똑같음 
		List<Question> list2 = this.questionRepository.findBySubjectLike("%sb%"); // 검색어 넣기.
		System.out.println(">>" + list2.size());
		
	}
	*/
	/*
	@Test
	void testJpa() {
		// Repository
		// CrudRepository
		// PagingAndSortRepository
		// RpaRepository
		Question q = this.questionRepository.findBySubject("sbb가 뭔가요?");
		System.out.println(">>" + q.getContent());
		
	}
	*/
	
	/*
	@Test
	void testJpa() {
		
		// 질문ID에 해당하는 질문을 조회
		Optional<Question> optional = this.questionRepository.findById(2); // 2번글에 대한 질문을 조회
		if (optional.isPresent()) { // 값이 존재한다면?
			Question q = optional.get(); // Question 객체 변환
			System.out.println(q.getSubject() + ":" + q.getContent());
		} // if
	}
	*/
	
	/*
	@Test
	void testJpa() {
		
		// 모든 질문을 조회하는 작업
		List<Question> list = this.questionRepository.findAll(); // 모든걸 찾음
		System.out.println("> " + list.size()); // 레코드 갯수
		
		list.stream().forEach(q->System.out.println(q.getSubject()));
		
	}
	*/
	
	/*
	@Test
	void testJpa() {
		
		// 질문 등록 테스트
		Question q1 = new Question();
		q1.setSubject("sbb가 뭔가요?");
		q1.setContent("엥?점심뭐먹을까요?");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		
		
		Question q2 = new Question();
		q2.setSubject("sbb가 뭔가요?");
		q2.setContent("엥?점심뭐먹을까요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); // save : DB에 insert가 되는거임.
	}
	*/
	
	// 질문 insert!!(페이징처리위해서)
	
	@Autowired
	private QuestionService questionService;
	
	/*
	@Test
	void testJpa() {
		
		for (int i = 1; i < 285; i++) {
			String subject = "밥 : " + i;
			String content = "메뉴 : " + i;
			this.questionService.create(subject, content);
		} // for
	}
	*/

}
