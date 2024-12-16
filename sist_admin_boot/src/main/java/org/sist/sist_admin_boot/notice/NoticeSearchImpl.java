package org.sist.sist_admin_boot.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class NoticeSearchImpl 
extends QuerydslRepositorySupport 
implements NoticeSearch{

	public NoticeSearchImpl () {
		super(Notice.class);
	}

	@Override
	public Page<Notice> searchAll(String[] types, String keyword, Pageable pageable) {
		
		QNotice notice = QNotice.notice;       
		// SELECT ... FROM question
		JPQLQuery<Notice> query = from(notice);

		// 검색조건 맞는 게 있으면
		if( (types != null && types.length > 0 ) && keyword != null ) {

			BooleanBuilder booleanBuilder = new BooleanBuilder(); 
			//  String [] types = {"s", "c"}         "sc".split("")
			for (String type : types) {
				switch (type) {
				case "t":
					// title LIKE ...
					booleanBuilder.or(notice.title.contains(keyword));
					break;
				case "c":
					// content LIKE ...
					booleanBuilder.or(notice.content.contains(keyword));
					break;
				case "w":
					// content LIKE ...
					booleanBuilder.or(notice.writer.contains(keyword));
					break; 
				case "e":
					// content LIKE ...
					booleanBuilder.or(notice.email.contains(keyword));
					break; 
				} // switch            
			} // for
			query.where(booleanBuilder);
			query.where(notice.id.gt(0L));  // 성능 때문에 추가했는데 없어도 됨. / id 가 0 보다 크면
		} // if

		// 
		this.getQuerydsl().applyPagination(pageable, query);

		// 쿼리 실행
		List<Notice> list = query.fetch(); // 실행
		long count = query.fetchCount(); // 성공 갯수

		// 반환
		return new PageImpl<>(list, pageable, count);
				
	}

}
