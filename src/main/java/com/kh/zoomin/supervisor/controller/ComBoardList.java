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
import com.kh.zoomin.supervisor.model.dto.CompanyReview;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class comBoardList
 */
@WebServlet("/supervisor/comBoardList")
public class ComBoardList extends HttpServlet {
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
		List<CompanyReview> comList = ss.getComReviewAll(param);
		
		//B.pagebar영역
		int totalComReviewCnt = ss.getTotalComReviewCnt();
		String url = request.getRequestURI();	//url : 페이지바를 눌렀을떄 이동할 주소
		String comPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalComReviewCnt, url);
		
		//응답처리
		request.setAttribute("comList", comList);
		request.setAttribute("comPagebar", comPagebar);
		request.getRequestDispatcher("/WEB-INF/views/supervisor/comBoardList.jsp").forward(request, response);
	}

}
