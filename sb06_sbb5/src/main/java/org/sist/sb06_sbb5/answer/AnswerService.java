package org.sist.sb06_sbb5.answer;

import java.time.LocalDateTime;

import org.sist.sb06_sbb5.question.Question;
import org.sist.sb06_sbb5.user.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	// 답변 생성
	public void create(Question question, String content, SiteUser author) { // String content 은 AnswerForm 을 넘겨도 됨. DTO객체로 넘겨도 된다는말임
		
		Answer answer= new Answer();
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		
		answer.setAuthor(author); // 작성자 정보 추가.
		
		this.answerRepository.save(answer);
	}
	
}
