<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String) session.getAttribute("msg");
	System.out.println("msg@jsp = " + msg);
	if(msg != null) session.removeAttribute("msg"); 
	Member loginMember = (Member) session.getAttribute("loginMember");
	
	String saveId = null;
	Cookie[] cookies = request.getCookies();
	for(Cookie c : cookies){
		String name = c.getName();
		String value = c.getValue();
		System.out.println("[cookie] " + name + "=" + value);
		if("saveId".equals(name)){
			saveId = value;
		}
	}

 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zoomin</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />




</head>
<body>



