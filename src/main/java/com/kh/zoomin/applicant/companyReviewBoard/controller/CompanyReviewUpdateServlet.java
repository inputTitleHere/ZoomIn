package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * 수정 폼 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
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
	 * update 요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		CompanyReview companyReview = new CompanyReview();
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			int uid = Integer.parseInt(request.getParameter("uid"));
			String companyNo = request.getParameter("company_no");
			int categoryNumber = Integer.parseInt(request.getParameter("category_number"));
			String content = request.getParameter("content");
			int stars = Integer.parseInt(request.getParameter("stars"));
			int workLifeBalance = Integer.parseInt(request.getParameter("work_life_balance"));
			int levelUp = Integer.parseInt(request.getParameter("level_up"));
			int workIntensity = Integer.parseInt(request.getParameter("work_intensity"));
			int potential = Integer.parseInt(request.getParameter("potential"));
			int salarySatisfaction = Integer.parseInt(request.getParameter("salary_satisfaction"));
			CompanyReviewExt companyReview = new CompanyReviewExt(no, uid, companyNo, categoryNumber, content, stars, workLifeBalance, 
					levelUp, workIntensity, potential, salarySatisfaction, null);
			
//			companyReview.setNo(no);
//			companyReview.setUid(uid);
//			companyReview.setCompanyNo(companyNo);
//			companyReview.setCategoryNumber(categoryNumber);
//			companyReview.setCompanyNo(content);
//			companyReview.setStars(stars);
//			companyReview.setWorkLifeBalance(workLifeBalance);
//			companyReview.setLevelUp(levelUp);
//			companyReview.setWorkIntensity(workIntensity);
//			companyReview.setPotential(potential);
//			companyReview.setSalarySatisfaction(salarySatisfaction);

			System.out.println("companyReviewUpdate = " + companyReview);
			
			int result = companyReviewService.updateCompanyReview(companyReview);
			
			request.getSession().setAttribute("msg", "리뷰를 성공적으로 수정했습니다.");
			response.sendRedirect(request.getContextPath() + "/review/company/companyReviewBoard?no=" + no);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	} 

}
