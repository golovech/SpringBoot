package org.sist.sb06_sbb7;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.sist.sb06_sbb7.answer.Answer;
import org.sist.sb06_sbb7.answer.AnswerRepository;
import org.sist.sb06_sbb7.question.Question;
import org.sist.sb06_sbb7.question.QuestionRepository;
import org.sist.sb06_sbb7.question.QuestionService;
import org.sist.sb06_sbb7.user.SiteUser;
import org.sist.sb06_sbb7.user.UserRepository;
import org.sist.sb06_sbb7.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
class Sb06Sbb5ApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
/*
	@Test
	void testJpa() {
		//질문 등록 테스트
		Question q1 = new Question();
		
		q1.setSubject("ssb는 뭔가요");
		q1.setContent("sbb에 대해 알고 싶어요");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}*/
	
	
	/*
	@Test
	void testJpa() {
		//모든 질문 조회
		List<Question> list = this.questionRepository.findAll();
		System.out.println("@@@@@@@레코드 갯수는 :" + list.size());
		list.stream().forEach(q->System.out.println(q.getSubject()));
	}*/
	
	
	/*
	@Test
	void testJpa() {
		//질문 ID에 해당하는 질문 조회
		Optional<Question> optional = this.questionRepository.findById(2);
		if (optional.isPresent()) {//그 값이 존재한다면
			Question q = optional.get();
			System.out.println(q.getSubject() + "가 subject고 content는~~" + q.getContent());
			
		}
	}*/
	
	/*
	@Test
	void testJpa() {
		//제목으로 검색
		//this.questionRepository.findBy필드명();
		//this.questionRepository.findBySubject(String );
		Question q = this.questionRepository.findBySubject("스프링부트 모델 질문입니다.");
		System.out.println("@@@@@@@@@@@" + q.getContent());
			
		
	}*/
/*
	@Test
	void testJpa() {
		  //2
		 //List<Question> questions = this.questionRepository.findBySubjectContaining("모델");
		//List<Question> questions = this.questionRepository.findBySubjectLike("모델");
		System.out.println("@@@@@@@@@@@@@" + questions);
		
		
		   //2-2 모델%, %모델, %모델%
		 //List<Question> questions = this.questionRepository.findBySubjectLike("%모델%");
			
			System.out.println("@@@@@@@@@@@@@" + questions);
		    System.out.println("검색된 질문의 수: " + questions.size());
		    
		    System.out.println("검색된 질문의 수: " + questions.size());
		    for (Question q : questions) {
		        System.out.println("제목: " + q.getSubject());
		        System.out.println("내용: " + q.getContent());
		        System.out.println("--------------------");
		    }
			
		
	}*/
	
	//질문 수정(제목만 수정)
	//Update 테이블명
	//SET subject = '수정할 값'
	//WHERE id = 1;
	/*
	@Test
	void testJpa() {
		Optional<Question> q1 = this.questionRepository.findById(1);
		if (q1.isPresent()) {
			Question realQ = q1.get();
			realQ.setSubject("수정된 제목");
			this.questionRepository.save(realQ);
		}//if
		
	}*/
	
	//질문 삭제
	/*
	@Test
	void testJpa() {
		System.out.println("총 질문 수 : " + this.questionRepository.count());
		//void this.questionRepository.deleteById(1);
		Optional<Question> oq = this.questionRepository.findById(1);
		Question q1 = oq.get();
		this.questionRepository.delete(q1);
		System.out.println("총 질문 수 : " + this.questionRepository.count());
		
			Hibernate: 
			    select
			        al1_0.question_id,
			        al1_0.id,
			        al1_0.content,
			        al1_0.create_date 
			    from
			        answer al1_0 
			    where
			        al1_0.question_id=?
			Hibernate: 
			    delete 
			    from
			        question 
			    where
			        id=?
			Hibernate: 
			    select
			        count(*) 
			    from
			        question q1_0
        
	}
	*/
	//---------------------------------------------
	// 답변 저장
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	@Test
	void testJpa() {
		
		
		Optional<Question> optional = this.questionRepository.findById(2);
		if (optional.isPresent()) {
			
			Question q = optional.get();
			
			Answer a = new Answer();
			a.setContent("네 자동생성 가능합니다람쥐");
			a.setCreateDate(LocalDateTime.now());
			a.setQuestion(q);
			this.answerRepository.save(a);
		}
		*/
	
		/*
		 	Hibernate: 
			    select
			        q1_0.id,
			        q1_0.content,
			        q1_0.create_date,
			        q1_0.subject 
			    from
			        question q1_0 
			    where
			        q1_0.id=?
			Hibernate: 
			    insert 
			    into
			        answer
			        (content, create_date, question_id, id) 
			    values
			        (?, ?, ?, default)
		   	
	}
	
	
	
	//질문 1번에 대한 모든 답변글 조회
	//데이터 가져오는 방식
	//1. 즉시 가져오는 방식(Eager)
	//2. 지연시킨 후에 가져오는 방식(Lazy) 	
	
	@Transactional
	@Test
	void testJpa() {
		Optional<Question> optional = this.questionRepository.findById(2);
		//이 이후에 DB 세션 끊어졌어(lazy)
		
	     if (optional.isPresent()) {
			Question q = optional.get();
			List<Answer> answerList = q.getAnswerList();
			answerList.stream().forEach(a -> System.out.println("answer 드가자 : " + a.getContent() ) );
		}*/
		
	//많은 질문을 추가할거야
	/*@Autowired
	private QuestionService questionService;
	@Autowired
	private SiteUser siteUser;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Test
	void testJpa() {
		SiteUser user = userRepository.getById(1);
	    
	    // 생성된 사용자로 질문 생성
	    for (int i = 0; i < 285; i++) {
	        String subject = "질문 " + i;
	        String content = "질문 내용 " + i;
	        this.questionService.create(subject, content, user);
	    }
			
		}*/
}
