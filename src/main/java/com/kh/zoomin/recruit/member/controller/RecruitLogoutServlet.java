package com.kh.zoomin.recruit.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RecruitLogoutServlet
 */
@WebServlet("/recruit/logout")
public class RecruitLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 무효화처리. 세션객체가 없을 경우 무효화할것
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();

		response.sendRedirect(request.getContextPath() + "/");
	}

}
