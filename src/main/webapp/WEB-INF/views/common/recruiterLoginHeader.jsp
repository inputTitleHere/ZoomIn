<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />

<body>
<header>
	<div id="account">
		<a href="<%= request.getContextPath() %>/applicant/mypage" id="userInfo">회원정보</a>
		<a href ="<%= request.getContextPath() %>/applicant/logout" id="logOut">로그아웃</a>
	</div>
	<div id="logodiv">
		<a href="<%=request.getContextPath()%>/recruit/board/recruitBoardList">
			<img id="logo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
		</a>
	</div>
	<div id="searchBoxdiv">
		<input id="searchBox" type="text">
		<button class="custom-btn btn-3"><span>검색</span></button>
	</div>
</header>
