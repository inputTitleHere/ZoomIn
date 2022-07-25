package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt;
import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.member.dto.Member;

/**
 * Servlet implementation class CompanyEnrollServlet
 */
@WebServlet("/review/company/companyReviewEnroll")
public class CompanyReviewEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		String companyNo = request.getParameter("companyNo");
		
		request.setAttribute("uid", uid);
		request.setAttribute("companyNo", companyNo);
		
		request.getRequestDispatcher("/WEB-INF/views/applicant/companyReviewEnroll.jsp")
			.forward(request, response);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			CompanyReview companyReview = new CompanyReview();

			/*
			 * if(member instanceof ApplicantMember) { ApplicantMember applicantMember =
			 * (ApplicantMember) member; }
			 */
			int uid = Integer.parseInt(request.getParameter("uid"));
			String companyNo = request.getParameter("companyNo");
			int categoryNumber = Integer.parseInt(request.getParameter("category_number"));
			String content = request.getParameter("content");
			int stars = Integer.parseInt(request.getParameter("stars"));
			int workLifeBalance = Integer.parseInt(request.getParameter("work_life_balance"));
			int levelUp = Integer.parseInt(request.getParameter("level_up"));
			int workIntensity = Integer.parseInt(request.getParameter("work_intensity"));
			int potential = Integer.parseInt(request.getParameter("potential"));
			int salarySatisfaction = Integer.parseInt(request.getParameter("salary_satisfaction"));
			companyReview = new CompanyReview(0, uid, companyNo, categoryNumber, content, stars, workLifeBalance, levelUp, workIntensity, potential, salarySatisfaction, null);
			System.out.println("companyReviewEnroll = " + companyReview);
			/*
			 * companyReview.setUid(uid); companyReview.setCompanyNo(companyNo);
			 * companyReview.setCategoryNumber(categoryNumber);
			 * companyReview.setContent(content); companyReview.setStars(stars);
			 * companyReview.setWorkLifeBalance(workLifeBalance);
			 * companyReview.setLevelUp(levelUp);
			 * companyReview.setWorkIntensity(workIntensity);
			 * companyReview.setPotential(potential);
			 * companyReview.setSalarySatisfaction(salarySatisfaction);
			 */
			
			System.out.println("companyReviewEnroll = " + companyReview);
			// 업무로직
			int result = companyReviewService.insertCompanyReview(companyReview);
			
			// redirect
			HttpSession session = request.getSession();
			request.getSession().setAttribute("msg", "게시글을 성공적으로 등록했습니다.");
			request.setAttribute("uid", uid);
			request.setAttribute("companyNo", companyNo);
			request.setAttribute("companyReview", companyReview);
			response.sendRedirect(request.getContextPath() + "/recruit/review/recruitReviewList?companyNo=" + companyNo);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
