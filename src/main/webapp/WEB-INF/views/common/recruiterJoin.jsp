<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 회원가입</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/simpleJoin.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<div id="join-wrap">
		<div id="join-container">
		<form name="joinRFrm" class="joinFrm" id="joinRFrm" action="" method="POST">
			<h2>기업 회원가입</h2>
				<div class="textFrm">
					<input type="text" class="companyNo" name="companyNo" id="companyNo" value=""
						placeholder="(-)없이 사업자등록번호를 입력하세요" maxlength="10" required><br>
				</div>
				<div class="textFrm">
					<input type="text" class="name" placeholder="이름을 입력해주세요" name="name" id="name" value="" required>
				</div>
				<div class="textFrm">
					<input type="text" placeholder="아이디를 입력하세요" class="id" name="id" id="rId" value="" required> 
					<input type="button" id="idcheck" value="중복검사" onclick="checkIdRecruiter();" /> 
					<input type="hidden" id="idValid" value="0" />
				</div>
				<div class="textFrm">
					<input type="password" name="password" class="pw" id="rPassword" value="" placeholder="비밀번호를 입력해주세요." required>
				</div>
				<div class="textFrm">
					<input type="password" class="pwCheck" id="passwordCheck" placeholder="비밀번호를 다시 입력하세요"  value="" required>
				</div>
				<div class="textFrm">
					<input type="email" class="email" id="email" placeholder="@을 포함한 이메일 주소를 작성하세요" name="email" id="email" value=""><br>
				</div>
				<input type="submit" class="btn-join" value="가입하기">
			</form>
		</div>
	</div>
	<section>
		<form action="<%=request.getContextPath()%>/recruit/checkId" name="checkIdRFrm">
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
document.joinRFrm.onsubmit = (e) => {
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