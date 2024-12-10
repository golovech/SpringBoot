package org.sist.sb06_sbb4.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sist.sb06_sbb4.exception.DataNotFoundException;
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
	public void create(String subject, String content) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
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
	
	
}
