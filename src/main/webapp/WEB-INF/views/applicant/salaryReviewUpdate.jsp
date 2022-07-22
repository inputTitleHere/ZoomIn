<%@page import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReviewExt"%>
<%@page import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
	SalaryReview salaryReview = (SalaryReview) request.getAttribute("salaryReview");
	SalaryReviewExt salaryReviewExt = (SalaryReviewExt) request.getAttribute("salaryReviewExt");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<section id="salary-review-view-container">
	<h2>연봉 리뷰 수정</h2>
	
	<form
		action="<%= request.getContextPath() %>/review/salary/salaryReviewUpdate" 
		name="salaryReviewUpdateFrm"
		method="post"
	>
		<input type="hidden" name="no" value="<%= salaryReview.getNo() %>"/>
		<table id="tbl-salary-review">
			<tr>
				<th>작성자 번호</th>
				<td>
					<input type="text" name="uid" value="<%= salaryReview.getUid() %>" readonly />
				</td>
			</tr>
			<tr>
				<th>회사명</th>
				<td>
					<input type="text" name="company_no" value="<%= salaryReview.getCompanyNo() %>" readonly/>
				</td>
			</tr>
			<tr>
				<th>분야</th>
				<td>
					<input type="text" name ="category_number" value="<%= salaryReview.getCategoryNumber() %>"/>
				</td>
			</tr>
			<tr>
				<th>연차</th>
				<td>
					<input type="text" name="work_year" value="<%= salaryReview.getWorkYear() %>"/>
				</td>
			</tr>
			<tr>
				<th>직급</th>
				<td>
					<select name="job_position" id="aaaa" value="<%= salaryReview.getJobPosition() %>">
						<option value="1">사원</option>
						<option value="2">주임</option>
						<option value="3">대리</option>
						<option value="4">과장</option>
						<option value="5">차장</option>
						<option value="6">부장</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>연봉</th>
				<td>
					<input type="text" name="salary" value="<%= salaryReview.getSalary() %>"/>만원
				</td>
			</tr>
			<tr>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
	
	<div id="end">
		<button onclick="location.href='<%= request.getContextPath() %>/review/salary/salaryReviewList'">취소</button>
		<button id="btnSubmit">수정하기</button>
	</div>
</section>
<script>
document.querySelector("#btnSubmit").onclick = () =>{
	document.salaryReviewUpdateFrm.submit();
};
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>