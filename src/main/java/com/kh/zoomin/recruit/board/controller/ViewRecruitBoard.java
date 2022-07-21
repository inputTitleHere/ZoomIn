package com.kh.zoomin.recruit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.AsyncDispatcher;

import com.kh.zoomin.applicant.member.model.dao.ApplicantDao;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.member.dto.Member;
import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

import oracle.jdbc.internal.OracleConnection.XSSessionOperationCode;

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
		HttpSession session=request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		boolean isApplicant=false;
		if(loginMember != null &&loginMember.getMemberType()==2) {
			isApplicant=true;
		}
		// 2 업무로직
		RecruitBoard rb = rbs.viewRecruitBoard(boardNo);
		
		boolean isFavourited=false;
		boolean isEnrolled=false;
		if(isApplicant) {
			int uid=((ApplicantMember)loginMember).getUid();
			isFavourited=rbs.isRecruitBoardFaved(boardNo, uid);
			isEnrolled=rbs.isRecruitBoardEnrolled(boardNo, uid);
			
			request.setAttribute("isFaved", isFavourited);
			request.setAttribute("isEnrolled", isEnrolled);
		}
		
		request.setAttribute("recruitBoard", rb);
		request.getRequestDispatcher("/WEB-INF/views/recruit/board/recruitBoardView.jsp").forward(request, response);
	}


}
