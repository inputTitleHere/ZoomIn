package com.kh.zoomin.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.member.model.service.ApplicantService;
import com.kh.zoomin.recruit.member.model.dto.RecruitMember;
import com.kh.zoomin.recruit.member.model.service.RecruitService;

/**
 * Servlet implementation class PasswordChange
 */
@WebServlet("/PasswordChange")
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitService rs = new RecruitService();
	private ApplicantService as = new ApplicantService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// db상 비번 업데이트
		
		// recruit 비번 업데이트
		
		List<RecruitMember> rmember = rs.loadPassword1234();
		for(RecruitMember r : rmember) {
			String newPassword = ZoominMvcUtils.getEncryptedPassword(r.getPassword(), r.getId());
			r.setPassword(newPassword);
		}
		int result = rs.setPassword1234(rmember);
		
		/*
		// applicant 비번 업데이트
		List<ApplicantMember> amember=as.loadPassword1234();
		for(ApplicantMember a:amember) {
			String newPassword=ZoominMvcUtils.getEncryptedPassword(a.getPassword(), a.getId());
			a.setPassword(newPassword);
		}
		int result=as.setPassword1234(amember);
		*/
		
		
		
	}

}
