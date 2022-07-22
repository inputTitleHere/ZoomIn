<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>


<div class="title" id="subtitle">
	<a href="<%=request.getContextPath() %>/recruit/board/recruitBoardList" id="recruitBoard">등록한 채용글</a>
	<a href="<%=request.getContextPath() %>/recruit/review/recruitReviewList?companyNo=<%=rm.getCompanyNo() %>" id="companyInfo">회사 리뷰</a>
	<a href="<%=request.getContextPath() %>/recruit/headhunt/headhuntList" id="headhunt">헤드헌팅</a>
</div>

<style>
.title{
	display:flex;
	justify-content:space-around;
}
</style>
