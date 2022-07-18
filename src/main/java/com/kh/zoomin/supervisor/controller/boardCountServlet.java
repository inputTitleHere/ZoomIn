package com.kh.zoomin.supervisor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class boardCountServlet
 */
@WebServlet("/supervisor/countBoard")
public class boardCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService supervisorService = new SupervisorService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업무로직 : 오늘 업데이트된 게시글 수 
		int todayComCnt = supervisorService.getTodayComCnt();	//select count(*) from company_review where reg_date = sysdate
		int todaySalCnt = supervisorService.getTodaySalCnt();	//select count(*) from salary_review where reg_date = sysdate
		
		//전체 게시판 게시글 수 
		int totalBoardCnt = supervisorService.getTotalBoardCnt();
		
		//응답처리
		HttpSession session = request.getSession();
		session.setAttribute("todayComCnt", todayComCnt);
		session.setAttribute("todayComCnt", todaySalCnt);
		session.setAttribute("totalBoardCnt", totalBoardCnt);
		request.getRequestDispatcher("/WEB-INF/views/supervisor/supervisorIndex.jsp").forward(request, response);
	}

}
