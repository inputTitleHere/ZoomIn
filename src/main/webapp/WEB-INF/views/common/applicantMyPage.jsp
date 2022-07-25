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
<title>마이페이지</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/myPageNav.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/myPage.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
<main class="main">
	<aside class="sidebar">
		<nav class="nav">
			<ul>
				<li class="active"><a href="#">회원정보수정</a></li>
				<li><a href="<%= request.getContextPath() %>/applicant/updatePw">패스워드 변경</a></li>
			</ul>
		</nav>
	</aside>
</main>
<section class="mypage">
	<div class="container">
		<div class="mypage-content">
			<form id="updateFrm" class="updateFrm" name="updateFrm" action="<%=request.getContextPath() %>/applicant/update" method="POST">
				<h2>회원정보 수정</h2>
					<div class="textFrm">
						<input class="id" type="text" name="id" id="id" value="<%= amember.getId() %>" />
					</div>
					<div class="textFrm">
						<input type="name" class="name" name="name" id="name" value="<%= amember.getName() %>" />		
					</div>
					<div class="textFrm">
						<input type="phone" class="phone" name="phone" id="phone" placeholder="010제외 뒷자리 숫자만 적어주세요" value="<%= amember.getPhone() %>" />			
					</div>
					<div class="textFrm">
						<input type="email" class="email" name="email" id="email" value="<%= amember.getEmail() %>" />		
					</div>
					<div class="btn-group">
						<input type="submit" id="btn-change" value="회원정보수정" />
						<input type="button" id="btn-delete" value="회원 탈퇴" onclick="deleteApplicant();"/>
					</div>
			</form>
		</div>
	</div>
</section>
	<form name="deleteFrm" method="POST" action="<%= request.getContextPath() %>/applicant/delete">
		<input type="hidden" name="id" value="<%= amember.getId() %>" />
	</form>
			<!-- 수정가능한 회원정보 이름, 핸드폰, 이메일 -->

<script>
window.addEventListener('load',()=>{
	<% if(msg!=null){%>
	alert('<%=msg%>');
	<%}%>
});
/**
 * 회원정보 변경 유효성 검사
 */
document.updateFrm.onsubmit = (e) => {
	const name = document.querySelector("#name");
	if(!/^[가-힣]{2,}$/.test(name.value)) {
		alert("한글로 2글자 이상 입력해주세요");
		name.select();
		return false;
	}
	 
	const phone = document.querySelector("#phone");
	if(!/^010[0-9]{8}$/.test(phone.value)) {
		alert("유효한 전화번호를 입력해주세요.");
		phone.select();
		return false;
	}
	const email = document.querySelector("#email");
	//if(!/^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_-]+)([a-zA-Z0-9_-]+(\.[A-Za-z0-9\_-]+){1,2}$/.test(email.value)) {
	if(!/^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/.test(email.value)) {
		alert("유효한 이메일을 입력해주세요.");
		return false;
	}
};

	/**
	 * ApplicantDeleteServlet으로 보내짐.
	 jsp -> deleteFrm 전달 (정말 탈퇴하겠습니까? -> 탈퇴되었습니다 흐름)
	 */
const deleteApplicant = () => {
	if(confirm("정말로 탈퇴하시겠습니까?")) {
		document.deleteFrm.submit();		 
	}
};
</script>

</body>
</html>