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
 * Servlet implementation class ApplicantUpdateServlet
 */
@WebServlet("/applicant/update")
public class ApplicantUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicantService as = new ApplicantService();
       
    /**
     * updateApplicant = update applicant_member set name = ?, phone = ?, email = ? where id = ?
     * 아이디, 패스워드는 회원 수정목록 포함x / regdate는 가입일이니 x / uid는 애초에 작성x
     * @see HttpServlet#HttpServlet()
     */
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//사용자 입력
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			ApplicantMember amember = 
					new ApplicantMember(0, name, id, null, phone, email, null);
			System.out.println("ApplicantUpdateServlet = " + amember);
			
			//업무로직
			int result = as.updateApplicant(amember);
			
			//응답
			HttpSession session = request.getSession();
			String msg = null;
			
			if(result > 0) {
				msg = "회원정보 수정완료했습니다.";
				session.setAttribute("loginMember", as.findAppliId(id));
				session.setAttribute("loginMember", amember);
			}
			
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/applicant/myPage"); //ApplicantViewServlet
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/common/applicantMyPage.jsp").forward(request, response);
    }

}
