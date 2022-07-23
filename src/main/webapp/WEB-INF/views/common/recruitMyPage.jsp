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
<title>Insert title here</title>
</head>
<body>
<div class="mypage-content-top">
	<div class="mypage-container">
		<div class="mypage-content-large">
			<h3>마이페이지</h3>
			<!-- 회원정보수정x. 비밀번호변경만O -->
		</div>
	</div>
	<div>
		<form id="myFrm" action="<%=request.getContextPath() %>/recruit/myPage" method="POST">
		<p>
			<label>ID</label>
			<input class="inputR" type="text" name="id" id="id" value="<%= rmember.getId() %>" />
		</p>
		<p>
			<label>Name</label>
			<input type="text" type="text" name="name" id="name" value="<%= rmember.getName() %>" />
		</p>
		<p>
			<label>사업자등록번호</label>
			<input type="text" type="text" name="companyNo" id="companyNo" value="<%= rmember.getCompanyNo() %>" />
		</p>
		<p>
			<label>Email</label>
			<input type="text" type="text" name="email" id="email" value="<%= rmember.getEmail() %>" />
		</p>
		<p class="update-btn">
			<input type="button" value="비밀번호 변경" onclick="updatePassword();" />
			<input type="button" value="회원 탈퇴" onclick="deleteRecruit();"/>
		</p>
		</form>
	</div>
	<form name="deleteFrm" method="POST" action="<%= request.getContextPath() %>/recruit/delete">
		<input type="hidden" name="id" value="<%= rmember.getId() %>" />
	</form>
</div>
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