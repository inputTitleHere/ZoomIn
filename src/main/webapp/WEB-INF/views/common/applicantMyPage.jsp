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
<title>Insert title here</title>
</head>
<body>
<div class="mypage-content-top">
	<div class="mypage-container">
		<div class="mypage-content-large">
			<h3>마이페이지</h3>
			<!-- 수정가능한 회원정보 이름, 핸드폰, 이메일 -->
		</div>
	</div>
	<div>
		<form id="updateFrm" name="updateFrm" action="<%=request.getContextPath() %>/applicant/update" method="POST">
		<p>
			<label>ID</label>
			<input class="id" type="text" name="id" id="id" value="<%= amember.getId() %>" />
		</p>
		<p>
			<label>Name</label>
			<input type="name" name="name" id="name" value="<%= amember.getName() %>" />
		</p>
		<p>
			<label>Phone</label>
			<input type="phone" name="phone" id="phone" placeholder="010제외 뒷자리 숫자만 적어주세요" value="<%= amember.getPhone() %>" />
		</p>
		<p>
			<label>Email</label>
			<input type="email"  name="email" id="email" value="<%= amember.getEmail() %>" />
		</p>
		<p class="update-btn">
			<input type="submit" value="회원정보수정" />
			<input type="button" value="비밀번호 변경" onclick="updatePassword();" />
			<input type="button" value="회원 탈퇴" onclick="deleteApplicant();"/>
		</p>
		</form>
	</div>
	<form name="deleteFrm" method="POST" action="<%= request.getContextPath() %>/applicant/delete">
		<input type="hidden" name="id" value="<%= amember.getId() %>" />
	</form>
</div>
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
 
const updatePassword = () => {
	location.href = "<%= request.getContextPath() %>/applicant/updatePw";
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