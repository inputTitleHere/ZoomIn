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
			ApplicantMember amember = as.findAppliId(id);
			System.out.println("amamber= " + amember);
			HttpSession session = request.getSession();
			
			//로그인 여부
			String msg = null;
			String path = null;
			
			if(amember != null && password.equals(amember.getPassword())) {
				//세션 객체 인스턴스
//				session.setAttribute("id", id);
				session.setAttribute("loginMember", amember); //객체저장 
				msg = "로그인 성공입니다.";
				path = "/zoomin";
				
				
			} else {
				msg = "아이디 또는 비밀번호가 일치하지 않습니다.";
//				session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				request.getRequestDispatcher("/WEB-INF/views/common/applicantLogin.jsp").forward(request, response);
//				path = request.getContextPath() + "/applicant/login";
				return;
			}

			//3. 리다이렉트
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(path);
//			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/common/applicantLogin.jsp").forward(request, response);
    }
	

}
