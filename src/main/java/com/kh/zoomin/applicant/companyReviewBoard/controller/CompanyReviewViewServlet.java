package com.kh.zoomin.applicant.companyReviewBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.service.CompanyReviewService;

/**
 * Servlet implementation class CompanyReviewViewServlet
 */
@WebServlet("/CompanyReviewViewServlet")
public class CompanyReviewViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyReviewService companyReviewService = new CompanyReviewService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			
			Cookie[] cookies = request.getCookies();
			String reviewCookieVal = "";
			boolean hasRead = false;
			
			if(cookies != null) {
				for(Cookie c : cookies) {
					String name = c.getName();
					String value = c.getValue();
					if("reviewCookie".equals(name)) {
						reviewCookieVal = value;
						if(value.contains("[" + no + "]")) {
							hasRead = true;
						}
						break;
					}
				}
			}
			
			if(!hasRead) {
				Cookie cookie = new Cookie("reviewCookie", reviewCookieVal + "[" + no + "]");
				cookie.setPath(request.getContextPath() + "/CompanyReviewViewServlet");
				cookie.setMaxAge(365 * 24 * 60 * 60);
				response.addCookie(cookie);
				System.out.println("[boardCookie 새로 발급되었음 : " + cookie.getValue() + "]");
			}
			
			CompanyReview companyReview = companyReviewService.findByCompanyReviewNo(no, hasRead);
			request.setAttribute("companyReview", companyReview);
			request.getRequestDispatcher("/WEB-INF/views/applicant/companyReviewView.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
