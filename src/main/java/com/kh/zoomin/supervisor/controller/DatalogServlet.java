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
import com.kh.zoomin.supervisor.model.dto.AmemberLog;
import com.kh.zoomin.supervisor.model.dto.ComLog;
import com.kh.zoomin.supervisor.model.dto.RecLog;
import com.kh.zoomin.supervisor.model.dto.RmemberLog;
import com.kh.zoomin.supervisor.model.dto.SalLog;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class DatalogServlet
 */
@WebServlet("/supervisor/Datalog")
public class DatalogServlet extends HttpServlet {
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
		List<AmemberLog> amLogList = ss.getAmemberLogAll(param);
		List<RmemberLog> rmLogList = ss.getRmemberLogAll(param);		
		List<SalLog> salLogList = ss.getSalLogAll(param);
		List<ComLog> comLogList = ss.getComLogAll(param);
		List<RecLog> recLogList = ss.getRecLogAll(param);
		
			//B.pagebar영역
		int totalAmLogCnt = ss.getTotalAmLogCnt();
		int totalRmLogCnt = ss.getTotalRmLogCnt();
		int totalSalLogCnt = ss.getTotalSalLogCnt();
		int totalComLogCnt = ss.getTotalComLogCnt();
		int totalRecLogCnt = ss.getTotalRecLogCnt();
		
		String url = request.getRequestURI();
		String amLogPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalAmLogCnt, url);
		String rmLogPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalRmLogCnt, url);
		String salLogPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalSalLogCnt, url);
		String comLogPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalComLogCnt, url);
		String recLogPagebar = ZoominUtils.getPageBar(cPage, numPerPage, totalRecLogCnt, url);
		
		//응답처리
		request.setAttribute("amLogList", amLogList);
		request.setAttribute("rmLogList", rmLogList);
		request.setAttribute("salLogList", salLogList);
		request.setAttribute("comLogList", comLogList);
		request.setAttribute("recLogList", recLogList);
		
		request.setAttribute("amLogPagebar", amLogPagebar);
		request.setAttribute("rmLogPagebar", rmLogPagebar);
		request.setAttribute("salLogPagebar", salLogPagebar);
		request.setAttribute("comLogPagebar", comLogPagebar);
		request.setAttribute("recLogPagebar", recLogPagebar);

		request.getRequestDispatcher("/WEB-INF/views/supervisor/dataLog.jsp").forward(request, response);
	}

}
