package com.kh.zoomin.company.controller;

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

import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;

/**
 * Servlet implementation class Company
 */
@WebServlet("/Company")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyService companyService = new CompanyService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> param = new HashMap<>();
		List<Company> list = companyService.findCompany(param);
		System.out.println("list = " + list);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-inf/views/applicant/companyReviewList.jsp")
			.forward(request, response);
	}

}
