package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.zoomin.supervisor.model.dto.WeekData;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * 오늘포함 최근 1주일 date, 
 * date별 new방문자수, 
 * date별 new게시글 수
 */
@WebServlet("/supervisor/findData")
public class FindDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService supervisorService = new SupervisorService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//date별 방문자 수 가져오기
		List<WeekData> visitList = new ArrayList<>();
		visitList = supervisorService.getVisitData(); 
		//System.out.println("visitList = " + visitList);
		
		//date별 게시글 수 가져오기
		List<WeekData> boardList = new ArrayList<>();
		boardList = supervisorService.getBoardData();
		//System.out.println("boardList = " + boardList);
				
		//응답요청 : json으로 변환해서 저장하기		
		Map<String, List<WeekData>> value = new HashMap<>();
		value.put("visitList", visitList);
		value.put("boardList", boardList);
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(value, response.getWriter());	//맵으로 한번에 보냄!
		
	}

}
