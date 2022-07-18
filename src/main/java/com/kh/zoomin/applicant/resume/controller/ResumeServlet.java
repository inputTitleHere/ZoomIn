package com.kh.zoomin.applicant.resume.controller;

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
@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResumeService ResumeService = new ResumeService();
 
	/**
	 * select * from resume where resume_no = ?
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩처리
		request.setCharacterEncoding("utf-8");
		// 2. 사용자입력값 처리
		HttpSession loginSession = request.getSession();
		ApplicantMember member = (ApplicantMember)loginSession.getAttribute("loginMember");
//		int uid = Integer.parseInt(member.getUid()) ;
		int uid = 21;
		// 3. 업무로직
		Resume result = ResumeService.findByResume(uid);
		//4. 응답처리 
		if(result != null) {
			String birthday = result.getBirthday().toString().substring(0,10);
			
			request.setAttribute("categoryNumber", result.getCategoryNumber());
			request.setAttribute("name", result.getName());
			request.setAttribute("birthday", birthday);
			request.setAttribute("gender", result.getGender().toString());
			request.setAttribute("address", result.getAddress());
			request.setAttribute("schoolType", result.getSchoolType().toString());
			request.setAttribute("schoolName", result.getSchoolName());
			request.setAttribute("schoolStatus", result.getSchoolStatus().toString());
			request.setAttribute("majorName", result.getMajorName());
			request.setAttribute("grade", result.getGrade());
			request.setAttribute("totalPoint", result.getTotalPoint());
			request.setAttribute("result","ok");
			
		}else {
			request.setAttribute("result", "no");
		}
		request.getRequestDispatcher("/WEB-INF/views/applicant/Resume.jsp")
			.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession loginSession = request.getSession();
		ApplicantMember member = (ApplicantMember)loginSession.getAttribute("loginMember");
		// 김지윤님 applicantMember 수정후에 Integer.parseInt 제거 할것!
//		int uid = Integer.parseInt(member.getUid()) ; 로그인 기능 구현후 uid 수정
		int uid = 21;	
		try {
			//인코딩처리
			request.setCharacterEncoding("utf-8");
			//1. 사용자 입력값 처리
			int categoryNumber = Integer.parseInt(request.getParameter("categoryNumber"));
			String name = request.getParameter("name");
			String _birthday = request.getParameter("birthday");
			Gender gender = Gender.valueOf(request.getParameter("gender"));
			String address = request.getParameter("address");
			SchoolType schoolType = SchoolType.valueOf(request.getParameter("schoolType"));
			String schoolName = request.getParameter("schoolName");
			Status schoolStatus = Status.valueOf(request.getParameter("schoolStatus"));
			String majorName = request.getParameter("majorName");
			double grade = Double.valueOf(request.getParameter("grade"));
			double totalPoint = Double.valueOf(request.getParameter("totalPoint"));
		
			SimpleDateFormat dateFomat = new SimpleDateFormat("yyyyMMdd");
			Date birthday = dateFomat.parse(_birthday);
			
			Resume resume = new Resume(uid, categoryNumber ,name, birthday, gender, address, 
					schoolType, schoolName, schoolStatus, majorName, grade, totalPoint);
			
			//2.업무로직 처리
			String newResult = request.getParameter("result");
			// 여기서의 no 는 신규이력서 즉 구직자 로그인후 이력서 정보가 없는상태이다.
			int result = 0;
			if("no".equals(newResult)) {
				result = ResumeService.insertResume(resume);				
			}else if("ok".equals(newResult)) {
				result = ResumeService.updateResume(resume);
			}
			
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
