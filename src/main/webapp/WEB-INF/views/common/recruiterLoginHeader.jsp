<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="com.kh.zoomin.recruit.member.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />

<%
RecruitMember recruitMember = (RecruitMember)session.getAttribute("loginMember"); // 여기까지 왔으면 일단 loginMember는 있을 것.
//System.out.println("@recruiterLoginHeader.jsp : UID = "+recruitMember.getUid());
%>

<body>
<header>
	<div id="account">
		<a href="" id="userInfo">회원정보수정</a>
		<a href ="" id="logOut">로그아웃</a>
	</div>
	<div id="logodiv">
		<a href="<%=request.getContextPath()%>/WEB-INF/views/recruit/recruitIndex.jsp">
			<img id="logo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
		</a>
	</div>
	<div id="searchBoxdiv">
		<input id="searchBox" type="text">
		<button class="custom-btn btn-3"><span>검색</span></button>
	</div>
</header>
