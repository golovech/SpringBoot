package org.sist.sb06_sbb7.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sist.sb06_sbb7.answer.Answer;
import org.sist.sb06_sbb7.exception.DataNotFoundException;
import org.sist.sb06_sbb7.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	//페이징 처리 전 목록
	public List<Question> getList() {
		return this.questionRepository.findAll();
	}
	
	
	//id에 해당하는 질문 가져와
	public Question getQuestion(Integer id) {
		Optional<Question> oQuestion = this.questionRepository.findById(id);
		if(oQuestion.isPresent()) {
			return oQuestion.get();//Optional<Question>을 Question으로 변환하여 return
		}else {
			//강제로 내가 만든 사용자 정의 예외를 발생시켜
			//exception package + DataNotFoundException 예외 클래스 추가
			throw new DataNotFoundException("질문이 없습니다~");
		}
		
	}
	
	//질문 등록
	public void create(String subject, String content, SiteUser user) {
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		
		question.setAuthor(user);//작성자 정보 추가
		this.questionRepository.save(question);
	}
	
	//페이징 처리된 목록
	/*[1]
	public Page<Question> getList(int page) {
		
		//1번 페이지를 주고 싶다면 0이라고 주어야 한다.
		//								  		   pageNumber, pageSize
		
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));//정렬기준이 여러 개일 때가 있기 때문에 굳이 List로 만든 것
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		//현재 페이지 받아와서 10개씩 뿌려 direction이랑 property라는 parameter도 있으니 공부할 것
		return this.questionRepository.findAll(pageable); // 1번 페이지 끝
	}
	*/
	
	//질문 수정
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	//질문 삭제
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	//추천					질문						추천인(로그인 회원)
	public void vote(Question question, SiteUser siteUser) {//사실 id만 와도 되는데 객체 단위로 넘어가
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}
	
	
	//페이징 처리 + 검색 기능 추가된 목록
	//[1]
	public Page<Question> getList(int page, String kw) {//keyword 넘어오겠지, 검색 조건은 지금 없으므로 패스
		
		//1번 페이지를 주고 싶다면 0이라고 주어야 한다.
		//								  		   pageNumber, pageSize
		
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));//정렬기준이 여러 개일 때가 있기 때문에 굳이 List로 만든 것
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		//Specification<Question> spec = search(kw);
		//현재 페이지 받아와서 10개씩 뿌려 direction이랑 property라는 parameter도 있으니 공부할 것
		//return this.questionRepository.findAll(spec, pageable); // 1번 페이지 끝
		return this.questionRepository.findAllByKeyword(kw, pageable);
	}
	
	
	//페이징 처리 + 검색을 위한
	//Specification,
	//Specification은 org.springframework.data.jpa.domain 기준, 나머지는 jakarta.persistence 기준 
	private Specification<Question> search(String kw) {//매개변수로 검색어
		
        return new Specification<>() {//interface를 annonymous class로 객체 생성해서 return, 이 interface의 toPredicate가 abstract method로 있어
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {//generic에 의해 만들어져
            	//JPA 안의 function으로 JPQL 만들어!
            	//Root<Question>이므로 from Question과 같아 기준이 되는 entity
                query.distinct(true);  // 중복을 제거 
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);//question과 SiteUser가 Left Outer Join, SiteUser alias를 u1로 준 셈
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(//이거 or 말고 if문으로 분기 나눠도 될 듯? 근데 그럴 바에 QueryDSL로 동적으로 처리하는 게 좋을 듯 공부하자
                		cb.like(q.get("subject"), "%" + kw + "%"), // 제목 QueryDSL에선 BooleanBuilder에 담지만 지금은 CriteriaBuilder에 담아 
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용  검색 조건, 검색어가 Spring Legacy에서도 Criteria에 담겨졌었지
                        cb.like(u1.get("username"), "%" + kw + "%"), // 질문 작성자 
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용 
                        cb.like(u2.get("username"), "%" + kw + "%")  // 답변 작성자
                        );    //이게 내부적으로는 Repository에 있는 JPQL로 만들어져서 날아가는거야
            }
        };
    }
	
	
}//class
