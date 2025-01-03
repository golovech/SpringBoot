package org.sist.sb06_sbb6.question;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sist.sb06_sbb6.exception.DataNotFoundException;
import org.sist.sb06_sbb6.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final
public class QuestionService {

	private final QuestionRepository questionRepository; // 주입
	
	// 질문 목록 가져오기(페이징처리 XX)
	public List<Question> getList(){
		return this.questionRepository.findAll();
	}
	
	// id에 해당하는 질문 가져오는 메서드
	public Question getQuestion(Integer id) {
		Optional<Question> op  = this.questionRepository.findById(id);
		if(op.isPresent()) { // 질문이 존재한다면?
			return op.get(); // Optional<Question> -> Question로 받아서..? ??
		} else {
			// 강제로 사용자정의 예외 발생시키자~
			// excetion 패키지 + DataNotFoundExcetion  예외클래스 추가하자.
			throw new DataNotFoundException("not foundCake.");
		}
	}
	
	// 질문 등록하는 메서드
	public void create(String subject, String content, SiteUser user) { // 질문작성자 추가.
		Question question = new Question();
		//SiteUser siteUser = new SiteUser();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		
		question.setAuthor(user); // 추가
		
		this.questionRepository.save(question);
	}
	
	// 페이징 처리 된 목록보기
	public Page<Question> getList(int page){ // int page : 현재페이지
		//									pageNumber, pageSize(한목록의 글수)
		// Pageable pageable = PageRequest.of(page, 10); // 1번페이지 보려면 0을 줘야함. sort도 줄수 있음.
		
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id")); // 나중에 2차, 3차 정렬 여기에 add해주면
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // 1번페이지 보려면 0을 줘야함. sort도 줄수 있음.
		return this.questionRepository.findAll(pageable); // Pageable 객체 주면, 다 계산 됨.
	}
	
	// 질문 수정
	public void modify(
			Question question, String subject, String content
			) { // 수정하고자 하는 정보들을 매개변수로 받음.
				// 다른 내용은 예전꺼 그대로 가져가야함. ->> 실제 원래 객체가 필요해서 Question를 받은것.
		
		question.setSubject(subject); // set 으로 수정.
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question); // 수정도 sava() 사용.
		
	}
	
	// 질문 삭제
	public void delete(Question question) { // 객체로 받아서 삭제하는걸로 바뀜. 전처럼 id받아서가 아님
		this.questionRepository.delete(question);
	}
	
	
}
