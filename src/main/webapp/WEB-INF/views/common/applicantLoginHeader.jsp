<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
	<div id="account">
		<a href ="applicantLogin.jsp" id="writeResume">이력서 작성</a>
		<a href ="" id="info">회원정보</a>
		<a href ="" id="logOut">로그아웃</a>
	</div>
	<div id="logodiv">
		<img id="logo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
	</div>
	<div id="searchBoxdiv">
		<input id="searchBox" type="text">
		 <button class="custom-btn btn-3"><span>검색</span></button>
	</div>


