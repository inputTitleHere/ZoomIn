package com.kh.zoomin.applicant.salaryReviewBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview;
import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReviewExt;
import com.kh.zoomin.applicant.salaryReviewBoard.model.service.SalaryReviewService;

/**
 * 수정
 */
@WebServlet("/review/salary/salaryReviewUpdate")
public class SalaryReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalaryReviewService salaryReviewService = new SalaryReviewService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			SalaryReview salaryReview = salaryReviewService.findBySalaryReviewNo(no);
			
			request.setAttribute("salaryReview", salaryReview);
			request.getRequestDispatcher("/WEB-INF/views/applicant/salaryReviewUpdate.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		SalaryReview salaryReivew = new SalaryReview();
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			int uid = Integer.parseInt(request.getParameter("uid"));
			String companyNo = request.getParameter("company_no");
			int categoryNumber = Integer.parseInt(request.getParameter("category_number"));
			int salary = Integer.parseInt(request.getParameter("salary"));
			int workYear = Integer.parseInt(request.getParameter("work_year"));
			String jobPosition = request.getParameter("job_position");
			SalaryReviewExt salaryReview = new SalaryReviewExt(no, uid, companyNo, categoryNumber, salary, workYear, jobPosition, null);
			
			System.out.println("salaryReviewUpdate = " + salaryReview);
			
			int result = salaryReviewService.updateSalaryReview(salaryReview);
			HttpSession session =request.getSession();
			session.setAttribute("msg", "리뷰를 성공적으로 수정했습니다.");
			response.sendRedirect(request.getContextPath() + "/review/salary/salaryReviewBoard?no=" + no);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
