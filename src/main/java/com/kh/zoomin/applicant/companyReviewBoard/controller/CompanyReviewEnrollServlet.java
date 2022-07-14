package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.companyReviewBoard.Service.CompanyReviewService;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;

/**
 * Servlet implementation class CompanyEnrollServlet
 */
@WebServlet("/CompanyReviewEnrollServlet")
public class CompanyReviewEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/applicant/companyReviewEnroll.jsp")
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
			
			String content = request.getParameter("content");
			int stars = Integer.parseInt(request.getParameter("stars"));
			int workLifeBalance = Integer.parseInt(request.getParameter("workLifeBalance"));
			int levelUp = Integer.parseInt(request.getParameter("levelUp"));
			int workIntensity = Integer.parseInt(request.getParameter("workIntensity"));
			int potential = Integer.parseInt(request.getParameter("potential"));
			int salarySatisfaction = Integer.parseInt(request.getParameter("salarySatisfaction"));
			
			
			CompanyReview companyReview = new CompanyReview(uid, companyNo, categoryNumber, content, stars, workLifeBalance, levelUp, workIntensity, potential, salarySatisfaction, null);
			
			// 업무로직
			int result = companyReviewService.insertCompanyReview(companyReview);
			
			// redirect
			request.getSession().setAttribute("msg", "게시글을 성공적으로 등록했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
