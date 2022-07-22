<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<input type="password" name="nextPwCheck" id="#nextPw" required /> 
			<label for="password">새 비밀번호 확인</label>
		</div>
		<div id="check">
			<input type="submit" value="확인" />	
		</div>

</form>
</section>
<script>
/**
 * 
 */
 
	document.updatePwRFrm.onsubmit = (e) => {
		const prevPw = document.querySelector("#prevPw");
		const nextPw = document.querySelector("#nextPw");
		const re = /^[a-zA-Z0-9!@#$%^&*()]{4,}$/; 
		if(!re.test(prevPw.value)){
			alert("비밀번호는 영문자/숫자/!@#$%^&*()로 최소 4글자이상이어야 합니다.");
			prevPw.select();
			return false;
		}
		if(!re.test(nextPw.value)){
			alert("새 비밀번호는 영문자/숫자/!@#$%^&*()로 최소 4글자이상이어야 합니다.");
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