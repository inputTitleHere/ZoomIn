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
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
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
<div class="container" align="center">
	<div class="collg"></div>
	<div class="collg"></div>
		<div class="form-group-container" style="padding-top: 20px;">
		<form name="loginAFrm" method="POST" action= "<%= request.getContextPath() %>/applicant/login" />
		<h3 style="text-allign: center;"> 로그인 화면 </h3>
			<div class="form-group">
				<input type="text" class="Aid" id="id" name="id" placeholder="Username" maxlength="20">
			</div>
			<div class="form-group">
				<input type="password" class="Apassword" id="password" name="password" name="password" placeholder="Password">
			</div>
			<button type="submit" class="btn-login" onclick="return loginCheck()">로그인</button>
			<button class="btn-Ajoin" type="button" onclick="location.href='<%= request.getContextPath() %>/applicant/join';">회원가입</button>
		</form>

	</div>
</div>


</body>
</html>