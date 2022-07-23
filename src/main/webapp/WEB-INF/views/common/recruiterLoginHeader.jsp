<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />

<body>
<header>
	<div id="account">
		<a href="<%= request.getContextPath() %>/recruit/myPage" id="myPage">마이페이지</a>
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
