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
import com.kh.zoomin.supervisor.model.dto.SalaryReview;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class SalaryBoardView
 */
@WebServlet("/supervisor/salaryBoardView")
public class SalaryBoardView extends HttpServlet {
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
		
		List<SalaryReview> salList = ss.getSalReviewAll(param);
		
		int totalSalReviewCnt = ss.getTotalSalReviewCnt();
		String url = request.getRequestURI();
		String salPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalSalReviewCnt, url);
		
		request.setAttribute("salList", salList);
		request.setAttribute("salPagebar", salPagebar);
		
		
		response.sendRedirect(request.getHeader("Referer"));
		
	}

}
