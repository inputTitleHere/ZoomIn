<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember" %>
<%@ page import="com.kh.zoomin.applicant.member.model.dao.ApplicantDao" %>
<%@ page import="com.kh.zoomin.member.dto.Member" %>

<script>
function loginCheck() {
	const id = document.querySelector("#id");
	if(!/^[가-힣a-zA-Z0-9]{3,}$/.test(id.value)){
		alert("유효한 아이디를 입력해주세요.");
		id.select();
		return false;
	}
		
	const password = document.querySelector("#password");
	if(!/^.{4,}$/.test(password.value)){
		alert("유효한 비밀번호를 입력해주세요.");
		password.select();
		return false;
	}
	else {
		return true;
	}
		
};

</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구직자 로그인</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/simpleLogin.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
<%
String msg = (String) session.getAttribute("msg");
if(msg != null) session.removeAttribute("msg"); 
%>
<script>
window.addEventListener('load',()=>{
	<% if(msg!=null){%>
	alert('<%=msg%>');
	<%}%>
});
</script>
<div class="container">
	<div class="collg"></div>
	<div class="collg"></div>
		<div class="form-group-container">
			<form name="loginAFrm" class="loginFrm" method="POST" action= "<%= request.getContextPath() %>/applicant/login" />
				<h3> 로그인 화면 </h3>
				<div class="form-group">
					<input type="text" class="Aid" id="id" name="id" placeholder="아이디를 입력해주세요." maxlength="20">
				</div>
				<div class="form-group">
					<input type="password" class="Apassword" id="password" name="password" name="password" placeholder="비밀번호를 입력해주세요.">
				</div>
				<div class="btn-group">
					<button type="submit" id="login-box" onclick="return loginCheck()">로 그 인</button>
					<button id="btn-join" type="button" onclick="location.href='<%= request.getContextPath() %>/applicant/join';">회원가입</button>
				</div>
			</form>
		</div>
</div>


</body>
</html>