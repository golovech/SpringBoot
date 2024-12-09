package org.sist.sb06_sbb2.question;

import java.util.List;
import java.util.Optional;

import org.sist.sb06_sbb2.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final
public class QuestionService {

	private final QuestionRepository questionRepository; // 주입
	
	public List<Question> getList(){
		return this.questionRepository.findAll();
	}
	
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
}
