package com.kh.zoomin.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.service.CompanyService;

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
		List<String> resultList = new ArrayList<>();	//전송할 결과리스트	
		List<Company> company = cs.getCompanyAll();		//회사 리스트
		List<String> comNameList = new ArrayList<>();	//회사이름 리스트
			
			for(Company com : company) {
				comNameList.add(com.getCompanyName());	//회사이름 추출
			}
		
			//사용자 검색값과 비교
			for(String comName : comNameList) {
				if(comName.contains(term))
					resultList.add(comName);
			}
			System.out.println("resultList = " + resultList);
		
		//응답처리 : json
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(resultList, response.getWriter());
	}

}
