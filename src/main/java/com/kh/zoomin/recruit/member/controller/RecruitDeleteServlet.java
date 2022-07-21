package com.kh.zoomin.recruit.member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.recruit.member.model.service.RecruitService;

/**
 * Servlet implementation class RecruitDeleteServlet
 */
@WebServlet("/recruit/delete")
public class RecruitDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitService rs = new RecruitService();


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값
			String id = request.getParameter("id");
			
			int result = rs.deleteRecruiter(id);
			HttpSession session = request.getSession();
			Enumeration<String> names = session.getAttributeNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				session.removeAttribute(name);
			}
			
			session.setAttribute("msg", "회원을 성공적으로 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		
	}

}
