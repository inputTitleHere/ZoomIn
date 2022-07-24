<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
RecruitMember rmember = (RecruitMember) session.getAttribute("loginMember");

String msg = (String) session.getAttribute("msg");
if(msg != null) session.removeAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/myPageNav.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/myPage.css" />
</head>
<body>
<main class="main">
	<aside class="sidebar">
		<nav class="nav">
			<ul>
				<li class="active"><a href="<%= request.getContextPath() %>/applicant/updatePw">패스워드 변경</a></li>
				<li><a href="<%= request.getContextPath() %>/applicant/delete">회원 탈퇴</a></li>
			</ul>
		</nav>
	</aside>
</main>
<!-- 회원정보수정x. 비밀번호변경만O -->
<section class="mypage">
	<div class="container">
		<div class="mypage-content">
			<form id="updateFrm" class="updateFrm" action="<%=request.getContextPath() %>/recruit/myPage" method="POST">
				<h2>회원정보 수정</h2>
				<div class="textFrm">
					<input class="id" type="text" name="id" id="id" value="<%= rmember.getId() %>" />
				</div>
				<div class="textFrm">
					<input type="text" class="name" type="text" name="name" id="name" value="<%= rmember.getName() %>" />		
				</div>
				<div class="textFrm">
					<input type="text" class="companyNo" type="text" name="companyNo" id="companyNo" value="<%= rmember.getCompanyNo() %>" />		
				</div>
				<div class="textFrm">
					<input type="text" class="email" type="text" name="email" id="email" value="<%= rmember.getEmail() %>" />
				</div>
				<div class="btn-group">
					<input type="button" id="btn-change" value="패스워드 수정" onclick="updatePassword();" />
					<input type="button" id="btn-delete" value="회원 탈퇴" onclick="deleteRecruit();"/>
				</div>
		</form>
	</div>
<section>
	<form name="deleteFrm" method="POST" action="<%= request.getContextPath() %>/recruit/delete">
		<input type="hidden" name="id" value="<%= rmember.getId() %>" />
	</form>
</section>
<script>
window.addEventListener('load',()=>{
	<% if(msg!=null){%>
	alert('<%=msg%>');
	<%}%>
});

const updatePassword = () => {
	location.href = "<%= request.getContextPath() %>/recruit/updatePw";
};

/**
 * 서블릿으로 보내짐 
 jsp -> deleteFrm 전달 (정말 탈퇴하겠습니까? -> 탈퇴되었습니다 흐름)
 */
 const deleteRecruit = () => {
	 if(confirm("정말로 탈퇴하시겠습니까?")) {
		 document.deleteFrm.submit();		 
	 }
 };

</script>
</body>
</html>