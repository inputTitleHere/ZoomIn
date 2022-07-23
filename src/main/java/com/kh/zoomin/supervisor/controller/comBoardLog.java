package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.supervisor.model.dto.ComLog;
import com.kh.zoomin.supervisor.model.dto.RecLog;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class comBoardLog
 */
@WebServlet("/supervisor/comBoardLog")
public class comBoardLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupervisorService ss = new SupervisorService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값
		int cPage = 1;
		int numPerPage = 5;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}	catch(NumberFormatException e) {}
		
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		
		Map<String, Object> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		
		//업무로직
		//A.content영역
		List<ComLog> comLogList = ss.getComLogAll(param);
		//B.pagebar영역
		int totalComLogCnt = ss.getTotalComLogCnt();
		String url = request.getRequestURI();
		String comLogPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalComLogCnt, url);
		
		//응답처리
		request.setAttribute("comLogList", comLogList);
		request.setAttribute("comLogPagebar", comLogPagebar);
		request.getRequestDispatcher("/WEB-INF/views/supervisor/comBoardLog.jsp").forward(request, response);
	}

}
