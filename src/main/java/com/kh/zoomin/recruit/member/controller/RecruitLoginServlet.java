package com.kh.zoomin.recruit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.recruit.member.RecruitMember;
import com.kh.zoomin.recruit.member.model.service.RecruitService;

/**
 * Servlet implementation class RecruitLoginServlet
 */
@WebServlet("/recruit/login")
public class RecruitLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitService res = new RecruitService();
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//인코딩처리
			request.setCharacterEncoding("utf-8");
			
			//1. 사용자입력값 처리 (아이디, 비번) 
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			//2. 업무로직
			RecruitMember rmember = res.findById(id);
			HttpSession session = request.getSession(true); 
			System.out.println(session.getId());
			
			//로그인 여부 판단. 로그인 성공
			if(rmember != null && password.equals(rmember.getPassword())) {
				session.setAttribute("loginMember", rmember);
			}
			else {
				//로그인 실패
				session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			
			//3. 리다이렉트
			String location = request.getHeader("Referer"); //현재 페이지 오기전 url정보
			//수정필요한 부분 
			response.sendRedirect(location); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}