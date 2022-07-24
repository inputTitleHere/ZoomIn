package com.kh.zoomin.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.company.dto.Category;
import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;
import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class SearchCompany
 */
@WebServlet("/search/company")
public class SearchCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();
	private RecruitBoardService rbs = new RecruitBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값
		String userVal = request.getParameter("userVal");		//카테고리 아니면 회사이름
		
		//업무로직 : 사용자 입력값에 해당하는 회사 찾기



		//응답처리

		
	}

}
