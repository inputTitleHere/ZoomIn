<%@page import="java.util.List"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>

<%
	List<CompanyReview> companyReviewList = (List<CompanyReview>) request.getAttribute("companyReivewList");
%>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<div class="write-companyReview-wrapper">
	<button id="write-button" onclick="location.href='<%= request.getContextPath() %>/review/company/companyReviewEnroll'">회사 리뷰 등록하기</button>
</div>
<section id="companyReview-view-container">
<h1>회사 리뷰 목록</h1>
	<table id="company-review-list">
		
	</table>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>