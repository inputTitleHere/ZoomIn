package com.kh.zoomin.applicant.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.member.model.dao.ApplicantDao;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.member.model.service.ApplicantService;

/**
 * Servlet implementation class ApplicantLoginServlet
 */
@WebServlet("/applicant/login")
public class ApplicantLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicantService as= new ApplicantService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//인코딩처리
			request.setCharacterEncoding("utf-8");
			
			//1. 사용자입력값 처리 (아이디, 비번) 폼입력값
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			System.out.println("id = " + id);
			System.out.println("password = " + password);
			System.out.println(id + "," + password);
			
			//2. 업무로직
			ApplicantMember amember = as.findAppliId(id, password);
			System.out.println("amamber= " + amember);
			
			//로그인 여부
			String message = null;
			
			if(amember != null) {
				message = amember + "님, 환영합니다.";
				
				//세션 객체 인스턴스
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				session.setAttribute("msg", message);
				session.setAttribute("loginMember", amember); //객체저장 
			} else {
				message = "아이디 또는 비밀번호가 일치하지 않습니다.";
				request.getRequestDispatcher("/WEB-INF/views/common/applicantLogin.jsp").forward(request, response);
			}

			//3. 리다이렉트
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/common/applicantLogin.jsp").forward(request, response);
    }
	

}
