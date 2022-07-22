<%@page import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
	List<SalaryReview> list = (List<SalaryReview>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/salaryReviewEnroll.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<section id="salary-review-view-container">
	<h2>-연봉 리뷰 등록-</h2>
	
	<form 
		action="<%= request.getContextPath() %>/review/salary/salaryReviewEnroll" 
		name="salaryReviewEnrollFrm" 
		method="post"
	>
		<table id="tbl-salary-review">
			<tr>
				<th>작성자 번호</th>
				<td>
					<input type="text" name="uid" />
				</td>
			</tr>
			<tr>
				<th>회사명</th>
				<td>
					<input type="text" name="company_no" />
				</td>
			</tr>
			<tr>
				<th>분야</th>
				<td>
					<input type="text" name ="category_number"/>
				</td>
			</tr>
			<tr>
				<th>연차</th>
				<td>
					<input type="text" name="work_year"/>
				</td>
			</tr>
			<tr>
				<th>직급</th>
				<td>
					<select name="job_position" id="aaaa">
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
					<input type="text" name="salary"/>만원
				</td>
			</tr>
		</table>
	</form>
	
	<div id="end">
		<button onclick="location.href='<%= request.getContextPath() %>/review/salary/salaryReviewList'">취소</button>
		<button id="btnSubmit">리뷰 등록하기</button>
	</div>
</section>
<script>
document.querySelector("#btnSubmit").onclick = () =>{
	document.salaryReviewEnrollFrm.submit();
};

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>