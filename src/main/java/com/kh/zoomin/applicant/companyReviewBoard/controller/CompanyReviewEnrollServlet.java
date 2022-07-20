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
//		int no = Integer.parseInt(request.getParameter("no"));
//		int uid = Integer.parseInt(request.getParameter("uid"));
		// 로그인 된 사람이 없어서 현재 위 두개를 안받고 47번 줄에서 저렇게 설정을 해줘야하나?
		
		HttpSession loginSession = request.getSession();
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
			HttpSession loginSession = request.getSession();
			ApplicantMember member = (ApplicantMember)loginSession.getAttribute("loginMember");
			// 사용자 입력값 처리
			
			int uid = Integer.parseInt(request.getParameter("uid"));
			uid = 5;
			String companyNo = request.getParameter("company_no");
			int categoryNumber = Integer.parseInt(request.getParameter("category_number"));
			
			String content = request.getParameter("content");
			int stars = Integer.parseInt(request.getParameter("stars"));
			int workLifeBalance = Integer.parseInt(request.getParameter("work_life_balance"));
			int levelUp = Integer.parseInt(request.getParameter("level_up"));
			int workIntensity = Integer.parseInt(request.getParameter("work_intensity"));
			int potential = Integer.parseInt(request.getParameter("potential"));
			int salarySatisfaction = Integer.parseInt(request.getParameter("salary_satisfaction"));
			companyReview = new CompanyReviewExt(uid, companyNo, categoryNumber, content, stars, workLifeBalance, levelUp, workIntensity, potential, salarySatisfaction, null);
			
			companyReview.setUid(uid);
			companyReview.setCompanyNo(companyNo);
			companyReview.setCategoryNumber(categoryNumber);
			companyReview.setContent(content);
			companyReview.setStars(stars);
			companyReview.setWorkLifeBalance(workLifeBalance);
			companyReview.setLevelUp(levelUp);
			companyReview.setWorkIntensity(workIntensity);
			companyReview.setPotential(potential);
			companyReview.setSalarySatisfaction(salarySatisfaction);
			
			System.out.println("companyReview = " + companyReview);
			// 업무로직
			int result = companyReviewService.insertCompanyReview(companyReview);
			
			// redirect
			request.getSession().setAttribute("msg", "게시글을 성공적으로 등록했습니다.");
			
			response.sendRedirect(request.getContextPath() + "/review/company/companyReviewList");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
