package org.sist.sist_admin_boot.page;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Criteria : 기준, 척도
// 페이징 처리 기준 클래스라는 뜻
@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum; // 현재 페이지번호
	private int amount; // 한 페이지에 출력할 게시글 수
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}	
	
	// ?pageNum=2&amount=10&type=t&keyword=최 ...
	// 스프링 유틸 안에, 
	//   ㄴ UrlComponentsBullder 라는 클래스가 있다.
	public String getListLisk() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath(getListLisk())
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount);
				return builder.toUriString();
	}
}
