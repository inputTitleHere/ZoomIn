package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt;
import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;

/**
 * Servlet implementation class CompanyReviewUpdateServlet
 */
@WebServlet("/review/company/companyReviewUpdate")
public class CompanyReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			int uid = 5;
			CompanyReview companyReview = companyReviewService.findByCompanyReviewNo(no);
			
			request.setAttribute("companyReview", companyReview);
			request.getRequestDispatcher("/WEB-INF/views/applicant/companyReviewUpdate.jsp")
				.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			String content = request.getParameter("content");
			int stars = Integer.parseInt(request.getParameter("stars"));
			int workLifeBalance = Integer.parseInt(request.getParameter("workLifeBalance"));
			int levelUp = Integer.parseInt(request.getParameter("levelUp"));
			int workIntensity = Integer.parseInt(request.getParameter("workIntensity"));
			int potential = Integer.parseInt(request.getParameter("potential"));
			int salarySatisfaction = Integer.parseInt(request.getParameter("salarySatisfaction"));
			CompanyReviewExt companyReview = new CompanyReviewExt(salarySatisfaction, content, no, content, stars, workLifeBalance, levelUp, workIntensity, potential, salarySatisfaction, null);
			
			System.out.println("companyReview = " + companyReview);
			
			int result = companyReviewService.updateCompanyReview(companyReview);
			
			request.getSession().setAttribute("msg", "리뷰를 성공적으로 수정했습니다.");
			response.sendRedirect(request.getContextPath() + "/review/company/companyReviewUpdate?no=" + no);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	} 

}
