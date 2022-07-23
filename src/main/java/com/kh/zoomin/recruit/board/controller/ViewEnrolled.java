package com.kh.zoomin.recruit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class ViewEnrolled
 */
@WebServlet("/recruit/board/viewEnrolled")
public class ViewEnrolled extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		List<Resume> enrolledList = rbs.loadEnrolledList(boardNo);
		
		request.setAttribute("title", title);
		request.setAttribute("enrolledList", enrolledList);
		request.setAttribute("boardNo", boardNo);
		request.getRequestDispatcher("/WEB-INF/views/recruit/board/viewEnrolledList.jsp").forward(request, response);
	}

}
