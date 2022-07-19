package com.kh.zoomin.applicant.information.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.resume.model.dto.Gender;
import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.applicant.resume.model.dto.SchoolType;
import com.kh.zoomin.applicant.resume.model.dto.Status;
import com.kh.zoomin.applicant.resume.model.service.ResumeService;
import com.kh.zoomin.member.dto.Member;


/**
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/ApplicantInfoServlet")
public class ApplicantInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResumeService ResumeService = new ResumeService();
 
	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩처리
		request.setCharacterEncoding("utf-8");
		
		request.getRequestDispatcher("/WEB-INF/views/applicant/applicant.jsp")
			.forward(request, response);
	}
	
	
}
