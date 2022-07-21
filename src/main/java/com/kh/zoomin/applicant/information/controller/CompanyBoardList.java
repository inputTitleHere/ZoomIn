package com.kh.zoomin.applicant.information.controller;

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

import com.google.gson.Gson;
import com.kh.zoomin.applicant.information.model.dto.CompanyInfo;
import com.kh.zoomin.applicant.information.model.service.ApplicantInfoService;
import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.member.dto.Member;


/**
 * Servlet implementation class MainRecruitBoardList
 */
@WebServlet("/CompanyBoardList")
public class CompanyBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicantInfoService applicantInfoService = new ApplicantInfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Member member = (Member)session.getAttribute("loginMember");
//		if (member instanceof ApplicantMember) { // ApplicantMember(구인자)인경우
//			ApplicantMember applicantMember = (ApplicantMember) member;
//			int uid = applicantMember.getUid();
//			
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("uid", uid);
//			// 순서는 일단 무조건 마감임박순으로 정렬
//			try {
//				List<RecruitBoard> result = rbs.loadRecruiterBoard(param);
//				request.setAttribute("recruiterBoard", result);
//				// view처리는 아래에서 수행함.
//			}catch(Exception e) {
//				e.printStackTrace();
//				throw e;
//			}
//		}
		
		
		// 기본적인 채용게시판 처리
		try {
			int currentPage = 1;
			int itemsPerPage = 3;
			
			try {
				currentPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
			}

			int start = (currentPage - 1) * itemsPerPage + 1; // 몇번 객체부터 몇번까지 가져올지 설정한다.
			int end = start + itemsPerPage - 1; // 시작번호 + 페이지당 로드할 개수 - 1(1~7 이면 7개인데 1+7=8이므로 1을 뺀다. 8~14=>7개. =8+7-1)

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("start", start);
			param.put("end", end);
			

			//컴퍼니 테이블 데이터 불러오기
			CompanyInfo res = new CompanyInfo();
			List<CompanyInfo> result = applicantInfoService.findByCompany(param);
			request.setAttribute("boardList", result);
			
			// 컴퍼니 테이블 총 데이터 수 조회하기
			int totalCount = applicantInfoService.totalCompanyCount();
			String url = request.getRequestURI();
			String pagebarHTML = ZoominUtils.getApplicantPageBar(currentPage, itemsPerPage, totalCount, url);
			request.setAttribute("pagebar", pagebarHTML);
			res.setResult(result);
			res.setPageBar(pagebarHTML);
			
			System.out.println(pagebarHTML);
			// 3. view 처리
			// 2. 응답 json으로 작성
			Gson gson = new Gson();
			String jsonStr = gson.toJson(res);
			System.out.println("jsonStr = " + jsonStr);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonStr);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
