package com.kh.zoomin.recruit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.zoomin.common.ZoominMvcUtils;
import com.kh.zoomin.company.service.CompanyService;
import com.kh.zoomin.recruit.member.model.dto.RecruitMember;
import com.kh.zoomin.recruit.member.model.service.RecruitService;

/**
 * Servlet implementation class RecruiterJoinServlet
 */
@WebServlet("/recruit/join")
public class RecruitJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecruitService rs = new RecruitService();
    private CompanyService cs = new CompanyService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/recruiterJoin.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			//사용자 입력값
			String companyNo = request.getParameter("companyNo");
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			String password = ZoominMvcUtils.getEncryptedPassword(request.getParameter("password"), id);
			String email = request.getParameter("email");
		
			RecruitMember rmember = new RecruitMember(0, companyNo, name, id, password, email, false, null);
			System.out.println("rmember@RecruitJoinServlet = " + rmember);
			
			// companyNo있는지 확인하기.
			boolean isCompanyExist = cs.isCompanyExist(companyNo);
			System.out.println("isCompanyExist="+isCompanyExist);
			if(!isCompanyExist) {
				System.out.println("회사 없음!!!!");
				request.setAttribute("rmember", rmember);
				request.getRequestDispatcher("/WEB-INF/views/company/enrollCompany.jsp").forward(request, response);
				return;
			}
			
			
			//업무로직
			int result = rs.addRecruiter(rmember);
			//System.out.println("result@RecruitJoinServlet = " + result);
			
			//리다이렉트
			HttpSession session = request.getSession();
			session.setAttribute("msg", "회원가입이 정상적으로 처리되었습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
