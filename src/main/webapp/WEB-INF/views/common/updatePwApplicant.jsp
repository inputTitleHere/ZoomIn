<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ApplicantMember amember = (ApplicantMember) session.getAttribute("loginMember");

String msg = (String) session.getAttribute("msg");
if(msg != null) session.removeAttribute("msg");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 변경</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/updatePw.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
<div class="container">
	<div class="collg"></div>
	<div class="collg"></div>
		<div class="form-group-container">
			<form name="updatePwAFrm" class="changePwFrm" action="<%=request.getContextPath()%>/applicant/updatePw" method="POST">
			<h2>패스워드 변경</h2>
			<div class="form-group">
				<input type="password" name="prevPw" id="prevPw" placeholder="기존 패스워드" required /> 
			</div>
			<div class="form-group">
				<input type="password" name="nextPw" id="nextPw" placeholder="새 패스워드" required /> 
			</div>
			<div class="form-group">
				<input type="password" name="nextPwCheck" id="nextPwCheck" placeholder="패스워드 확인" required /> 
			</div>
			<div class="btn-group">
				<input type="submit" id="btn-check" value="수정" />	
				<input type="reset" id="btn-reset" value="취소" />	
				<input type="hidden" value="<%= amember.getId() %>" name="id" id="id" />
			</div>
		</form>
	</div>
</div>
<script>
window.addEventListener('load',()=>{
	<% if(msg!=null){%>
	alert('<%=msg%>');
	<%}%>
});
/**
 * 유효성검사
 */
 
document.updatePwAFrm.onsubmit = (e) => {
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