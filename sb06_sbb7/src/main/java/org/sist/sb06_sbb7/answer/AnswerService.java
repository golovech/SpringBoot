package org.sist.sb06_sbb7.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.sist.sb06_sbb7.exception.DataNotFoundException;
import org.sist.sb06_sbb7.question.Question;
import org.sist.sb06_sbb7.user.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	//답변 작성
	public Answer create(Question question, String content, SiteUser author) {
		
		Answer answer = new Answer();
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		
		answer.setAuthor(author);//작성자 정보 추가
		
		this.answerRepository.save(answer);//이거 return 해도 돼
		return answer;//Mapping 되어있어
	}
	
	// 답변 조회
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer가 낫파운드 케잌이양");
        }
    }

    // 답변 수정
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
    
    // 답변 삭제
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }
    
  //추천					답변						추천인(로그인 회원)
  	public void vote(Answer answer, SiteUser siteUser) {//사실 id만 와도 되는데 객체 단위로 넘어가
  		answer.getVoter().add(siteUser);
  		this.answerRepository.save(answer);
  	}
}//class
