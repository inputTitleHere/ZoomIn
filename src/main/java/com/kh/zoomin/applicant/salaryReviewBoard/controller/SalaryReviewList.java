package com.kh.zoomin.applicant.salaryReviewBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview;
import com.kh.zoomin.applicant.salaryReviewBoard.model.service.SalaryReviewService;
import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;
import com.kh.zoomin.member.dto.Member;

/**
 * Servlet implementation class SalaryReivewList
 */
@WebServlet("/review/salary/salaryReviewList")
public class SalaryReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalaryReviewService salaryReviewService = new SalaryReviewService();
	private CompanyService companyService = new CompanyService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("loginMember");
			if(member instanceof ApplicantMember) {
				ApplicantMember applicantMember = (ApplicantMember) member;
				int uid = applicantMember.getUid();
			
			
//				int result = salaryReviewService.
				
			}
			
			int numPerPage = 5;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {}
			
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			
			List<SalaryReview> list = salaryReviewService.findAll(param);
			
			int totalContent = salaryReviewService.getTotalContent();
			String url = request.getRequestURI();
			String pagebar = ZoominUtils.getApplicantPageBar(cPage, numPerPage, totalContent, url);
			
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/applicant/salaryReviewList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
