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
import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;
import com.kh.zoomin.member.dto.Member;

/**
 * Servlet implementation class CompanyReviewViewServlet
 */
@WebServlet("/review/company/companyReviewBoard")
public class CompanyReviewViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();
	private CompanyService companyService = new CompanyService();
	/**
	 * 리뷰 상세보기
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			int no = Integer.parseInt(request.getParameter("no"));
			
			CompanyReview companyReview = companyReviewService.findByCompanyReviewNo(no);
			System.out.println("companyReview 상세보기 " + companyReview);
			
			HttpSession session = request.getSession(); 
			Member loginMember = (Member) session.getAttribute("loginMember");
			boolean isApplicant = false;
			if(loginMember != null && loginMember.getMemberType() == 2) {
				isApplicant = true;
			}
			
			if(isApplicant) {
				int uid = ((ApplicantMember) loginMember).getUid();
			}
		
			String companyNo = companyReview.getCompanyNo();
			Company company = companyService.getCompanyByNo(companyNo);

			//xss 공격대비
			companyReview.setContent(ZoominUtils.escapeXml(companyReview.getContent()));
			// 개행문자 변환처리
			companyReview.setContent(ZoominUtils.convertLineFeedToBr(companyReview.getContent()));
			
			request.setAttribute("company", company);
			request.setAttribute("companyReview", companyReview);
			request.getRequestDispatcher("/WEB-INF/views/applicant/companyReviewView.jsp")
				.forward(request, response);
			
		
	}

}
