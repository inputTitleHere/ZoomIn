package com.kh.zoomin.company.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;

/**
 * Servlet implementation class EnrollCompany
 */
@WebServlet("/company/enrollCompany")
public class EnrollCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String companyNo = request.getParameter("companyNo");
		String companyName=request.getParameter("companyName");
		String companyInfo=request.getParameter("companyInfo");
		Company company = new Company(companyNo, companyName, companyInfo);
		System.out.println("company = "+company);
		try {
			int result=cs.insertNewCompany(company);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/recruit/join");
	}

}
