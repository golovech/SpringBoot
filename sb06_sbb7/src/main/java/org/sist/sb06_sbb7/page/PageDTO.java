package org.sist.sb06_sbb7.page;

import lombok.Getter;
import lombok.ToString;

//paging block
// 1 2 [3] 4 5 6 7 8 9 10 >
@Getter
@ToString
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int total;
	private Criteria criteria;
	//criteria 안에 이미 몇 페이지인지, 한 페이지에 얼마 뿌릴지 있으므로 변수 선언 생략
	
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
