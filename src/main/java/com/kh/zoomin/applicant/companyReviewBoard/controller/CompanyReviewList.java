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

import com.kh.mvc.common.HelloMvcUtils;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;

/**
 * Servlet implementation class CompanyReviewList
 */
@WebServlet("/CompanyReviewList")
public class CompanyReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
			String url = request.getRequestURI(); // /mvc/board/boardList
//			String pagebar = 
					
			
			// view
			request.setAttribute("list", list);
//			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/applicant/companyReviewList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
