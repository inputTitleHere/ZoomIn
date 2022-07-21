<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.zoomin.recruit.member.RecruitMember" %>
<%@ page import="com.kh.zoomin.recruit.member.model.dao.RecruitDao" %>
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
<title>구인자 로그인</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/recruiterLoginHeader.jsp"%>

	<div class="container" align="center">
		<div class="collg-4"></div>
		<div class="collg-4"></div>
		<div class="jumbotron" style="padding-top: 20px;">
			<form method="POST" action="<%=request.getContextPath()%>/recruit/login" />
			<h3 style="text-allign: center;">로그인 화면</h3>
			<div class="form-group">
				<input type="text" class="form-control" name="id"
					placeholder="Username" maxlength="20">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" name="password"
					placeholder="Password" maxlength="20">
			</div>
			<button type="submit" class="btn-btn-primary form-control">로그인</button>
			<button class="btn btn-lg btn-secondary btn-block" type="button"
				onclick="location.href='<%= request.getContextPath() %>/recruit/join';">회원가입</button>
			</form>
		</div>
	</div>
</body>
</html>