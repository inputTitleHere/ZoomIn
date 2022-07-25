<%@page import="com.kh.zoomin.company.dto.Company"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
	CompanyReview companyReview = (CompanyReview) request.getAttribute("companyReview");
	Company company = (Company) request.getAttribute("company");
	Member applicantM = (Member) session.getAttribute("loginMember");
	ApplicantMember applicant = null;
	
	if(applicantM instanceof ApplicantMember){
		applicant = (ApplicantMember) applicantM;
	}
	
	if(applicantM != null && applicantM.getMemberType()==2){
%>
		<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%		
	}else{
%>
		<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%		
	}
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/companyReview.css"/>

<section id="company-review-view-container">
	<h1>리뷰 상세보기</h1>
<%
	System.out.println("applicantMember + "  + applicant);
	if(applicantM != null && applicantM.getMemberType()==2){
		if((applicant != null && companyReview.getUid() == applicant.getUid())){
%>
	<div class="edit-button">
		<button id="update-button1" onclick="updateBoard()">수정</button>
		<button id="update-button2" onclick="deleteBoard()">삭제</button>
	</div>
<%			
		}
	}
%>
	
	<table class="company-review-list">
		<tr>
			<th>회사명</th>
			<td>
				<%= company.getCompanyName() %>
			</td>
		</tr>			
		<tr>
			<th>분야</th>
			<td>
				<% if(companyReview.getCategoryNumber()==1) %>인사
				<% if(companyReview.getCategoryNumber()==2) %>회계/총무
				<% if(companyReview.getCategoryNumber()==3) %>마케팅
				<% if(companyReview.getCategoryNumber()==4) %>영업
				<% if(companyReview.getCategoryNumber()==5) %>생산/관리
				<% if(companyReview.getCategoryNumber()==6) %>연구개발
				<% if(companyReview.getCategoryNumber()==7) %>기술
				<% if(companyReview.getCategoryNumber()==8) %>서비스
				<% if(companyReview.getCategoryNumber()==9) %>IT/인터넷
			</td>
		</tr>
		<tr>	
			<th>별점</th>
			<td><%= companyReview.getStars() %></td>
		</tr>
		<tr>	
			<th>워라벨</th>
			<td><%= companyReview.getWorkLifeBalance() %></td>
		</tr>
		<tr>	
			<th>승진 가능성</th>
			<td><%= companyReview.getLevelUp() %></td>
		</tr>
		<tr>	
			<th>업무 강도</th>
			<td><%= companyReview.getWorkIntensity() %></td>
		</tr>
		<tr>	
			<th>회사 잠재력</th>
			<td><%= companyReview.getPotential() %></td>
		</tr>
		<tr>	
			<th>연봉 만족도</th>
			<td><%= companyReview.getSalarySatisfaction() %></td>
		</tr>
		<tr>	
			<th>내용</th>
			<td><%= companyReview.getContent() %></td>
		</tr>
		<tr>	
			<th>등록 날짜</th>
			<td><%= companyReview.getRegDate() %></td>
		</tr>	
		

</table>
</section>
<form 
	action="<%= request.getContextPath() %>/review/company/companyReviewDelete" 
	method="post"
	name="companyReviewDelFrm">
	<input type="hidden" name="no" value="<%= companyReview.getNo() %>"/>
</form>
<script>
const deleteBoard = () => {
	if(confirm("삭제하시겠습니까?"))
		document.companyReviewDelFrm.submit();
};

const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/review/company/companyReviewUpdate?no=<%= companyReview.getNo() %>"
};
</script>
<%-- <% } %> --%>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>