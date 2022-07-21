package com.kh.zoomin.common;

public class ZoominUtils {
	
	// 백승윤 Start
	/**
	 * 해당 페이지의 url을 받아 그것을 기반으로 cPage 파라미터를 활용하여 페이징 블럭을 만든다.
	 * cPage는 url에 적용되는 파라미터이다. 
	 * 반환으로 완성된 페이징용 HTML형식의 문자열을 반환한다.
	 * @param currentPage
	 * @param numPerPage
	 * @param totalCount
	 * @param url
	 * @return
	 */
	public static String getPageBar(int currentPage, int numPerPage, int totalCount, String url) {
		StringBuilder pagebarBuilder=new StringBuilder();
		
		url+=url.contains("?")?"&cPage=":"?cPage=" ;
		int totalPages=(int)Math.ceil((double)totalCount/numPerPage);
		int pagebarSize=5;//페이지바에 노출할 번호 개수
		int pagebarStart=(((currentPage-1)/pagebarSize)*pagebarSize)+1;
		int pagebarEnd=pagebarStart+pagebarSize-1;
		
		int pageNo=pagebarStart; // 페이지 링크들 만들기
		//=== "이전" 버튼 만들기 ===// 
		if(pageNo==1) {
			// 첫페이지인 경우 "이전"처리를 생략한다.
		}else {
			// 현제 pageNo는 pageStart를 가르킨다. 여기서 -1을 하면 이전페이지 블럭으로 넘긴다.
			pagebarBuilder.append("<a href='"+url+(pageNo-1)+"'>이전</a>\n");
		}
		//=== 각각의 페이지에 대한 링크들 만들기 ===//
		while(pageNo<=pagebarEnd&&pageNo<=totalPages) {
			if(pageNo==currentPage) {
				// 현재페이지이면 링크없이 그냥 span을 추가.
				pagebarBuilder.append("<span class='cPage'>"+pageNo+"</span>\n");
			}else {
				// 현재페이지가 아니면 해당 페이지를 로드할 링크를 기제한다.
				pagebarBuilder.append("<a href='"+url+pageNo+"'>"+pageNo+"</a>\n");
			}
			pageNo++; // 증감처리
		}
		//=== "다음"을 제작한다. ===//
		if(pageNo>totalPages) {
			//마지막 페이지 블럭인 경우 "다음"을 추가하지 않는다.
			// 위의 증감처리로 인해 pageNo에는 해당 페이지 블럭의 마지막 값+1이 들어있다.
		}else {
			pagebarBuilder.append("<a href='"+url+pageNo+"'>다음</a>\n");
		}
		return pagebarBuilder.toString();
	}
	
	/**
	 * XML에 사용되는 특정 기호들을 변환시켜 악성 스크립트를 방지합니다.   
	 * @param str
	 * @return
	 */
	public static String escapeXml(String str) {
		return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	/**
	 * 내용물의 개행문자(\n)를 \<br\>태그로 변환합니다.
	 * @param str
	 * @return
	 */
	public static String convertLineFeedToBr(String str) {
		return str.replaceAll("\\n", "<br>");
	}
	
	// 백승윤 END
	
	//김승환 start
	/**
	 * ajax 전용 redirect 없이 paging 구현 (페이지 이동없이 div 아래서 화면 전환)
	 */
	public static String getApplicantPageBar(int currentPage, int numPerPage, int totalCount, String url) {
		StringBuilder pagebarBuilder=new StringBuilder();
		
		url+=url.contains("?")?"&cPage=":"?cPage=" ;
		int totalPages=(int)Math.ceil((double)totalCount/numPerPage);
		int pagebarSize=5;//페이지바에 노출할 번호 개수
		int pagebarStart=(((currentPage-1)/pagebarSize)*pagebarSize)+1;
		int pagebarEnd=pagebarStart+pagebarSize-1;
		
		int pageNo=pagebarStart; // 페이지 링크들 만들기
		//=== "이전" 버튼 만들기 ===// 
		if(pageNo==1) {
			// 첫페이지인 경우 "이전"처리를 생략한다.
		}else {
			// 현제 pageNo는 pageStart를 가르킨다. 여기서 -1을 하면 이전페이지 블럭으로 넘긴다.
			pagebarBuilder.append("<button class='paging' id='page"+(pageNo-1)+"'>이전</button>\n");
		}
		//=== 각각의 페이지에 대한 링크들 만들기 ===//
		while(pageNo<=pagebarEnd&&pageNo<=totalPages) {
			if(pageNo==currentPage) {
				// 현재페이지이면 링크없이 그냥 span을 추가.
				pagebarBuilder.append("<button style ='background: #F2BB62' class='cPage paging' id='page"+pageNo+"'>"+pageNo+"</button>\n");
			}else {
				// 현재페이지가 아니면 해당 페이지를 로드할 링크를 기제한다.
				pagebarBuilder.append("<button class='paging' id='page"+pageNo+"'>"+pageNo+"</button>\n");
			}
			pageNo++; // 증감처리
		}
		//=== "다음"을 제작한다. ===//
		if(pageNo>totalPages) {
			//마지막 페이지 블럭인 경우 "다음"을 추가하지 않는다.
			// 위의 증감처리로 인해 pageNo에는 해당 페이지 블럭의 마지막 값+1이 들어있다.
		}else {
			pagebarBuilder.append("<button class='paging' id='page"+pageNo+"'>다음</button>\n");
		}
		return pagebarBuilder.toString();
	}
}
