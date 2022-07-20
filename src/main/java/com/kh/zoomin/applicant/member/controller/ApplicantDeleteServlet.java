package com.kh.zoomin.applicant.member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.member.model.service.ApplicantService;

/**
 * Servlet implementation class ApplicantDeleteServlet
 */
@WebServlet("/applicant/delete")
public class ApplicantDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicantService as = new ApplicantService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자입력값
			String id = request.getParameter("id");
			
			//2. 서비스단으로 연결. Enumeration 인터페이스로 값을 갖고오기로.
			int result = as.deleteMember(id);
			HttpSession session = request.getSession();
			Enumeration<String> names = session.getAttributeNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				session.removeAttribute(name);
			}
			
			//3. 리다이렉트 
			session.setAttribute("msg", "성공적으로 탈퇴되었습니다.");
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
