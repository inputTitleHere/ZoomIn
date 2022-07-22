<%@page import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
	List<SalaryReview> list = (List<SalaryReview>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/salaryReviewList.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<section id="salaryReview-container">
	<h2>-연봉 리뷰 게시판-</h2>
	<div id="companyReview-btn-add">
		<button onclick="location.href='<%= request.getContextPath() %>/review/salary/salaryReviewEnroll'">리뷰 등록하기</button>
	</div>
	<div id="companyReview-btn-update">
		<button onclick="location.href='<%= request.getContextPath() %>/review/salary/salaryReviewUpdate'">리뷰 수정하기</button>
	</div>
	<table class="salary-review-list">
		<tr>
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
		<tr>
			<td>
				<a href="<%= request.getContextPath() %>/review/salary/salaryReviewBoard?no=<%= salaryReview.getNo() %>"><%= salaryReview.getNo() %></a>
			</td>
			<td>
				<%= salaryReview.getUid() %>
			</td>
			<td><%= salaryReview.getCompanyNo() %></td>
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
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>