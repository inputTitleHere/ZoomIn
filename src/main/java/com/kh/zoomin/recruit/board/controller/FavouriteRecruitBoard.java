package com.kh.zoomin.recruit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class FavouriteRecruitBoard
 */
@WebServlet("/recruit/board/addFavourite")
public class FavouriteRecruitBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		int applicantUid=Integer.parseInt(request.getParameter("uid"));
		boolean isFaved=Boolean.parseBoolean(request.getParameter("isFaved"));
		
//		System.out.println("fav servlet BEFORE: "+boardNo+" "+applicantUid+" "+isFaved);
		
		if(!isFaved) {
			//찜하기 안누른 경우
			int result = rbs.setFavourite(boardNo, applicantUid);
			isFaved=true;
		}else {
			// 찜하기 누른 경우
			int result = rbs.removeFavourite(boardNo, applicantUid);
			isFaved=false;
		}
//		System.out.println("fav servlet AFTER: "+boardNo+" "+applicantUid+" "+isFaved);
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(isFaved, response.getWriter());
		
		
	}

}
