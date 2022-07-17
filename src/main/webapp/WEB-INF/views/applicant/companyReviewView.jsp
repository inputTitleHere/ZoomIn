<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
	CompanyReview companyReview = (CompanyReview) request.getAttribute("companyReview");
	CompanyReviewExt companyReviewExt = (CompanyReviewExt) request.getAttribute("companyReviewExt"); 
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<section id="company-review-view-container">
	<h1>ReviewView.jsp회사 리뷰 페이지</h1>
	
</section>

<%@ include file="/WEB-INF/views/common/commonFooter.jsp" %>