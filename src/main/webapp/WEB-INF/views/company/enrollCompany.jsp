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
</head>
<body>

<div class="content-wrapper">
<h1>신규 회사 등록</h1>
<form action="<%=request.getContextPath() %>/company/enrollCompany" method="post" id="enroll-company-frm" name="enroll-company-frm">
	<label for="companyNo">사업자 등록 번호 : </label>
	<input type="text" value="<%=rmember.getCompanyNo() %>" id="companyNo" name="companyNo" />
	<br />
	<label for="companyName">회사명 : </label>
	<input type="text" id="companyName" name="companyName" />
	<br />
	<label for="companyInfo">회사에 대한 간단한 설명 : </label>
	<textarea rows="10" cols="30" name="companyInfo" id="companyInfo"></textarea>
	
	<button>회사 등록하기</button>
</form>

</div>



</body>
</html>