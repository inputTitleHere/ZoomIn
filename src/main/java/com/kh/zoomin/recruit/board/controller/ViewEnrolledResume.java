package com.kh.zoomin.recruit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.applicant.resume.model.service.ResumeService;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class ViewEnrolledResume
 */
@WebServlet("/recruit/board/viewEnrolledResume")
public class ViewEnrolledResume extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid=Integer.parseInt(request.getParameter("uid"));
		int boardNo=-1;
		try {
			boardNo=Integer.parseInt(request.getParameter("boardNo"));			
		}catch(NumberFormatException e) {}
		String title=null;
		if(boardNo!=-1) {
			title=new RecruitBoardService().findBoardByNo(boardNo).getTitle();			
		}
		Resume resume = new ResumeService().findByResume(uid);
		
		request.setAttribute("resume", resume);
		request.setAttribute("title", title);
		request.getRequestDispatcher("/WEB-INF/views/recruit/board/viewResume.jsp").forward(request, response);
	}

}
