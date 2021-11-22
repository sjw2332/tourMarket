package board.vo;

public class PageVo {
	
	//Field
	private int   nowpage;        // 현재페이지  
	private int   pagecount;      // 페이지당 자료수
	
	private int   startpagenum;    // 페이지 시작번호 
	private int   endpagenum;      // 페이지 종료번호
	
	private int   totalcount;       // 전체 자료수
	private int   pagenumcount;     // 페이지번호 출력수 
	private int   totalpagecount;   // 전체 페이지수

	// Constructor
	public PageVo() {
		
	}
	public PageVo(int nowpage, int pagecount, int startpagenum, int endpagenum, int totalcount, int pagenumcount,
			int totalpagecount) {
		this.nowpage = nowpage;
		this.pagecount = pagecount;
		this.startpagenum = startpagenum;
		this.endpagenum = endpagenum;
		this.totalcount = totalcount;
		this.pagenumcount = pagenumcount;
		this.totalpagecount = totalpagecount;
	}

	//Constructor
	public PageVo(int nowpage, int pagecount, int totalcount) {
		
		this.nowpage = nowpage;  	    // 현재 페이지
		this.pagecount = pagecount;  	// 페이지당 자료수
		
		this.totalcount = totalcount;	// 전체 자료수
		
		this.pagenumcount = 3;              

		//  시작 페이지 번호
		this.startpagenum = (nowpage-1) / pagenumcount * pagenumcount + 1; 
	    
		//  끝 페이지 번호
		this.endpagenum = startpagenum + pagenumcount - 1;
		
		// 전체 페이지  수(13) = 전체자료수(123) / 페이지당 자료수(10)
		this.totalpagecount = (int) Math.ceil ((double) totalcount / (double) pagecount) ;	
		
		if(this.endpagenum >= this.totalpagecount )
			this.endpagenum = this.totalpagecount;
		
		if( this.nowpage >=  this.totalpagecount  )
			this.nowpage =  this.totalpagecount;
	}
	
	// Getter & Setter
	public int getNowpage() {
		return nowpage;
	}
	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getStartpagenum() {
		return startpagenum;
	}
	public void setStartpagenum(int startpagenum) {
		this.startpagenum = startpagenum;
	}
	public int getEndpagenum() {
		return endpagenum;
	}
	public void setEndpagenum(int endpagenum) {
		this.endpagenum = endpagenum;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getPagenumcount() {
		return pagenumcount;
	}
	public void setPagenumcount(int pagenumcount) {
		this.pagenumcount = pagenumcount;
	}
	public int getTotalpagecount() {
		return totalpagecount;
	}
	public void setTotalpagecount(int totalpagecount) {
		this.totalpagecount = totalpagecount;
	}

	// toString
	@Override
	public String toString() {
		return "PageVo [nowpage=" + nowpage + ", pagecount=" + pagecount + ", startpagenum=" + startpagenum
				+ ", endpagenum=" + endpagenum + ", totalcount=" + totalcount + ", pagenumcount=" + pagenumcount
				+ ", totalpagecount=" + totalpagecount + "]";
	}
	
}
