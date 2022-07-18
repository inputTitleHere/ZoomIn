package com.kh.zoomin.recruit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class DeleteRecruitBoard
 */
@WebServlet("/recruit/board/deleteRecruitBoard")
public class DeleteRecruitBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		try {
			int result=rbs.deleteRecruitBoard(no);
		}catch(Exception e) {
			throw e;
		}
		response.sendRedirect(request.getContextPath()+"/recruit/board/recruitBoardList");
	}

}
