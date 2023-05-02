package co.ovmkas.domain;

import lombok.Data;

@Data
public class PageDto {

	// 하단에 출력 될 페이지 사이즈(<1,2,3,4,5>)
	private int pageCount = 10;
	// 시작 페이지 숫자
	private int startPage;
	// 종료 페이지 숫자
	private int endPage;
	// 게시글 총 갯수
	private int total;
	// next, prev(한개짜리)
	private boolean prev;
	private boolean next;
	private boolean doublePrev;
	private boolean doubleNext;
	// Criteria
	private Criteria cri;

	public PageDto(int total, Criteria cri) {
		//total = 글의 갯수
		this(10, total, cri);
	}

	public PageDto(int pageCount, int total, Criteria cri) {
		this.pageCount = pageCount;
		this.total = total;
		this.cri = cri;
//		cri.getAmount();
//		cri.getPageNum();
//		total
		/************** 페이징 처리에 대한 페이지 갯수 계산하는 공식 ***********************************/
		endPage = (cri.getPageNum() + (pageCount - 1)) / pageCount * pageCount;
		startPage = endPage - (pageCount - 1);
		int realEnd = (total + (cri.getAmount() - 1)) / cri.getAmount();
//		System.out.println(realEnd);
		if (endPage > realEnd) {
			endPage = realEnd;
		}
		prev = cri.getPageNum() > 1;
		next = cri.getPageNum() < realEnd;
		
		doublePrev = startPage >1;
		doubleNext = endPage < realEnd;
	}
	// 예정 <<, >>

}
