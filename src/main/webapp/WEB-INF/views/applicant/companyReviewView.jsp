<%@page import="com.kh.zoomin.company.dto.Company"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	CompanyReview companyReview = (CompanyReview) request.getAttribute("companyReview");
	Company company = (Company) request.getAttribute("company");
/* 	List<Company> company = (List<Company>) request.getAttribute("company"); */
	/* Member lm = (Member)session.getAttribute("loginMember");
	if(loginMember instanceof ApplicantMember){
		am = (ApplicantMember)session.getAttribute("loginMember");
	} */
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/companyReview.css"/>

<section id="company-review-view-container">
	<h1>리뷰 상세보기</h1>
	<!-- 작성자 / 관리자만 버튼이 보이게 함 / 맨 아래도 있음-->
	<%-- <%
		boolean canEdit = am != null &&(am.getId().equals(companyReview.getUid()) || am.getMemberType() == 0);
		if(canEdit){
	%> --%>
	<div class="edit-button">
		<button id="update-button1" onclick="updateBoard()">수정</button>
		<button id="update-button2" onclick="deleteBoard()">삭제</button>
	</div>
	<%-- <%
		}
	%> --%>
	
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
				<% if(companyReview.getCategoryNumber()==1) %>IT/웹/통신
				<% if(companyReview.getCategoryNumber()==2) %>미디어/디자인
				<% if(companyReview.getCategoryNumber()==3) %>의료/제약/복지	
				<% if(companyReview.getCategoryNumber()==4) %>건설업
				<% if(companyReview.getCategoryNumber()==5) %>서비스업
				<% if(companyReview.getCategoryNumber()==6) %>은행/금융업
				<% if(companyReview.getCategoryNumber()==7) %>유통/무역/운송
				<% if(companyReview.getCategoryNumber()==8) %>제조/화학
				<% if(companyReview.getCategoryNumber()==9) %>기관/협회
				<% if(companyReview.getCategoryNumber()==110) %>교육업
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
<%-- <% if(canEdit) {%> --%>
<form 
	action="<%= request.getContextPath() %>/review/company/companyReviewDelete" 
	method="post"
	name="companyReviewDelFrm">
	<input type="text" name="no" value="<%= companyReview.getNo() %>"/>
</form>
<script>
const deleteBoard = () => {
	if(confirm("삭제하시겠습니까?"));
		document.companyReviewDelFrm.submit();
};

const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/review/company/companyReviewUpdate?no=<%= companyReview.getNo() %>"
};
</script>
<%-- <% } %> --%>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>