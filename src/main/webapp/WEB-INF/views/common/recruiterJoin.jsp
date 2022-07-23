<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>기업 회원가입</h2>
	<div>
		<form name="joinRFrm" id="joinRFrm" action="" method="POST">
		<table>
			<tr>
				<th>사업자등록번호<sup></sup></th>
				<td><input type="text" name="companyNo" id="companyNo" value=""
					placeholder="(-)없이 숫자만 입력" maxlength="10" required><br>
				</td>
			</tr>
			<tr>
				<th>이름<sup></sup></th>
				<td><input type="text" name="name" id="name" value="" required><br>
				</td>
			</tr>
			<tr>
				<th>아이디<sup></sup></th>
				<td><input type="text" placeholder="3글자이상" name="id" id="rId"
					value="" required> <input type="button" value="중복검사"
					onclick="checkIdRecruiter();" /> <input type="hidden" id="idValid"
					value="0" /></td>
			</tr>
			<tr>
				<th>비밀번호<sup></sup></th>
				<td><input type="password" name="password" id="rPassword"
					value="" placeholder="숫자와 특수문자를 포함 4글자 이상" required><br>
				</td>
			</tr>
			<tr>
				<th>비밀번호 재입력<sup></sup></th>
				<td><input type="password" id="passwordCheck" value=""
					required><br></td>
			</tr>
			<tr>
				<th>이메일주소</th>
				<td><input type="email" placeholder="@이후 메일주소까지 작성"
					name="email" id="email" value=""><br></td>
			</tr>
		</table>
		<input type="submit" value="가입"> <input type="reset"
			value="취소">
		</form>
	</div>
	<section>
		<form action="<%=request.getContextPath()%>/recruit/checkId"
			name="checkIdRFrm">
			<input type="hidden" name="id" />
		</form>
		<script>
	//사용자가 입력한 id 중복여부 검사 시작 
	const checkIdRecruiter = () => {
		const id = document.querySelector("#rId");
		if(!/^[a-zA-Z0-9]{3,}$/.test(id.value)){
			alert("유효한 아이디를 입력해주세요.");
			id.select();
			return;
	}
		
		const title = "checkIdRFrm";
		const spec = "width=430px, height=170px"; 
		//나중에 css로 글씨 정중앙 수정필요
		const popup = open("", title, spec);
		
		const frm = document.checkIdRFrm;
		frm.target = title;
		frm.id.value = id.value;
		frm.submit();
		
};

document.querySelector("#passwordCheck").onblur = (e) => {
	const password = document.querySelector("#rPassword");
	const passwordCheck = e.target;
	if(password.value != passwordCheck.value) {
		alert("비밀번호가 일치하지 않습니다.");
		password.select();
	}
};

//중복검사 클릭->중복검사 전 idvalid가 0/중복검사 ok는 1 
document.querySelector("#rId").onchange = (e) => {
	document.querySelector("#idValid").value = 0;
};

//폼 유효성 검사. 사업자등록번호 유효성검사 해야함.. 
document.joinAFrm.onsubmit = (e) => {
	const idValid = document.querySelector("#idValid");
	if(idValid.value !== "1") {
		alert("아이디 중복 검사해주세요.");
		id.nextElementSibling.focus(); //id 중복검사 후 다음칸으로 감 
		return false;
	}

	const password = document.querySelector("#rPassword");
	if(!/^[a-zA-Z0-9!@#$%^&*()]{4,}$/.test(password.value)){
		alert("비밀번호는 숫자와 특수문자!@#$%^&* 포함 최소 4글자이상이어야 합니다.");
		password.select();
		return false;
	}
	const passwordCheck = document.querySelector("#passwordCheck");
	if(password.value !== passwordCheck.value){
		alert("비밀번호가 일치하지 않습니다.");
		password.select();
		return false;
	}
	
}
	</script>
	</section>
</body>
</html>