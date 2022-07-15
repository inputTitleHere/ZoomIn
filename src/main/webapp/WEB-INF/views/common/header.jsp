<%@page import="com.oreilly.servlet.CookieNotFoundException"%>
<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String msg = (String) session.getAttribute("msg");
if (msg != null)
	session.removeAttribute("msg");
Member loginMember = (Member) session.getAttribute("loginMember");

String saveId = null;
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie c : cookies) {
		String name = c.getName();
		String value = c.getValue();
		// System.out.println("[cookie] " + name + "=" + value);
		if ("saveId".equals(name)) {
			saveId = value;
		}
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zoom인</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common/common.css" />

<%
// 여기서 로그인 관련 처리를 수행한다.
// if not login(by any means) then show login option
// else show respective menus of either recruiter or applicant.

// 

if (loginMember == null) {
%>
<%@ include file="/WEB-INF/views/common/noLoginHeader.jsp"%>
<%
} else if (loginMember.getMemberType() == 1) {
%>
<%@ include file="/WEB-INF/views/common/recruiterLoginHeader.jsp"%>
<%
} else if (loginMember.getMemberType() == 2) {
%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp"%>
<%
}
%>


</head>
<body>