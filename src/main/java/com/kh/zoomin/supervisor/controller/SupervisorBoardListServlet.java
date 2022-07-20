package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.supervisor.model.dto.SalaryReview;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class SupervisorBoardListServlet
 */
@WebServlet("/supervisor/BoardList")
public class SupervisorBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService ss = new SupervisorService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업무로직 (전체조회) 
		//연봉게시판
		List<SalaryReview> salList = ss.getSalReviewAll();
		//System.out.println("salList = " + salList);
		
		//응답처리
		request.setAttribute("salList", salList);
		request.getRequestDispatcher("/WEB-INF/views/supervisor/boardList.jsp").forward(request, response);
	}

}
