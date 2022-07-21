package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		//date별 방문자 수 가져오기 : select trunc(v_date) as "date", count(*) cnt from  visit where  v_date >= to_char((sysdate-7), 'yyyymmdd') group by trunc(v_date)
		List<WeekData> visitList = new ArrayList<>();
		visitList = supervisorService.getVisitData(); 
		//System.out.println("visitList = " + visitList);
		
		//date별 게시글 수 가져오기
		List<WeekData> boardList = new ArrayList<>();
		boardList = supervisorService.getBoardData();
		//System.out.println("boardList = " + boardList);
				
		//응답요청 : json으로 변환해서 저장하기
		Gson gson = new Gson();
		String jsonStrVisit = gson.toJson(visitList);
		String jsonStrBoard = gson.toJson(boardList);
		//System.out.println("jsonStrVisit = " + jsonStrVisit); //{"day":"7월 12, 2022","cnt":1},{"day":"7월 15, 2022","cnt":1} 이렇게 넘어옴
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonStrVisit);
		//response.getWriter().print(jsonStrBoard);
	}

}
