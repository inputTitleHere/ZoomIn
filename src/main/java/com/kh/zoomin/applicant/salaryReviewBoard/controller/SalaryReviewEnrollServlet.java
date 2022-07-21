package com.kh.zoomin.applicant.salaryReviewBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview;
import com.kh.zoomin.applicant.salaryReviewBoard.model.service.SalaryReviewService;

/**
 * Servlet implementation class SalaryReviewEnrollServlet
 */
@WebServlet("/review/salary/salaryReviewEnroll")
public class SalaryReviewEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalaryReviewService salaryReviewService = new SalaryReviewService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/applicant/salaryReviewEnroll.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			SalaryReview salaryReview = new SalaryReview();
			HttpSession loginSession = request.getSession();
			ApplicantMember am = (ApplicantMember) loginSession.getAttribute("loginMember");
			
			// 사용자 입력값 처리
			int uid = Integer.parseInt(request.getParameter("uid"));
			String companyNo = request.getParameter("company_no");
			int categoryNumber = Integer.parseInt(request.getParameter("category_number"));
			int salary = Integer.parseInt(request.getParameter("salary"));
			int workYear = Integer.parseInt(request.getParameter("work_year"));
			String jobPosition = request.getParameter("job_position");
			
			salaryReview = new SalaryReview(uid, companyNo, categoryNumber, salary, workYear, jobPosition, null);
			System.out.println("salaryReview = " + salaryReview);
			
			// 업무로직
			int result = salaryReviewService.insertSalaryReviewService(salaryReview);
			
			// redirect
			request.getSession().setAttribute("msg", "게시글을 성공적으로 등록했습니다.");
			response.sendRedirect(request.getContextPath() + "/review/salary/salaryReviewList");
						
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
