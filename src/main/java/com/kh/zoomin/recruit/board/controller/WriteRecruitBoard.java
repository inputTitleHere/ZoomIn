package com.kh.zoomin.recruit.board.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.exception.RecruitBoardException;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * 채용게시글 작성 페이지에 사용되는 writeRecruitBoard
 */
@WebServlet("/recruit/board/writeRecruitBoard")
public class WriteRecruitBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddThh:mm");
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/recruit/board/recruitBoardWrite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecruitBoard rb = new RecruitBoard();
		
		try {
		// 입력값을 전달받는다.
		int uid = Integer.parseInt(request.getParameter("uid"));
		String companyNo = request.getParameter("conpanyNo");
		String title = request.getParameter("title");
		int category = Integer.parseInt(request.getParameter("category"));
		try {
			Date closureDate = new Date(sdf.parse(request.getParameter("closureDate")).getTime()); // simpleDateFormat으로 parse하면 java.util.Date이다. 여기서 getTime을 통해 UTC(long)를 구하고 이를 이용해 새로운 java.sql.Date객체를 생성한다.			
		}catch(ParseException e) {
			throw new RecruitBoardException("@WriteRecruitBoard.java : 날짜 처리 오류",e);
		}
		String career = request.getParameter("career");
		String schoolStatus = request.getParameter("schoolStatus");
		String workType = request.getParameter("workType");
		String officeLocation = request.getParameter("officeLocation");
		String salary = request.getParameter("salary");
		String content = request.getParameter("content");
		// 객체에 내용을 담는다.
		rb.setUid(uid);
		rb.setCompany_no(companyNo);
		rb.setCategoryNumber(category);
		rb.setTitle(title);
		rb.setCareerYears(career);
		rb.setSchoolStatus(schoolStatus);
		rb.setWorkType(workType);
		rb.setOfficeLocation(officeLocation);
		rb.setSalary(salary);
		rb.setContent(content);

		int result = rbs.insertRecruitBoard(rb);

		HttpSession session = request.getSession();
		session.setAttribute("msg", "채용글 등록에 성공했습니다.");
		response.sendRedirect(request.getContextPath()+"/recruit/board/recruitBoardList");
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
