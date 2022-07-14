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
import com.kh.zoomin.recruit.board.dto.RecruitBoardReadMode;
import com.kh.zoomin.recruit.board.service.RecruitBoardService;

/**
 * Servlet implementation class IndexRecruitBoardList
 */
@WebServlet("/recruit/board/indexRecruitBoard")
public class IndexRecruitBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitBoardService rbs = new RecruitBoardService();
	/**
	 * 인덱스 페이지 최초 로드시 채용게시글 상위 5개만 띄우는 목적을 지닌 서블릿입니다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인덱스페이지에서는 딱 5개의 항목만 우선적으로 띄웁니다. 
		int start=1;
		int end=5;
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("start", 1);
		param.put("end", 5);
		param.put("mode",RecruitBoardReadMode.NEAR_CLOSURE);
		// db 달려오기
		List<RecruitBoard> recruitBoard = rbs.loadRecruitBoardHeaders(param); 
		
		// json형식으로 list객체를 반환하기(request반환처리까지 해줌)
		response.setContentType("application/json; charset=utf-8");		
		Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일").create();
		gson.toJson(recruitBoard, response.getWriter());
	}

}












