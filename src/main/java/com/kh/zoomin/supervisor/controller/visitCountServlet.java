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
 * Servlet implementation class visitCountServlet
 */
@WebServlet("/supervisor/visitMember")
public class visitCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService supervisorService = new SupervisorService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업무로직		
		HttpSession session = request.getSession();
		
		//총 방문자 조회
		int totalCount = supervisorService.getTotalCount();
		System.out.println("totalCnt = " + totalCount);
		
		//오늘 방문자 조회
		int todayCount = supervisorService.getTodayCount();	
		System.out.println("todayCount = " + todayCount);
		
		//응답처리 리다이렉트
		session.setAttribute("totalCount", totalCount);	
		session.setAttribute("todayCount", todayCount);	
		request.getRequestDispatcher("/WEB-INF/views/supervisor/supervisorIndex.jsp").forward(request, response);
	}

}
