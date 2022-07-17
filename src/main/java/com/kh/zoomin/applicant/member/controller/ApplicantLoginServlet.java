package com.kh.zoomin.applicant.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			
			//1. 사용자입력값 처리
			String memberId = request.getParameter("memberId");
			String password = request.getParameter("password");
			String saveId = request.getParameter("saveId");
			System.out.println("memberId = " + memberId);
			System.out.println("password = " + password);
			System.out.println("saveId = " + saveId);
					
			//2. 업무로직/로그인 여부 판단
			ApplicantMember amember = as.findById(memberId);
			System.out.println("applicant@ApplicantLoginServlet = " + amember);

			HttpSession session = request.getSession(true); 
			System.out.println(session.getId());
			
			//로그인 성공
			if(amember != null && password.equals(amember.getPassword())) {
				session.setAttribute("loginMember", amember);
				
				//saveId처리
				Cookie cookie = new Cookie("saveId", memberId);
				cookie.setPath(request.getContextPath()); 
				
				if(saveId != null) {
					cookie.setMaxAge(7 * 24 * 60 * 60);
				}
				else {
					cookie.setMaxAge(0); //삭제
				}
				response.addCookie(cookie);
				
			}
			else {
				session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			
			//3. 리다이렉트
			String location = request.getHeader("Referer");
			response.sendRedirect(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
