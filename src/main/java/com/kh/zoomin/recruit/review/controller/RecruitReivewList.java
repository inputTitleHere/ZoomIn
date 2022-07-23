package com.kh.zoomin.recruit.review.controller;

import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.interceptor.SlowQueryReport.QueryStats;

import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;
import com.kh.zoomin.applicant.information.model.dto.CompanyInfo;
import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview;
import com.kh.zoomin.applicant.salaryReviewBoard.model.service.SalaryReviewService;
import com.kh.zoomin.common.ZoominUtils;

/**
 * Servlet implementation class RecruitReivewList
 */
@WebServlet("/recruit/review/recruitReviewList")
public class RecruitReivewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService crs = new CompanyReviewService();
	private SalaryReviewService srs = new SalaryReviewService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String companyNo=request.getParameter("companyNo");
		int currCompanyReivewPage=1;
		int currSalaryReivewPage=1;
		int itemsPerPage=10;
		try {
			// crp = Company-Review-Page 약자
			currCompanyReivewPage=Integer.parseInt(request.getParameter("crp"));
		}catch(NumberFormatException e) {}
		try {
			// srp = Salary-Review-Page 약자
			currSalaryReivewPage=Integer.parseInt(request.getParameter("srp"));
		}catch (NumberFormatException e) {}
		
		int companyStart=(currCompanyReivewPage-1)*itemsPerPage+1;
		int companyEnd=companyStart+itemsPerPage-1;
		
		int salaryStart=(currSalaryReivewPage-1)*itemsPerPage+1;
		int salaryEnd=salaryStart+itemsPerPage-1;
		
		// 회사정보 회수하기
//		CompanyInfo ci = 
		
		// 리뷰회수용 parameter 세팅
		Map<String,Object> paramCompany=new HashMap<String, Object>();
		Map<String,Object> paramSalary=new HashMap<String, Object>();
		paramCompany.put("companyStart", companyStart);
		paramCompany.put("companyEnd", companyEnd);
		paramCompany.put("companyNo", companyNo);
		
		paramSalary.put("salaryStart", salaryStart);
		paramSalary.put("salaryEnd", salaryEnd);
		paramSalary.put("companyNo", companyNo);
		
		// 내용물 가져오기
		List<CompanyReview> companyReviewResult = crs.findByCompanyNo(paramCompany);
		List<SalaryReview> salaryReviewResult=srs.findByCompanyNo(paramSalary);
		
		// 페이지바 만들기
		int totalCompanyReviewCount=crs.getTotalContent(companyNo);
		int totalSalaryReviewCount=srs.getTotalContent(companyNo);
		
		String url=request.getRequestURI();
		String queryString=request.getQueryString();
		
		String companyReviewPagebarHTML=makePagebar(currCompanyReivewPage, itemsPerPage, totalCompanyReviewCount, url,"crp",queryString);
		String salaryReviewPagebarHTML=makePagebar(currSalaryReivewPage, itemsPerPage, totalSalaryReviewCount, url,"srp",queryString);
		
		request.setAttribute("companyReview", companyReviewResult);
		request.setAttribute("companyReviewPagebarHTML", companyReviewPagebarHTML);
		request.setAttribute("salaryReview", salaryReviewResult);
		request.setAttribute("salaryReviewPagebarHTML", salaryReviewPagebarHTML);
		
		request.getRequestDispatcher("/WEB-INF/views/recruit/review/recruitReviewList.jsp").forward(request, response);
		
	}

	private String makePagebar(int currentPage, int numPerPage, int totalCount, String url,String mode,String originalQueryString) {
		StringBuilder pagebarBuilder = new StringBuilder();
		StringBuilder queryBuilder=new StringBuilder();
		int totalPages=(int)Math.ceil((double)totalCount/numPerPage);
		int pagebarSize=5;//페이지바에 노출할 번호 개수
		int pagebarStart=(((currentPage-1)/pagebarSize)*pagebarSize)+1;
		int pagebarEnd=pagebarStart+pagebarSize-1;
		
		int pageNo=pagebarStart;
		
		//쿼리구문에 여러개 있는 경우  title=Main_page&action=raw 가 출력됨
		if(originalQueryString==null) {
			// 아예 쿼리문이 없는 경우
			queryBuilder.append("?"+mode+"=");
		}else if(!originalQueryString.contains(mode)) {
			// 쿼리문은 있는데 mode에 일치하는 쿼리가 없는 경우
			queryBuilder.append("?"+originalQueryString+"&"+mode+"=");
		}else {
			// 쿼리문도 있고 mode도 이미 있는 경우
			queryBuilder.append("?");
			if(originalQueryString.indexOf("&",originalQueryString.indexOf(mode))==-1) {
				// mode의 위치가 끝이면
				queryBuilder.append(originalQueryString.substring(0, originalQueryString.indexOf("&"+mode)));
			}else {
				// mode의 위치가 끝이 아니라면 -> mode에 해당하는 쿼리를 제거
				String[] splits=originalQueryString.split(mode+"=[0-9]+&");
				String merge = splits[0]+splits[1];
				queryBuilder.append(merge);
			}
			queryBuilder.append("&"+mode+"=");
		}
		
		String queryWithoutNumbers=queryBuilder.toString();
		url+=queryWithoutNumbers;
		
		if(pageNo==1) {
			
		}else {
			pagebarBuilder.append("<a href='"+url+(pageNo-1)+"'>이전</a>\n");
		}
		while(pageNo<=pagebarEnd&&pageNo<=totalPages){
			if(pageNo==currentPage) {
				// 현재페이지이면 링크없이 그냥 span을 추가.
				pagebarBuilder.append("<span class='cPage'>"+pageNo+"</span>\n");
			}else {
				// 현재페이지가 아니면 해당 페이지를 로드할 링크를 기제한다.
				pagebarBuilder.append("<a href='"+url+pageNo+"'>"+pageNo+"</a>\n");
			}
			pageNo++;
		}
		if(pageNo>totalPages) {
			//마지막 페이지 블럭인 경우 "다음"을 추가하지 않는다.
			// 위의 증감처리로 인해 pageNo에는 해당 페이지 블럭의 마지막 값+1이 들어있다.
		}else {
			pagebarBuilder.append("<a href='"+url+pageNo+"'>다음</a>\n");
		}
		
		return pagebarBuilder.toString();
	}
	
}
