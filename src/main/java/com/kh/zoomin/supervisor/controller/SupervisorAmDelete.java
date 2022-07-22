package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class SupervisorAmDelete
 */
@WebServlet("/supervisor/applicantMemberDelete")
public class SupervisorAmDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService ss = new SupervisorService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값 처리
		String[] amUid = request.getParameterValues("chk"); 

		//업무로직 
		int result = ss.deleteAmember(amUid);
		//System.out.println("result = " + result);
		System.out.println("result = " + result);
		//응답처리 
		response.sendRedirect(request.getContextPath() + "/supervisor/memberList");
	}

}
