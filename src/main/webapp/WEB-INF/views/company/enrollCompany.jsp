<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
RecruitMember rmember = (RecruitMember)request.getAttribute("rmember");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 회사 등록</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/enrollCompany.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>

<div class="content-wrapper">
<form action="<%=request.getContextPath() %>/company/enrollCompany" method="post" class="enrollCompanyFrm" id="enroll-company-frm" name="enroll-company-frm">
	<h2>신규 회사 등록</h2>
	<div class="textFrm">
		<input type="text" value="<%=rmember.getCompanyNo() %>" id="companyNo" name="companyNo" placeholder="사업자등록번호" />
	</div>
	<div class="textFrm">
		<input type="text" id="companyName" name="companyName" placeholder="회사명" />
	</div>
	<div class="infoFrm">
		<textarea rows="10" cols="30" name="companyInfo" id="companyInfo" placeholder="회사에 대한 간단한 설명"></textarea>
	</div>
	<div class="btn-company">
		<button id="btn-company" value="등록하기">등록하기</button>
	</div>
</form>

</div>



</body>
</html>