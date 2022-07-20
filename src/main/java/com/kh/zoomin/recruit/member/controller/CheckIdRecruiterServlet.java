package com.kh.zoomin.recruit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.recruit.member.RecruitMember;
import com.kh.zoomin.recruit.member.model.service.RecruitService;

/**
 * Servlet implementation class CheckIdRecruiterServlet
 */
@WebServlet("/recruit/checkId")
public class CheckIdRecruiterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitService rcs = new RecruitService();
       
	/**
	 * 아이디 중복검사 서블릿 시작 
	 * 
	 */
	//form형식이 아니므로 get으로 받고 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		
		//인코딩 후 사용자입력값 확인용
		System.out.println("id = " + id);
		
		//중복체크는 .jsp에서 진행예정 서블릿에선 jsp페이지로 입력값을 전달. 
		RecruitMember rmember = rcs.findrecruId(id);
		boolean result = rmember == null;
		RequestDispatcher check = request.getRequestDispatcher("/WEB-INF/views/common/checkIdRecruiter.jsp");
		
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
