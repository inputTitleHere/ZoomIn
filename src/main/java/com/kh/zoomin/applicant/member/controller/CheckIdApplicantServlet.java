package com.kh.zoomin.applicant.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.member.model.service.ApplicantService;

/**
 * Servlet implementation class CheckIdApplicantServlet
 */
@WebServlet("/applicant/checkId")
public class CheckIdApplicantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicantService ac = new ApplicantService();
       
    /**
     * 아이디 중복검사
     * applicant_member 테이블 객체 확인/반환 -> 존재한다면 아이디 사용불가함
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		//사용자입력값 확인용
		System.out.println("id = " + id);
		
		ApplicantMember amember = ac.findAppliId(id);
		boolean result = amember == null; 
		RequestDispatcher check = request.getRequestDispatcher("/WEB-INF/views/common/checkIdApplicant.jsp");
		
		//이 서블릿에선 jsp페이지로 값을 보내기만 할것. jsp페이지에서 id값 사용할 수 있도록 해줌
		//checkId = true, false. 
		request.setAttribute("checkId", result);
		request.setAttribute("id", id);
		check.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
