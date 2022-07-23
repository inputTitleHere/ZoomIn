package com.kh.zoomin.recruit.headhunt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.applicant.resume.model.service.ResumeService;
import com.kh.zoomin.common.ZoominUtils;

/**
 * Servlet implementation class headhuntList
 */
@WebServlet("/recruit/headhunt/headhuntList")
public class headhuntList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResumeService rs = new ResumeService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int category=1;
		int cPage=1;
		int itemsPerPage=7;
		try {
			category=Integer.parseInt(request.getParameter("category"));
		}catch (NumberFormatException e) {}
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {}
		
		int start=(cPage-1)*itemsPerPage+1;
		int end=start+itemsPerPage -1;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start",start);
		param.put("end",end);
		param.put("category", category);
		
		List<Resume> resumeList = rs.findResumeByCategory(param);
		
		int totalCount=rs.findResumeCountByCategory(category);
		String url=request.getRequestURI();
		url+=String.format("?%s=%d","category",category);
		String pagebarHTML = ZoominUtils.getPageBar(cPage, itemsPerPage, totalCount, url);
		
		request.setAttribute("resumeList", resumeList);
		request.setAttribute("pagebar", pagebarHTML);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/WEB-INF/views/recruit/headhunt/headhunt.jsp").forward(request, response);
		
	}

}
