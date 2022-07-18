package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.recruit.member.RecruitMember;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class SupervisorMemberFinderServlet
 */
@WebServlet("/supervisor/memberFinder")
public class SupervisorMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService supervisorService = new SupervisorService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//사용자 입력값 처리
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchkeyword");
			Map<String, Object> param = new HashMap<>();
			List<ApplicantMember> applicantMemberList = new ArrayList<>();
			List<RecruitMember> recruitMemberList = new ArrayList<>();
	
			//업무로직
			if(searchType.contains("applicant")) {
				//select * from applicantMember where # like ?
				param.put("searchType", searchType);
				param.put("searchKeyword", searchKeyword);
				applicantMemberList = supervisorService.findApplicantMemberLike(param);
				System.out.println("applicantMemberList = " + applicantMemberList);
			}
			else if(searchType.contains("recruit")){
				//select * from recruitMember where # like ?
				param.put("searchType", searchType);
				param.put("searchKeyword", searchKeyword);
				recruitMemberList = supervisorService.findRecruitMemberLike(param);
				System.out.println("recruitMemberList = " + recruitMemberList);
			}
			
			//응답처리 (setting도 분기해야 하나...?)
			request.setAttribute("applicantMemberList", applicantMemberList);
			request.setAttribute("recruitMemberList", recruitMemberList);
			request.getRequestDispatcher("/WEB-INF/views/supervisor/memberList.jsp").forward(request, response);
	
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
