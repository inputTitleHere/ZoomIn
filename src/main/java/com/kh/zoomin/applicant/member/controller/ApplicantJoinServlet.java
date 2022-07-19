package com.kh.zoomin.applicant.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.member.model.service.ApplicantService;

/**
 * Servlet implementation class ApplicantJoinServlet
 */
@WebServlet("/applicant/join")
public class ApplicantJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicantService as = new ApplicantService();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/applicantJoin.jsp")
		.forward(request, response);
	}

	/**
	 * insert into applicant_member values(seq_applicant_member_uid.nextval, ?, ?, ?, ?, ?, default);
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//이름, 아이디, 비밀번호, 핸드폰번호, 이메일 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩처리
			request.setCharacterEncoding("utf-8");
			
			// 2. 사용자입력값처리			
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			//HelloMvcUtis 아직 안만들었음. 
			String password = HelloMvcUtils.getEncryptedPassword(request.getParameter("password"), id);
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");

			ApplicantMember amember = 
					//RecruitMember와 똑같은 문제.... memberType때문에 이런듯. 
					new ApplicantMember(0, id, name, password, phone, email, null);
			System.out.println("amember@ApplicantJoinServlet = " + amember);
			
			// 3. 업무로직 : db insert
			int result = ApplicantService.insertApplicant(amember); 
			System.out.println("result@MemberEnrollServlet = " + result);
			
			// 4. 응답처리 : redirect
			HttpSession session = request.getSession();
			session.setAttribute("msg", "회원가입이 정상적으로 처리되었습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		
		} catch (Exception e) {
			e.printStackTrace(); 
			throw e; 
		}
	}

}
