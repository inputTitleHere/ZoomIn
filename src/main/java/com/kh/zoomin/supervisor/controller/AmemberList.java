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
import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.recruit.member.model.dto.RecruitMember;
import com.kh.zoomin.supervisor.model.dto.Rmember;
import com.kh.zoomin.supervisor.model.service.SupervisorService;


/**
 * Servlet implementation class SupervisorMemberListServlet
 */
@WebServlet("/supervisor/aMemberList")
public class AmemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService ss = new SupervisorService();
	
	/**
	 * 관리자 : 회원 전체 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//사용자 입력값
			int cPage = 1;
			int numPerPage = 5;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			}	catch(NumberFormatException e) {}
			
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			
			//업무로직 : 회원조회 
				//A.content영역
			//회원조회
			List<Rmember> recruitMemberList= ss.findRecruitMemberAll(param);
			List<ApplicantMember> applicantMemberList = ss.findApplicantMemberAll(param);
			
//			for(ApplicantMember am : applicantMemberList) {
//				int amUid = am.getUid();
//				int salCnt = ss.getRmwritingCnt(amUid);				
//			}
			
				//B.pagebar영역
			int totalAmCnt = ss.getTotalAmCnt();
			int totalRmCnt = ss.getTotalRmCnt();
			
			String url = request.getRequestURI();
			String amPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalAmCnt, url);
			String rmPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalRmCnt, url);
			
			//응답처리
			request.setAttribute("applicantMemebrList", applicantMemberList);
			request.setAttribute("amPagebar", amPagebar);
			request.setAttribute("recruitMemebrList", recruitMemberList);
			request.setAttribute("rmPagebar", rmPagebar);
			request.getRequestDispatcher("/WEB-INF/views/supervisor/AmemberList.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
