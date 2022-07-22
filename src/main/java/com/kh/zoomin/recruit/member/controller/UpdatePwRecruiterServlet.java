package com.kh.zoomin.recruit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.zoomin.common.ZoominMvcUtils;
import com.kh.zoomin.recruit.member.model.dto.RecruitMember;
import com.kh.zoomin.recruit.member.model.service.RecruitService;

/**
 * Servlet implementation class UpdatePwRecruiter
 */
@WebServlet("/recruit/updatePw")
public class UpdatePwRecruiterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitService rs = new RecruitService();
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//입력값
			String id = request.getParameter("id"); 
			//이전 아이디
			String prevPw = ZoominMvcUtils.getEncryptedPassword(request.getParameter("prevPw"), id);
			//new 아이디
			String nextPw = ZoominMvcUtils.getEncryptedPassword(request.getParameter("nextPw"), id);
			
			RecruitMember rmember = rs.findrecruId(id);
			
			//sql: update recruit_member set password = ? where id = ?
			//메시지 저장x 초기화
			String msg = null;
			String path = null;
			//recruit 멤버여야 하고 db에서 갖고온것과 이전아이디가 동일해야함
			if(rmember != null && prevPw.equals(rmember.getPassword())) {
				int result = rs.updatePwRecruiter(id, nextPw);
				msg = "성공적으로 비밀번호가 변경되었습니다.";
				path = request.getContextPath() + "/recruit/myPage";
				
				//구직자는 /applicant/myPage = ApplicantViewServlet
				//구인자는 /recruit/myPage = RecruitViewServlet
				RecruitMember loginMember = (RecruitMember) request.getSession().getAttribute("loginMember");
				loginMember.setPassword(nextPw);
				
			} else {
				msg = "기존 비밀번호가 일치하지 않습니다.";
				path = request.getContextPath() + "/recruit/updatePw";
			}
			
			//응답
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(path);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/updatePwRecruiter.jsp").forward(request, response);
	}


}
