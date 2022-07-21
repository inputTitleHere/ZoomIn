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
 * Servlet implementation class SalaryReviewEnrollServlet
 */
@WebServlet("/SalaryReviewEnrollServlet")
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
			
			// 사용자 입력값 처리

			int uid = Integer.parseInt(request.getParameter("uid"));
			String companyNo = request.getParameter("companyNo");
			int categoryNumber = Integer.parseInt(request.getParameter("categoryNumber"));

			int salary = Integer.parseInt(request.getParameter("salary"));
			int workYear = Integer.parseInt(request.getParameter("workYear"));
			String jobPosition = request.getParameter("jobPosition");
			
			SalaryReview salaryReview = new SalaryReview(uid, companyNo, categoryNumber, salary, workYear, jobPosition, null);
			
			// 업무로직
			int result = salaryReviewService.insertSalaryReviewService(salaryReview);
			
			// redirect
			request.getSession().setAttribute("msg", "게시글을 성공적으로 등록했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
						
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
