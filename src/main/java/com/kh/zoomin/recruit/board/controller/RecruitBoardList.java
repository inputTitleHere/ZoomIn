package com.kh.zoomin.recruit.board.controller;

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

import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.member.dto.Member;
import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.dto.RecruitBoardReadMode;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;
import com.kh.zoomin.recruit.member.model.dto.RecruitMember;

/**
 * Servlet implementation class MainRecruitBoardList
 */
@WebServlet("/recruit/board/recruitBoardList")
public class RecruitBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		if (member instanceof RecruitMember) { // RecruitMember(구인자)인경우
			RecruitMember recruitMember = (RecruitMember) member;
			int uid = recruitMember.getUid();
			String companyNo=recruitMember.getCompanyNo();
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("uid", uid);
			param.put("companyNo", companyNo);
			// 순서는 일단 무조건 마감임박순으로 정렬
			try {
				List<RecruitBoard> result = rbs.loadRecruiterBoard(param);
				request.setAttribute("recruiterBoard", result);
				// view처리는 아래에서 수행함.
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		
		// 기본적인 채용게시판 처리
		try {
			int currentPage = 1;
			int itemsPerPage = 7;
			// 아래는 만약 모드가 없으면 마감임박부터. 모드설정이 있으면 해당 모드를 사용.
			RecruitBoardReadMode mode = request.getParameter("mode") == null ? RecruitBoardReadMode.NEAR_CLOSURE
					: RecruitBoardReadMode.valueOf(request.getParameter("mode"));
			try {
				currentPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
			}

			int start = (currentPage - 1) * itemsPerPage + 1; // 몇번 객체부터 몇번까지 가져올지 설정한다.
			int end = start + itemsPerPage - 1; // 시작번호 + 페이지당 로드할 개수 - 1(1~7 이면 7개인데 1+7=8이므로 1을 뺀다. 8~14=>7개. =8+7-1)

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("start", start);
			param.put("end", end);
			param.put("mode", mode);

			// Load recruit board items
			List<RecruitBoard> result = rbs.loadRecruitBoardHeaders(param);
			request.setAttribute("boardList", result);
			
			// Load total recruit board items from db
			int totalCount = rbs.totalRecruitBoardCount();
			String url = request.getRequestURI();
			String pagebarHTML = ZoominUtils.getPageBar(currentPage, itemsPerPage, totalCount, url);
			request.setAttribute("pagebar", pagebarHTML);

			// 3. view 처리
			request.getRequestDispatcher("/WEB-INF/views/recruit/board/recruitBoardList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
