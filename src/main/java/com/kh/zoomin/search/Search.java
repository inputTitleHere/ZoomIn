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

import com.google.gson.Gson;
import com.kh.zoomin.company.dto.Category;
import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;
import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값
		String term = request.getParameter("term");
		System.out.println("term = " + term);
		
		//업무로직 
		List<String> autoList = new ArrayList<>();	//자동완성에 전송할 결과리스트	
		List<Company> comList = cs.getCompanyAll();		//회사 리스트	
		
		//회사이름 검색
		for(Company com : comList) {
			if(com.getCompanyName().contains(term))
				autoList.add(com.getCompanyName());
		}		

		
		System.out.println("autoList = " + autoList);
		
		//응답처리 : json
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(autoList, response.getWriter());
	}

}
