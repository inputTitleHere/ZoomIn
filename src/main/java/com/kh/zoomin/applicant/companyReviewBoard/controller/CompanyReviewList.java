package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;
import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.member.dto.Member;

/**
 * Servlet implementation class CompanyReviewList
 */
@WebServlet("/review/company/companyReviewList")

public class CompanyReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("loginMember");
			
			int numPerPage = 5;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {}
			
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			
			List<CompanyReview> list = companyReviewService.findAll(param);
			System.out.println("list = " + list);
			
			int totalContent = companyReviewService.getTotalContent();
			String url = request.getRequestURI(); 
			String pagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalContent, url);
			
			// view
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/applicant/companyReviewList.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
