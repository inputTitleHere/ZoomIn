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

import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.exception.RecruitBoardException;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class UpdateRecruitBoard
 */
@WebServlet("/recruit/board/updateRecruitBoard")
public class UpdateRecruitBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("No"));
		RecruitBoard rb = rbs.findBoardByNo(no);
		request.setAttribute("recruitBoard", rb);
		request.getRequestDispatcher("/WEB-INF/views/recruit/board/recruitBoardUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		RecruitBoard rb = new RecruitBoard();
		try {
		// 입력값을 전달받는다.
		int uid = Integer.parseInt(request.getParameter("uid"));
		String companyNo = request.getParameter("companyNo");
		String title = request.getParameter("title");
		int category = Integer.parseInt(request.getParameter("category"));
		System.out.println(request.getParameter("closureDate"));
		Date closureDate=null;
		try {
			closureDate = new Date(sdf.parse(request.getParameter("closureDate")).getTime()); // simpleDateFormat으로 parse하면 java.util.Date이다. 여기서 getTime을 통해 UTC(long)를 구하고 이를 이용해 새로운 java.sql.Date객체를 생성한다.			
		}catch(ParseException e) {
			throw new RecruitBoardException("@WriteRecruitBoard.java : 날짜 처리 오류",e);
		}
		int no=Integer.parseInt(request.getParameter("no"));
		String career = request.getParameter("career");
		String schoolStatus = request.getParameter("schoolStatus");
		String workType = request.getParameter("workType");
		String officeLocation = request.getParameter("officeLocation");
		String salary = request.getParameter("salary");
		String content = request.getParameter("content");
		// 객체에 내용을 담는다.
		rb.setNo(no);
		rb.setUid(uid);
		rb.setCompanyNo(companyNo);
		rb.setCategoryNumber(category);
		rb.setTitle(title);
		rb.setCareerYears(career);
		rb.setSchoolStatus(schoolStatus);
		rb.setWorkType(workType);
		rb.setOfficeLocation(officeLocation);
		rb.setSalary(salary);
		rb.setContent(content);
		rb.setClosureDate(closureDate);
		
		int result = rbs.updateRecruitBoard(rb.setDefaults());
		
		// 리다이렉트처리
		response.sendRedirect(request.getContextPath()+"/recruit/board/viewRecruitBoard?boardNo="+no);
		}catch(Exception e) {
			throw e;
		}
	}

}
