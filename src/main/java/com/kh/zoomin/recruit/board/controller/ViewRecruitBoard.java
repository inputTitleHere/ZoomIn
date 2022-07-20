package com.kh.zoomin.recruit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class ViewRecruitBoard
 */
@WebServlet("/recruit/board/viewRecruitBoard")
public class ViewRecruitBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 입력전달
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 2 업무로직
		RecruitBoard rb = rbs.viewRecruitBoard(boardNo);
		
		
		request.setAttribute("recruitBoard", rb);
		request.getRequestDispatcher("/WEB-INF/views/recruit/board/recruitBoardView.jsp").forward(request, response);
	}


}
