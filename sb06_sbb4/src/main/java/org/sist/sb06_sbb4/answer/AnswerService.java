package org.sist.sb06_sbb4.answer;

import java.time.LocalDateTime;

import org.sist.sb06_sbb4.question.Question;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	// 답변 생성
	public void create(Question question, String content) { // String content 은 AnswerForm 을 넘겨도 됨. DTO객체로 넘겨도 된다는말임
		
		Answer answer= new Answer();
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		
		this.answerRepository.save(answer);
	}
	
}
