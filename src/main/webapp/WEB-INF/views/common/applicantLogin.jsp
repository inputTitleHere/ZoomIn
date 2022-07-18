<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember" %>
<%@ page import="com.kh.zoomin.applicant.member.model.dao.ApplicantDao" %>
<%@ page import="com.kh.zoomin.member.dto.Member" %>

<%
	request.setCharacterEncoding("UTF-8");
	
	String id= request.getParameter("id");
	String password = request.getParameter("password");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구직자 로그인</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<!-- /applicant/login 서블릿 이동 -->
<body>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<div class="jumbotron">
	<div class="container">
		<h1 class="display-3">로그인</h1>
	</div>
</div>
<div class="container" align="center">
	<div class="col-md-4 col-me-offset-4">
		<h3 class="form-signin-heading">Please sign in</h3>

		<form class="form-signin" action="<%= request.getContextPath() %>/applicant/login" method="post">
			<div class"form-group">
				<label class="sr-only">User Name</label>
				<input name="id" class="form-control" placeholder="ID" required autofocus>
			</div>
			<div class="form-group">
				<label class="sr-only">Password</label>
				<input type="password" name="password" class="form-control" placeholder="Password" required>
			</div>
			<button class="btn btn-lg btn-success btn-block" type="submit">로그인</button>
			<button class="btn btn-lg btn-secondary btn-block" type="button" onclick="location.href='addMember.jsp'">회원가입</button>
		</form>
	</div>
</div>


</body>
</html>