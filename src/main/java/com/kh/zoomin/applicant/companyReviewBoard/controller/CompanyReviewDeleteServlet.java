package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;

/**
 * Servlet implementation class CompanyReviewDeleteServlet
 */
@WebServlet("/CompanyReviewDeleteServlet")
public class CompanyReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = companyReviewService.deleteCompanyReview(no);
			
			request.getSession().setAttribute("msg", "리뷰를 성공적으로 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "CompanyReviewViewServlet");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
