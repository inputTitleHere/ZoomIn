<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 회원가입</title>
</head>
<body>
	<h2>개인 회원가입</h2>
	<div>
		<form name="A_joinFrm" id="A_joinFrm"
			action="http://localhost:9090/zoomin/applicant/join/">
			<table>
				<tr>
					<th>이름<sup>*</sup></th>
					<td><input type="text" name="name" id="name" value="" required><br>
					</td>
				</tr>
				<tr>
					<th>아이디<sup>*</sup></th>
					<td><input type="text" placeholder="3글자이상" name="id" id="Aid"
						value="" required> <input type="button" value="중복검사"
						onclick="checkIdDuplicate();" /> <input type="hidden"
						id="idValid" value="0" /> <%-- 중복검사전 0, 중복검사후(유효한 아이디) 1 --%></td>
				</tr>
				<tr>
					<th>비밀번호<sup>*</sup></th>
					<td><input type="password" name="password" id="Apassword"
						value="password" placeholder="숫자와 특수문자 포함한 3글자 이상" required><br>
					</td>
				</tr>
				<tr>
					<th>비밀번호 재입력<sup>*</sup></th>
					<td><input type="password" id="passwordCheck" value="password"
						required><br></td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td><input type="tel" placeholder="(-)없이 숫자만 입력" name="phone"
						id="phone" maxlength="11" value="" required><br /></td>
				</tr>
				<tr>
					<th>이메일주소</th>
					<td><input type="email" placeholder="@이후 메일주소까지 작성"
						name="email" id="email" value=""><br></td>
				</tr>
				</table>
			</form>
		</div>
</body>
</html>