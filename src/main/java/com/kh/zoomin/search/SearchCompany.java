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
import javax.servlet.http.HttpSession;

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

		String companyName = request.getParameter("userVal");		//회사이름

		
		//업무로직 : 사용자 입력값에 해당하는 회사 번호 찾기
		List<Company> comList = cs.getCompanyAll();		//회사 리스트			
		List <String> comNoList = new ArrayList<>();	//결과값으로 보낼 회사 번호 리스트

		
			//사용자 입력값을 포함하는 회사번호
		for(Company com : comList) {
			if(com.getCompanyName().contains(companyName)) {
				comNoList.add(com.getCompanyNo());
			}
		}
		
		Company result=cs.getCompanyByName(companyName); // 없으면 아마 null
		if(result!=null) {
			request.setAttribute("company", result);
			response.sendRedirect(request.getContextPath()+"/recruit/review/recruitReviewList?companyNo="+result.getCompanyNo());
			return;
		}else {
			String msg="조회된 회사가 없습니다.";
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
		
		
		
		
		

		//응답처리
		
	}

}
