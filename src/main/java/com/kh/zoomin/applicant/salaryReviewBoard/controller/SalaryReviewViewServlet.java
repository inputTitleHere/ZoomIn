package com.kh.zoomin.applicant.salaryReviewBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview;
import com.kh.zoomin.applicant.salaryReviewBoard.model.service.SalaryReviewService;

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
		
		request.setAttribute("salaryReview", salaryReview);
		request.getRequestDispatcher("/WEB-INF/views/applicant/salaryReviewView.jsp")
			.forward(request, response);
		
	}

}
