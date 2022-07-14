package com.kh.zoomin.applicant.resume.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.applicant.resume.model.dto.Gender;
import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.applicant.resume.model.dto.SchoolType;
import com.kh.zoomin.applicant.resume.model.dto.Status;
import com.kh.zoomin.applicant.resume.model.service.ResumeService;


/**
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResumeService ResumeService = new ResumeService();
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/applicant/Resume.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		int uid = Integer.parseInt();
				
		try {
			//인코딩처리
			request.setCharacterEncoding("utf-8");
			//1. 사용자 입력값 처리
			String name = request.getParameter("name");
			int uid = Integer.parseInt(request.getSession("uid"));
			String birthday = request.getParameter("birthday");
			Gender gender = Gender.valueOf(request.getParameter("gender"));
			String address = request.getParameter("address");
			int interestJob = Integer.parseInt(request.getParameter("interestJob"));
			SchoolType schoolType = SchoolType.valueOf(request.getParameter("schoolType"));
			String schoolName = request.getParameter("schoolName");
			Status schoolStatus = Status.valueOf(request.getParameter("schoolStatus"));
			String majorName = request.getParameter("majorName");
			double grade = Double.valueOf(request.getParameter("grade"));
			double totalPoint = Double.valueOf(request.getParameter("totalPoint"));
		
			Resume resume = new Resume(interestJob, uid, name, birthday, gender, address, interestJob, 
					schoolType, schoolName, schoolStatus, majorName, grade, totalPoint);
			
			//2.업무로직 처리
			int result = ResumeService.insertResume(resume);
			
			//3.응답 리다이렉트
			
			HttpSession session = request.getSession();
			String msg = "";
			
			if(result > 0) {
				msg = "이력서 성공적으로 등록!";
				session.setAttribute("msg", msg);
			}
			//현재 페이지 리다이렉트
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
