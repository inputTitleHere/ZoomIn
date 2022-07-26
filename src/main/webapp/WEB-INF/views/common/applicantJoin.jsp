<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 회원가입</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/simpleJoinAppli.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<div id="join-wrap">
		<div id="join-container">
			<form name="joinAFrm" class="joinFrm" id="joinAFrm" action="" method="POST"/>
				<h2>개인 회원가입</h2>
					<div class=textFrm>
						<input type="text" class="name" name="name" id="name" placeholder="이름을 입력하세요" value="" required>				
					</div>
					<div class=textFrm>
						<input type="text" placeholder="아이디를 입력하세요" name="id" class="id" id="aId" value="" required> 
						<input type="button" id="idcheck" value="중복검사" onclick="checkIdApplication();" /> 
						<input type="hidden" id="idValid" value="0" />
					</div>
					<div class=textFrm>
						<input type="password" name="password" class="pw" id="aPassword"
							value="password" placeholder="비밀번호를 입력하세요" required>
					</div>
					<div class=textFrm>
						<input type="password" class="pwCheck" id="passwordCheck" value="password" placeholder="비밀번호를 다시 입력하세요" required>
					</div>
					<div class=textFrm>
						<input type="tel" class="phone" placeholder="(-)없이 핸드폰 번호를 입력하세요" name="phone" id="phone" maxlength="11" value="" required><br />					
					</div>
					<div class=textFrm>
						<input type="email" class="email" placeholder="@을 포함한 이메일 주소를 작성하세요" name="email" id="email" value="">
					</div>
					<input type="submit" class="btn-join" value="가입하기" >
				</form>
			</div>
		</div>
	<section>
		<form action="<%=request.getContextPath()%>/applicant/checkId" name="checkIdAFrm">
			<input type="hidden" name="id" />
		</form>
	<script>
	//사용자가 입력한 id 중복여부 검사 시작 
	const checkIdApplication = () => {
		const id = document.querySelector("#aId");
		if(!/^[a-zA-Z0-9]{3,}$/.test(id.value)){
			alert("유효한 아이디를 입력해주세요.");
			id.select();
			return;
	}
		
		const title = "checkIdAFrm";
		const spec = "width=430px, height=170px"; 
		//나중에 css로 글씨 정중앙 수정필요
		const popup = open("", title, spec);
		
		const frm = document.checkIdAFrm;
		frm.target = title;
		frm.id.value = id.value;
		frm.submit();
		
};

document.querySelector("#passwordCheck").onblur = (e) => {
	const password = document.querySelector("#aPassword");
	const passwordCheck = e.target;
	if(password.value != passwordCheck.value) {
		alert("비밀번호가 일치하지 않습니다.");
		password.select();
	}
};

//중복검사 클릭->중복검사 전 idvalid가 0/중복검사 ok는 1 
document.querySelector("#aId").onchange = (e) => {
	document.querySelector("#idValid").value = 0;
};

//폼 유효성 검사
document.joinAFrm.onsubmit = (e) => {
	const idValid = document.querySelector("#idValid");
	if(idValid.value !== "1") {
		alert("아이디 중복 검사해주세요.");
		id.nextElementSibling.focus(); //id 중복검사 후 다음칸으로 감 
		return false;
	}

	const password = document.querySelector("#aPassword");
	if(!/^[a-zA-Z0-9!@#$%^&*()]{4,}$/.test(password.value)){
		alert("비밀번호는 최소 4글자이상이어야 합니다.");
		password.select();
		return false;
	}
	const passwordCheck = document.querySelector("#passwordCheck");
	if(password.value !== passwordCheck.value){
		alert("비밀번호가 일치하지 않습니다.");
		password.select();
		return false;
	}
	const phone = document.querySelector("#phone");
	if(!/^010[0-9]{8}$/.test(phone.value)){
		alert("유효한 전화번호를 입력해주세요");
		phone.select();
		return false;
	}
	
}
	</script>
</section>
</body>
</html>