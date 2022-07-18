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
			ApplicantDao ad = new ApplicantDao();
			ApplicantMember name = ad.findAppliId(id, password);
			System.out.println("이름 : " + name);
			
			//로그인 여부
			String message = new String();
			String page = new String();
			
			if(name != null) {
				message = name + "님, 환영합니다.";
				page = "/common/index.jsp";
				
				//세션 객체 인스턴스
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				session.setAttribute("message", message);
			} else {
				message = "아이디 또는 비밀번호가 일치하지 않습니다.";
				page = "/common/header.jsp?message="+ URLEncoder.encode(message, "utf-8");
				
				response.sendRedirect(request.getContextPath() + page);
			}
				
				
				//로그인 페이지로 백
//			ApplicantMember amember = as.findAppliId(id, password);
//			System.out.println("amember@MemberLoginServlet = " + amember);
//			HttpSession session = request.getSession(true); 
//			System.out.println(session.getId());
//			
//			//로그인 여부 판단. 로그인 성공
//			if(amember != null && password.equals(amember.getPassword())) {
//				session.setAttribute("loginMember", amember);
				
//			}
//			else {
//				//로그인 실패
//				session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
//				response.sendRedirect("applicantLogin.jsp"); //로그인 페이지
//			}
			
			//3. 리다이렉트
			String location = request.getHeader("index.jsp"); //
			response.sendRedirect(location); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//		} else {
//			//로그인 실패시 (이게 되면 로그인 실패 jsp 만들기..) 
//			RequestDispatcher location = request.getRequestDispatcher("/views/common/applicantLogin.jsp");
//			
//			location.forward(request, response);
//		}
//		
//		
//
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/common/applicantLogin.jsp").forward(request, response);
    }
	

}
