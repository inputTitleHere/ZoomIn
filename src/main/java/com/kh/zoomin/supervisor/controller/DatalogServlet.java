package com.kh.zoomin.supervisor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.supervisor.model.dto.AmemberLog;
import com.kh.zoomin.supervisor.model.dto.RmemberLog;
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
		//업무로직
		List<AmemberLog> amLogList = ss.getAmemberLogAll();
		List<RmemberLog> rmLogList = ss.getRmemberLogAll();		
		//System.out.println("amLogList = " + amLogList);
		
		//응답처리
		request.setAttribute("amLogList", amLogList);
		request.setAttribute("rmLogList", rmLogList);
		request.getRequestDispatcher("/WEB-INF/views/supervisor/dataLog.jsp").forward(request, response);
	}

}
