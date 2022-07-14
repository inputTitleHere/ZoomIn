package com.kh.zoomin.applicant.salaryReviewBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.salaryReviewBoard.model.service.SalaryReviewService;

/**
 * Servlet implementation class SalaryReviewEnrollServlet
 */
@WebServlet("/SalaryReviewEnrollServlet")
public class SalaryReviewEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalaryReviewService srs = new SalaryReviewService();
	
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
			
		} catch (Exception e) {
			
		}
	}

}
