<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	List<CompanyReview> list = (List<CompanyReview>) request.getAttribute("list");
	List<CompanyReview> board = (List<CompanyReview>) request.getAttribute("board");
	CompanyReviewExt companyReviewExt = (CompanyReviewExt) request.getAttribute("ext");
	CompanyReviewExt cre = new CompanyReviewExt();
	
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/companyReview.css"/>
<section id="companyReview-container">
	<h1>-회사 리뷰 게시판-</h1>
	<div id="companyReview-btn-add">
		<button onclick="location.href='<%= request.getContextPath() %>/review/company/companyReviewEnroll'">리뷰 등록하기</button>
	</div>
	<div id="companyReview-btn-update">
		<button onclick="location.href='<%= request.getContextPath() %>/review/company/companyReviewUpdate'">리뷰 수정하기</button>
	</div>
	<br />
	<table class="company-review-list">
		<tr>
			<th>번호</th>
			<th>회사명</th>
			<th>분야</th>
			<th>별점</th>
			<!-- <th>워라벨</th>
			<th>승진 가능성</th>
			<th>업무 강도</th>
			<th>회사 잠재력</th>
			<th>연봉 만족도</th> -->
			<th>내용</th>
			<th>등록 날짜</th>
		</tr>	
<%
	if(list != null && !list.isEmpty()){
		for(CompanyReview companyReview : list){
%>
		<tr>
			<td><a href="<%= request.getContextPath() %>/review/company/companyReviewBoard"><%= companyReview.getNo() %></a></td>
			<td>
				<% if(companyReview.getCompanyNo() == cre.getCompanyNo()) %>
					
					<%= cre.getCompanyName() %>
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
				<% if(companyReview.getCategoryNumber()==110) %>교육업
			</td>
			<td><%= companyReview.getStars() %></td>
			<%-- <td><%= companyReview.getWorkLifeBalance() %></td>
			<td><%= companyReview.getLevelUp() %></td>
			<td><%= companyReview.getWorkIntensity() %></td>			
			<td><%= companyReview.getPotential() %></td>
			<td><%= companyReview.getSalarySatisfaction() %></td> --%>
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

<br />
	<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
	</div>

</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>