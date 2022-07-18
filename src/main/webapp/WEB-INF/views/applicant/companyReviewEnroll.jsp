<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
	ApplicantMember applicantMember = (ApplicantMember) request.getAttribute("applicantMember");
	CompanyReview companyReview = (CompanyReview) request.getAttribute("companyReview");
	CompanyReviewExt companyReviewExt = (CompanyReviewExt) request.getAttribute("companyReviewExt"); 
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<section id="company-review-view-container">
	<h1>회사 리뷰 등록</h1>

	<form 
		name="companyReviewEnrollFrm"
		action="<%= request.getContextPath() %>/CompanyReviewUpdateServlet"
		method="post"
	>
		<table id="tbl-company-review">
			<tr>
				<th>작성자 번호</th>
				<td>
					<%-- <input type="text" name="writer" value="<%= applicantMember.getUid() %>" readonly/> --%>
				</td>
			</tr>
			<tr>
	            <th>평점</th>
	            <td><input type='radio' value='1' name='stars'/>1점</td>
	            <td><input type='radio' value='2' name='stars'/>2점</td>
	            <td><input type='radio' value='3' name='stars'/>3점</td>
	            <td><input type='radio' value='4' name='stars'/>4점</td>
	            <td><input type='radio' value='5' name='stars'/>5점</td>
	        </tr>
	        <tr>
	            <th>워라벨</th>
	            <td><input type='radio' value='1' name='wlb'/>1점</td>
	            <td><input type='radio' value='2' name='wlb'/>2점</td>
	            <td><input type='radio' value='3' name='wlb'/>3점</td>
	            <td><input type='radio' value='4' name='wlb'/>4점</td>
	            <td><input type='radio' value='5' name='wlb'/>5점</td>
	        </tr>
	        <tr>
	            <th>승진 가능성</th>
	            <td><input type='radio' value='1' name='levup'/>1점</td>
	            <td><input type='radio' value='2' name='levup'/>2점</td>
	            <td><input type='radio' value='3' name='levup'/>3점</td>
	            <td><input type='radio' value='4' name='levup'/>4점</td>
	            <td><input type='radio' value='5' name='levup'/>5점</td>
	        </tr>
	        <tr>
	            <th>업무 강도</th>
	            <td><input type='radio' value='1' name='work_inten'/>1점</td>
	            <td><input type='radio' value='2' name='work_inten'/>2점</td>
	            <td><input type='radio' value='3' name='work_inten'/>3점</td>
	            <td><input type='radio' value='4' name='work_inten'/>4점</td>
	            <td><input type='radio' value='5' name='work_inten'/>5점</td>
	        </tr>
	        <tr>
	            <th>발전 가능성</th>
	            <td><input type='radio' value='1' name='poten'/>1점</td>
	            <td><input type='radio' value='2' name='poten'/>2점</td>
	            <td><input type='radio' value='3' name='poten'/>3점</td>
	            <td><input type='radio' value='4' name='poten'/>4점</td>
	            <td><input type='radio' value='5' name='poten'/>5점</td>
	        </tr>
	        <tr>
	            <th>연봉 만족도</th>
	            <td><input type='radio' value='1' name="salary_satis"/>1점</td>
	            <td><input type='radio' value='2' name="salary_satis"/>2점</td>
	            <td><input type='radio' value='3' name="salary_satis"/>3점</td>
	            <td><input type='radio' value='4' name="salary_satis"/>4점</td>
	            <td><input type='radio' value='5' name="salary_satis"/>5점</td>
	        </tr>
		
		
		</table>
	</form>
	
	<div>
		<button>cancle</button>
		<button id="btnSubmit">submit</button>
	</div>
</section>
<script type="text/javascript">
	document.getElementById("btnSubmit").onclick = (e) =>{
		document.companyReviewEnrollFrm.submit();
	};
</script>
<%@ include file="/WEB-INF/views/common/commonFooter.jsp" %>