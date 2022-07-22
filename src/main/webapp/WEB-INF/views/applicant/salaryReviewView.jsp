<%@page import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
	SalaryReview salaryReview = (SalaryReview) request.getAttribute("salaryReview");
	List<SalaryReview> list = (List<SalaryReview>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/salaryReviewView.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<section id="salary-review-view-container">
	<h2>연봉리뷰 상세보기</h2>
	
	<div class="edit-button">
		<button id="update-button1" onclick="updateBoard()">수정</button>
		<button id="update-button2" onclick="deleteBoard()">삭제</button>
	</div>
	<table class="salary-review-list">
		<tr>
			<th>번호</th>
			<td><%= salaryReview.getNo() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= salaryReview.getUid() %></td>
		</tr>
		<tr>
			<th>회사</th>
			<td><%= salaryReview.getCompanyNo() %></td>
		</tr>
		<tr>
			<th>카테고리</th>
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
		</tr>
		<tr>
			<th>연차</th>
			<td><%= salaryReview.getWorkYear() %></td>
		</tr>
		<tr>
			<th>직급</th>
			<td><%= salaryReview.getJobPosition() %></td>
		</tr>
		<tr>
			<th>연봉</th>
			<td><%= salaryReview.getSalary() %></td>
		</tr>
		<tr>
			<th>등록날짜</th>
			<td><%= salaryReview.getRegDate() %></td>
		</tr>
	</table>
</section>
<form 
	action="<%= request.getContextPath() %>/review/salary/salaryReviewDelete" 
	method="post"
	name="salaryReviewDelFrm">
	<input type="text" name="no" value="<%= salaryReview.getNo() %>"/>
</form>
<script>
const deleteBoard = () => {
	if(confirm("삭제하시겠습니까?"));
		document.salaryReviewDelFrm.submit();
};
const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/review/salary/salaryReviewUpdate?no=<%= salaryReview.getNo() %>"
};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>