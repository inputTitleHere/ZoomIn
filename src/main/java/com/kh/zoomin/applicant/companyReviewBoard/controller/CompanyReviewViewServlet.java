package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;
import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;

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
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			
			CompanyReview companyReview = companyReviewService.findByCompanyReviewNo(no);
			System.out.println("companyReview = " + companyReview);
		
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
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
