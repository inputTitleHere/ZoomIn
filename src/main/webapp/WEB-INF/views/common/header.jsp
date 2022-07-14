<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />

<%
// 여기서 로그인 관련 처리를 수행한다.
// if not login(by any means) then show login option
// else show respective menus of either recruiter or applicant.


// 
Member loginMember = (Member)session.getAttribute("loginMember");

if(loginMember==null){
%>
<%@ include file="/WEB-INF/views/common/noLoginHeader.jsp" %>
<%
}else if(loginMember.getMemberType()==1){
%>
<%@ include file="/WEB-INF/views/common/recruiterLoginHeader.jsp" %>
<%
}else if(loginMember.getMemberType()==2){
%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
}
%>


</head>
<body>



