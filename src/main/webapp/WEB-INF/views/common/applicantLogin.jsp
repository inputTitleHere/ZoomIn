<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember" %>
<%@ page import="com.kh.zoomin.member.dto.Member" %>


<%
	request.setCharacterEncoding("UTF-8");
	ApplicantMember amember = null;
	String id = request.getParameter("member_id");
	String pw = request.getParameter("member_name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구직자 로그인</title>
</head>
<!-- /applicant/login 서블릿 이동 -->
<body>
	<header>
		<div id="account">
			<a href="recruiterLogin.jsp" id="logIn">구인자 로그인</a> <a
				href="applicantLogin.jsp" id="logIn">구직자 로그인</a> <a href=""
				id="recruiterAsign">구인자 회원가입</a> <a href="" id="applicantAsign">구직자
				회원가입</a>
		</div>
		<div id="logodiv">
			<img id="logo" alt=""
				src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
		</div>
		<div id="searchBoxdiv">
			<input id="searchBox" type="text">
			<button class="custom-btn btn-3">
				<span>검색</span>
			</button>
		</div>
	</header>
	<div id="container">
		<tbody>
			<div class="login-container">
				<% if(loginMember.getMemberType() == 2) { %>
				<!-- 로그인폼 시작 -->
				<form id="applicantFrm" name="applicantFrm"
					action="<%= request.getContextPath() %>/applicant/login"
					method="POST">
					<fieldset id="inputs">
						<td><input id="username" type="text"
							onblur="if(this.value=='')this.value='Username';"
							onfocus="if(this.value=='Username')this.value='';"
							value="Username" /> <input id="password" type="text"
							onblur="if(this.value=='')this.value='Password';"
							onfocus="if(this.value=='Password')this.value='';"
							value="Password" /></td>
					</fieldset>
					<fieldset id="actions">
						<input type="button" value="구인자 로그인"
							onclick="location.href='<%= request.getContextPath() %>/';">
					</fieldset>
				</form>
				<!-- 로그인 폼 끝 -->
				<% } else { %>
				<table id="login">
				<tr>
					<td>[<%= loginMember.getMemberName() %>]님, 안녕하세요.</td>
				</tr>
				</table>
				<% } %>
			</div>
		</tbody>

	</div>

</body>
</html>