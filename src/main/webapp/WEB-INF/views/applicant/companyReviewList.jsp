<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="com.kh.zoomin.company.dto.Company"%>
<%@page import="com.kh.zoomin.common.ZoominUtils"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
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
	
	List<CompanyReview> list = (List<CompanyReview>) request.getAttribute("list");
	
	Member apMember = (Member) session.getAttribute("loginMember");
	ApplicantMember applicantMember = null;
	if(apMember instanceof ApplicantMember){
		applicantMember = (ApplicantMember) apMember;
	}
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/companyReview.css"/>
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<section id="companyReview-container">
	<h1>-회사 리뷰 게시판-</h1>

	<%-- <div id="companyReview-btn-update">
		<button onclick="location.href='<%= request.getContextPath() %>/review/company/companyReviewUpdate'">리뷰 수정하기</button>
	</div> --%>

	<br />
	<table class="company-review-list">
		<tr>
			<th>번호</th>
			<th>회사명</th>
			<th>분야</th>
			<th>별점</th>
			<th>내용</th>
			<th>등록 날짜</th>
		</tr>	
<%
	if(list != null && !list.isEmpty()){
		for(CompanyReview companyReview : list){
%>
		<tr>
			<td>
				<a href="<%= request.getContextPath() %>/review/company/companyReviewBoard?no=<%= companyReview.getNo() %>"><%= companyReview.getNo() %></a>
			</td>
			<td>
				<%= companyReview.getCompanyNo() %>
			</td>
			<td>
				<% if(companyReview.getCategoryNumber()==1) %>IT/웹/통신
				<% if(companyReview.getCategoryNumber()==2) %>미디어/디자인
				<% if(companyReview.getCategoryNumber()==3) %>의료/제약/복지	
				<% if(companyReview.getCategoryNumber()==4) %>건설업
				<% if(companyReview.getCategoryNumber()==5) %>서비스업
				<% if(companyReview.getCategoryNumber()==6) %>은행/금융업
				<% if(companyReview.getCategoryNumber()==7) %>유통/무역/운송
				<% if(companyReview.getCategoryNumber()==8) %>제조/화학
				<% if(companyReview.getCategoryNumber()==9) %>기관/협회
				<% if(companyReview.getCategoryNumber()==10) %>교육업
			</td>
			<td><%= companyReview.getStars() %></td>
			<td><%= companyReview.getContent() %></td>
			<td><%= companyReview.getRegDate() %></td>
		</tr>
<%  
		}
	} else {
%>
		<tr>
			<td colspan="6">조회된 게시글이 없습니다.</td>
		</tr>
<%  } %>
</table>
<%
	if(loginMember != null && loginMember.getMemberType()==2){
%>
	<div id="companyReview-btn-add">
		<button onclick="location.href='<%= request.getContextPath() %>/review/company/companyReviewEnroll'">리뷰 등록하기</button>
	</div>
<%		
	}
%>	
<br />
	<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
	</div>

</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>