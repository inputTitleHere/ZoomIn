<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script>
window.onload = () => {
	
<% if(msg != null) { %>
	alert("<%= msg %>");
<% } %>

//구인자 로그인 유효성 
<% if((int) session.getAttribute("memberType") == 1) { %>
	document.applicantFrm.onsubmit = (e) => {
		const memberId = document.querySelector("#memberId");
		const password = document.querySelecotr("#password");
		
		//db에 입력된 아이디 최소글자가 3글자라 3글자 이상으로 설정
		if(!/^.{3,}$/.test(memberId.value)) {
			alert("유효한 아이디를 입력해주세요");
			memberId.select();
			return false;
		}
		
		//비번 1234통일했으므로 4글자 이상으로 설정
		if(!/^.{4,}$/.test(password.value)) {
			alert("유효한 비밀번호를 입력해주세요.");
			password.select();
			return false;
		}
	 };	

<% } %>
};
</script>
<head>
<meta charset="UTF-8">
<title>구인자 로그인</title>
</head>
<body>
<header>
	<div id="account">
		<a href ="" id="logIn">로그인</a>
		<a href ="" id="recruiterAsign">구인자 회원가입</a>
		<a href ="" id="applicantAsign">구직자 회원가입</a>
	</div>
	<div id="logodiv">
		<img id="logo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
	</div>
	<div id="searchBoxdiv">
		<input id="searchBox" type="text">
		<button class="custom-btn btn-3"><span>검색</span></button>
	</div>
</header>
<section>
<!-- 구인자 로그인 폼 시작 -->
	<form id="applicantFrm" name="applicantFrm" action="<%= request.getContextPath() %>/applicant/login" method="POST">
		<fieldset id="inputs">
	        <input id="username" type="text" onblur="if(this.value=='')this.value='Username';" onfocus="if(this.value=='Username')this.value='';" value="Username" />
	        <input id="password" type="text" onblur="if(this.value=='')this.value='Password';" onfocus="if(this.value=='Password')this.value='';" value="Password" />
	    </fieldset>
	    <fieldset id="actions">
	        <input type="button" value="구인자 로그인" 
	        	onclick="location.href='<%= request.getContextPath() %>/';"><!-- 여기부터 해야함 -->
	       
	    </fieldset>
	</form>

</section>