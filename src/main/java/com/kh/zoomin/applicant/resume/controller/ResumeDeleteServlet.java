package com.kh.zoomin.applicant.resume.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.resume.model.dto.Gender;
import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.applicant.resume.model.dto.SchoolType;
import com.kh.zoomin.applicant.resume.model.dto.Status;
import com.kh.zoomin.applicant.resume.model.service.ResumeService;

/**
 * Servlet implementation class ResumeDeleteServlet
 */
@WebServlet("/ResumeDeleteServlet")
public class ResumeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResumeService ResumeService = new ResumeService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession loginSession = request.getSession();
		ApplicantMember member = (ApplicantMember)loginSession.getAttribute("loginMember");
		// 김지윤님 applicantMember 수정후에 Integer.parseInt 제거 할것!
//		int uid = Integer.parseInt(member.getUid()) ; 로그인 기능 구현후 uid 수정
		int uid = 21;
		try {
			//인코딩처리
			request.setCharacterEncoding("utf-8");
			//2.업무로직 처리
			int result = ResumeService.deleteResume(uid);
			
			//3.응답 리다이렉트
			
			HttpSession session = request.getSession();
			String msg = "";
			
			if(result > 0) {
				msg = "이력서 성공적으로 등록!";
				session.setAttribute("msg", msg);
			}
			//현재 페이지 리다이렉트
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
