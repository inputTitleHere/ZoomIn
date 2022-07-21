package com.kh.zoomin.recruit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.recruit.member.RecruitMember;
import com.kh.zoomin.recruit.member.model.service.RecruitService;

/**
 * Servlet implementation class RecruitLoginServlet
 */
@WebServlet("/recruit/login")
public class RecruitLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitService res = new RecruitService();
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//인코딩처리
			request.setCharacterEncoding("utf-8");
			
			//1. 사용자입력값 처리 (아이디, 비번) 
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			//입력값 확인용
			System.out.println("id = " + id);
			System.out.println("password = " + password);
			
			//2. 업무로직
			RecruitMember rmember = res.findrecruId(id);
			System.out.println("rmember= " + rmember);

			String message = null; 
			HttpSession session = request.getSession(); 
			
			//로그인 여부 판단. 로그인 성공
			if(rmember != null) {
				session.setAttribute("id", id);
				session.setAttribute("msg", message);
				session.setAttribute("loginMember", rmember);
				session.setAttribute("msg", "로그인 성공입니다.");
				System.out.println(rmember.getMemberType());
			}
			else {
				//로그인 실패
				message = "아이디 또는 비밀번호가 일치하지 않습니다.";
				request.getRequestDispatcher("/WEB-INF/views/common/recruiterLogin.jsp").forward(request, response);
			}
			
			//관리자 로그인 (이윤정)
			if(rmember.isSupervisor()) {
				request.getRequestDispatcher("/WEB-INF/views/supervisor/supervisorIndex.jsp").forward(request, response);
			}
			//3. 리다이렉트
//			response.sendRedirect("http://localhost:9090/zoomin/recruit/board/recruitBoardList");
			response.sendRedirect(request.getContextPath()+"/recruit/board/recruitBoardList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/common/recruiterLogin.jsp").forward(request, response);
    }
	

}