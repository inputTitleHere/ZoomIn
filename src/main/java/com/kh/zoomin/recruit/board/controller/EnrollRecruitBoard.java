package com.kh.zoomin.recruit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class EnrollRecruitBoard
 */
@WebServlet("/recruit/board/addEnroll")
public class EnrollRecruitBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		int applicantUid=Integer.parseInt(request.getParameter("uid"));
		boolean isEnrolled=Boolean.parseBoolean(request.getParameter("isEnrolled"));
		
//		System.out.println("fav servlet BEFORE: "+boardNo+" "+applicantUid+" "+isFaved);
		
		if(!isEnrolled) {
			//찜하기 안누른 경우
			int result = rbs.setEnroll(boardNo, applicantUid);
			isEnrolled=true;
		}else {
			// 찜하기 누른 경우
			int result = rbs.removeEnroll(boardNo, applicantUid);
			isEnrolled=false;
		}
//		System.out.println("fav servlet AFTER: "+boardNo+" "+applicantUid+" "+isFaved);
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(isEnrolled, response.getWriter());
		
	}

}
