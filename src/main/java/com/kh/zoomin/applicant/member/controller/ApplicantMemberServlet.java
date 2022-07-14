package com.kh.zoomin.applicant.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.member.model.service.ApplicantMemberService;

/**
 * Servlet implementation class ApplicantMemberServlet
 */
@WebServlet("/ApplicantMemberServlet")
public class ApplicantMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicantMemberService ams = new ApplicantMemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
