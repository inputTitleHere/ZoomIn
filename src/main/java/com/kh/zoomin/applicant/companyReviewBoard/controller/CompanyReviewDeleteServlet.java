package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.member.dto.Member;

/**
 * Servlet implementation class CompanyReviewDeleteServlet
 */
@WebServlet("/review/company/companyReviewDelete")
public class CompanyReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("loginMember");
			if(member instanceof ApplicantMember) {
				ApplicantMember applicantMember = (ApplicantMember) member;
			}
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("companyReviewNo = " + no);
			
			int result = companyReviewService.deleteCompanyReview(no);
			
			CompanyReview companyReview = new CompanyReview();
			
			String companyNo = request.getParameter("companyNo");
			request.getSession().setAttribute("msg", "리뷰를 성공적으로 삭제했습니다.");
//			response.sendRedirect(request.getContextPath() + "/recruit/review/recruitReviewList?companyNo=" + companyNo);
			response.sendRedirect(request.getContextPath() + "/");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
