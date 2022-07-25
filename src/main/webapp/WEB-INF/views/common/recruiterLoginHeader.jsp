<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/recruiter/recruitLoginHeader.css"/>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<header>
	<div style="width:168%" id="account">
		<div id="menu">
			<ul class="main">
				<li>
					<span class="menu">MENU</span>
					<ul>
						<li><a href="<%= request.getContextPath() %>/recruit/myPage" id="myPage">마이페이지</a></li>
						<li><a href="<%= request.getContextPath() %>/recruit/logout" id="lougout">로그아웃</a></li>
					</ul>
				</li>
			</ul>
		</div>
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
