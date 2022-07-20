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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class MyRecruitBoardList
 */
@WebServlet("/recruit/board/myRecruitBoardList")
public class MyRecruitBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uid", uid);
		List<RecruitBoard> result=rbs.loadRecruiterBoard(param);
		
		response.setContentType("application/json; charset=utf-8");	
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm").create();
		gson.toJson(result,response.getWriter());
	}

}
