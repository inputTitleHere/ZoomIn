package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class amWritingView
 */
@WebServlet("/supervisor/amWriting")
public class amWritingView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값
		
		//업무로직
		
		//응답처리(팝업창)
		request.getRequestDispatcher("/WEB-INF/views/supervisor/amWriting.jsp").forward(request, response);
	}

}
