<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
RecruitMember rm = (RecruitMember) session.getAttribute("loginMember");

String msg = (String) session.getAttribute("msg");
if(msg != null) session.removeAttribute("msg");


%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
	<h2>비밀번호 변경</h2>
</header>
<section>
<form name="updatePwRFrm" action="<%=request.getContextPath()%>/recruit/updatePw" method="POST">
		<div class="">
			<input type="password" name="prevPw" id="prevPw" required /> 
			<label for="password">기존 비밀번호</label>
		</div>
		<div class="">
			<input type="password" name="nextPw" id="nextPw" required /> 
			<label for="password">새 비밀번호</label>
		</div>
		<div class="">
			<input type="password" name="nextPwCheck" id="#nextPwCheck" required /> 
			<label for="password">새 비밀번호 확인</label>
		</div>
		<div id="check">
			<input type="submit" value="확인" />	
		</div>
<input type="hidden" value="<%= rm.getId() %>" name="id" id="id" />
</form>
</section>
<script>
window.addEventListener('load',()=>{
	<% if(msg!=null){%>
	alert('<%=msg%>');
	<%}%>
});
/**
 * 유효성 검사
 */
 
	document.updatePwRFrm.onsubmit = (e) => {
		const prevPw = document.querySelector("#prevPw");
		const nextPw = document.querySelector("#nextPw");
		const re = /^.{4,}$/; 
		if(!re.test(prevPw.value)){
			alert("비밀번호는 최소 4글자이상이어야 합니다.");
			prevPw.select();
			return false;
		}
		if(!re.test(nextPw.value)){
			alert("새 비밀번호는 최소 4글자이상이어야 합니다.");
			nextPw.select();
			return false;
		}
		const nextPwCheck = document.querySelector("#nextPwCheck");
		if(nextPw.value !== nextPwCheck.value){
			alert("비밀번호가 일치하지 않습니다.");
			nextPw.select();
			return false;
		}
	};
</script>
</body>
</html>