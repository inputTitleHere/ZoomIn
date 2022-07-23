package com.kh.zoomin.applicant.salaryReviewBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.salaryReviewBoard.model.service.SalaryReviewService;

/**
 * Servlet implementation class SalaryReviewDeleteServlet
 */
@WebServlet("/review/salary/salaryReviewDelete")
public class SalaryReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalaryReviewService salaryReviewService = new SalaryReviewService();
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("지울 salaryReviewNo = " + no);
			
			int result = salaryReviewService.deleteSalaryReview(no);
			
			request.getSession().setAttribute("msg", "리뷰를 성공적으로 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
