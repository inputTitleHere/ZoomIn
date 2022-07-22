package com.kh.zoomin.applicant.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.member.model.service.ApplicantService;
import com.kh.zoomin.common.ZoominMvcUtils;

/**
 * Servlet implementation class UpdatePwApplicant
 */
@WebServlet("/applicant/updatePw")
public class UpdatePwApplicantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicantService as = new ApplicantService();
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//사용자 입력값
			String id = request.getParameter("id"); 
			//이전 아이디
			String prevPw = ZoominMvcUtils.getEncryptedPassword(request.getParameter("prevPw"), id);
			//이후 아이디
			String nextPw = ZoominMvcUtils.getEncryptedPassword(request.getParameter("nextPw"), id);
			
			ApplicantMember amember = as.findAppliId(id);
			String msg = null;
			String path = null;
			//sql: update applicant_member set password = ? where id = ?
			//db에서 갖고온 기존 id가 존재하고 갖고온 비번이 이전비번과 동일하다면. 
			if(amember != null && prevPw.equals(amember.getPassword())) {
				int result = as.updatePwApllicant(id, nextPw);
				msg = "성공적으로 비밀번호가 변경되었습니다.";
				path = request.getContextPath() + "/applicant/myPage";
				
				//구직자는 /applicant/myPage = ApplicantViewServlet
				//구인자는 /recruit/myPage = RecruitViewServlet
			} else {
				msg = "기존 비밀번호가 일치하지 않습니다.";
				path = request.getContextPath() + "/applicant/updatePw";
			}
			
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(path);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/updatePwApplicant.jsp").forward(request, response);
	}

}
