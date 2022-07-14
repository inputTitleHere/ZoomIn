<%@page import="com.kh.zoomin.member.dto.Member"%>
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
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script>
window.onload = () => {
	
<% if(msg != null) { %>
	alert("<%= msg %>");
<% } %>

//null일 경우 기능 사용못하게 팝업창 떠야함 
<% if(loginMember.getMemberType() == null) { %>

	 };	

	 //해당 부분 추후 구직자란으로 옮길것임. 해당란에 잘못적음 
<% } else if(loginMember.getMemberType() == 2) { %>
	document.RecruitLoginFrm.onsubmit = (e) => {
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
<%
// 여기서 로그인 관련 처리를 수행한다.
// if not login(by any means) then show login option
// else show respective menus of either recruiter or applicant.


// 

if(loginMember == null){
%>
<%@ include file="/WEB-INF/views/common/noLoginHeader.jsp" %>
<%
}else if(loginMember.getMemberType() == 1){
%>
<%@ include file="/WEB-INF/views/common/recruiterLoginHeader.jsp" %>
<%
}else if(loginMember.getMemberType() == 2){
%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
}
%>


</head>
<body>



