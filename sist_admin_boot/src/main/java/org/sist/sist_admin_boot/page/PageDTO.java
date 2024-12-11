package org.sist.sist_admin_boot.page;

import lombok.Getter;
import lombok.ToString;

// 페이징 블럭 만들 DTO  < 1 2 3 [4] 5 6 7 8 9 10 >
@Getter // setter는 생성자에 의해 생성이 될테니까 패스
@ToString
public class PageDTO {

	private int startPage; // 시작번호
	private int endPage; // 끝번호
	private boolean prev;
	private boolean next;

	private int total; // 총페이지 가져올 변수
	private Criteria criteria; // pageNum, amount 를 여기서 쓰자~

	public PageDTO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;

		this.endPage = (int)(Math.ceil(criteria.getPageNum()/
					   (double)criteria.getAmount())) * criteria.getAmount();
		this.startPage = this.endPage - criteria.getAmount() + 1;

		int realEndPage = (int)(Math.ceil((double)total/criteria.getAmount()));
		if(realEndPage < this.endPage) this.endPage = realEndPage;

		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}

}
