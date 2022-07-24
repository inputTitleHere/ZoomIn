<%@page import="com.kh.zoomin.company.dto.Company"%>
<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String msg = (String) session.getAttribute("msg");
	//System.out.println("msg@jsp = " + msg);
	if(msg != null) session.removeAttribute("msg"); 
	Member loginMember = (Member) session.getAttribute("loginMember");
	ApplicantMember am=null;

	
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie c : cookies){
			String name = c.getName();
			String value = c.getValue();
			//System.out.println("[cookie] " + name + " = " + value);
			
			}
	}

	List<SalaryReview> list = (List<SalaryReview>) request.getAttribute("list");
	
	Member apMember = (Member) session.getAttribute("loginMember");
	ApplicantMember applicantMember = null;
	if(apMember instanceof ApplicantMember){
		applicantMember = (ApplicantMember) apMember;
	}
%>
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/salaryReviewList.css" /> --%>
	<h1>-연봉 리뷰 게시판-</h1>

	<%-- <div id="companyReview-btn-update">
		<button onclick="location.href='<%= request.getContextPath() %>/review/salary/salaryReviewUpdate'">리뷰 수정하기</button>
	</div> --%>
	<table class="salary-review-list">
		<tr class="salary">
			<th>번호</th>
			<th>작성자</th>
			<th>회사</th>
			<th>카테고리</th>
			<th>연차</th> 
			<th>직급</th>
			<th>연봉</th>
			<th>등록날짜</th>
		</tr>
<% 
	if(list != null && !list.isEmpty()){
		for(SalaryReview salaryReview : list){
%>			
		<tr class="salary">
			<td>
				<a href="<%= request.getContextPath() %>/review/salary/salaryReviewBoard?no=<%= salaryReview.getNo() %>"><%= salaryReview.getNo() %></a>
			</td>
			<td>
				<%= salaryReview.getUid() %>
			</td>
			<td>
				<%= salaryReview.getCompanyNo() %></td>
			<td>
				<% if(salaryReview.getCategoryNumber() == 1) %>인사팀
				<% if(salaryReview.getCategoryNumber() == 2) %>회계/총무팀
				<% if(salaryReview.getCategoryNumber() == 3) %>마케팅팀
				<% if(salaryReview.getCategoryNumber() == 4) %>영업팀
				<% if(salaryReview.getCategoryNumber() == 5) %>생산/제조팀
				<% if(salaryReview.getCategoryNumber() == 6) %>연구개발팀 
				<% if(salaryReview.getCategoryNumber() == 7) %>기술팀
				<% if(salaryReview.getCategoryNumber() == 8) %>서비스팀
				<% if(salaryReview.getCategoryNumber() == 9) %>IT/인터넷팀
			</td>
			<td><%= salaryReview.getWorkYear() %></td>
			<td><%= salaryReview.getJobPosition() %></td>
			<td><%= salaryReview.getSalary() %>만원</td>
			<td><%= salaryReview.getRegDate() %></td>
		</tr>
<%			
		}
	} else {
%>		<tr>
			<td colspan="8">조회된 게시글이 없습니다.</td>
		</tr>
<% 		
	}
%>
	</table>
	
	<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
	</div>
	<br>
<%
	if(loginMember != null && loginMember.getMemberType()==2){
%>
		<div id="companyReview-btn-add">
			<button class="custom-btn btn-3" onclick="location.href='<%= request.getContextPath() %>/review/salary/salaryReviewEnroll'"><span>리뷰 등록하기</span></button>
		</div>
<%		
	}
%>
	