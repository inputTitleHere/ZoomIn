package com.kh.zoomin.applicant.information.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/ApplicantInfoServlet")
public class ApplicantInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩처리
		request.setCharacterEncoding("utf-8");
		
		// 응답처리

		request.getRequestDispatcher("/WEB-INF/views/applicant/applicant.jsp")
			.forward(request, response);
	}
	
	
}
