package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.supervisor.model.dto.RecruitBoard;
import com.kh.zoomin.supervisor.model.dto.CompanyReview;
import com.kh.zoomin.supervisor.model.dto.SalaryReview;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class SupervisorBoardListServlet
 */
@WebServlet("/supervisor/salBoardList")
public class SalBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService ss = new SupervisorService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		int cPage = 1;
		int numPerPage = 5;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}	catch(NumberFormatException e) {}
		
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		
		Map<String, Object> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		
		//2.업무로직 (전체조회) 
			//A.content영역
			//연봉게시판
		List<SalaryReview> salList = ss.getSalReviewAll(param);
		//System.out.println("salList = " + salList);
		
			//회사리뷰게시판
		//List<CompanyReview> comList = ss.getComReviewAll(param);
		//System.out.println("comList = " + comList);
			
			//채용게시판
		//List<RecruitBoard> recList = ss.getRecBoardAll(param);
		//System.out.println("recList = " + recList );
		
			//B.pagebar영역
		int totalSalReviewCnt = ss.getTotalSalReviewCnt();
		//int totalComReviewCnt = ss.getTotalComReviewCnt();
		//int totalComRecruitCnt = ss.getTotalComRecruitCnt();
		//System.out.println("totalSalReviewCnt = " + totalSalReviewCnt);
		String url = request.getRequestURI();	//url : 페이지바를 눌렀을떄 이동할 주소
		String salPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalSalReviewCnt, url);
		//String comPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalComReviewCnt, url);
		//String recPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalComRecruitCnt, url);
		//System.out.println("pagebar = " + pagebar);
		
		//응답처리		
		request.setAttribute("salList", salList);
		request.setAttribute("salPagebar", salPagebar);
		//request.setAttribute("comList", comList);
		//request.setAttribute("comPagebar", comPagebar);
		//request.setAttribute("recList", recList);
		//request.setAttribute("recPagebar", recPagebar);
		request.getRequestDispatcher("/WEB-INF/views/supervisor/salBoardList.jsp").forward(request, response);
	}

}
