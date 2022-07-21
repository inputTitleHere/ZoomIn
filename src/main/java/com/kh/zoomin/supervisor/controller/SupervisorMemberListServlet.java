package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.recruit.member.RecruitMember;
import com.kh.zoomin.supervisor.model.service.SupervisorService;


/**
 * Servlet implementation class SupervisorMemberListServlet
 */
@WebServlet("/supervisor/memberList")
public class SupervisorMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService ss = new SupervisorService();
	
	/**
	 * 관리자 : 회원 전체 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			//업무로직 : 회원조회 (일단 구인자 구직자 따로 조회해서 전체 띄우기, 페이징은 나중에 만들기)
//			List<ApplicantMember> applicantMemberList = new ArrayList<>();
//			List<RecruitMember> recruitMemberList= new ArrayList<>();
//			
//			applicantMemberList = ss.findApplicantMemberAll();
//			recruitMemberList = ss.findRecruitMemberAll();
//			
//			System.out.println("applicantMemeberList = " + applicantMemberList);
//			System.out.println("recuitMemeberList = " + recruitMemberList);
//			
//			
//			//응답처리
//			request.setAttribute("applicantMemebrList", applicantMemberList);
//			request.setAttribute("recruitMemebrList", recruitMemberList);
//			request.getRequestDispatcher("/WEB-INF/views/supervisor/memberList.jsp").forward(request, response);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
		
		request.getRequestDispatcher("/WEB-INF/views/supervisor/memberList.jsp").forward(request, response);
	}

}
