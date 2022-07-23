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
import com.kh.zoomin.member.dto.Member;

/**
 * Servlet implementation class SalaryReviewViewServlet
 */
@WebServlet("/review/salary/salaryReviewBoard")
public class SalaryReviewViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SalaryReviewService salaryReviewService = new SalaryReviewService();  
    
	/**
	 * 연봉 리뷰 상세보기
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		SalaryReview salaryReview = salaryReviewService.findBySalaryReviewNo(no);
		System.out.println("salaryReview 상세보기" + salaryReview);
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		boolean isApplicant = false;
		if(loginMember != null && loginMember.getMemberType() == 2) {
			isApplicant = true;
		}
		
		if(isApplicant) {
			int uid = ((ApplicantMember) loginMember).getUid();
		}
				
		request.setAttribute("salaryReview", salaryReview);
		request.getRequestDispatcher("/WEB-INF/views/applicant/salaryReviewView.jsp")
			.forward(request, response);
		
	}

}
