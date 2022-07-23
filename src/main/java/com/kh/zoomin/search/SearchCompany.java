package com.kh.zoomin.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;

/**
 * Servlet implementation class SearchCompany
 */
@WebServlet("/search/company")
public class SearchCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값
		String userVal = request.getParameter("userVal");		
		
		//업무로직 : 사용자 입력값에 해당하는 회사 찾기
		Company company = cs.getCompanyByName(userVal);	//이름으로 회사 가져오기		
		
		company.getCompanyNo();
		
	}

}
