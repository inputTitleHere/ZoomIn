<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/resume.css" />
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<!-- section 태그 / div id="title" / h1 -> 변경 금지(공통 css 사용) -->
<section>
<div class="title">
	<h1>이력서 작성</h1>
</div>
<div class="context">
	<form id="resumeFrm" name = "resumeFrm" action="<%=request.getContextPath()%>/ResumeServlet" method="POST">
		<input type="text" name="id">  <!-- 로그인 기능 구현되면 세션에서 받아올것이야! -->
		<div class="resumeDev">
			<div class="resumeTitle">인적사항</div>
			<div class="resumeContext">
				<table>
					<tr>
						<th>이름* : </th>
						<td><input type="text" name="name"/></td>
						<th>생년월일* : </th>
						<td><input type="text" name="birthday"/></td>
						<th>성별* : </th>
						<td>
							<select name="gender">
								<option value="M">남자</option>
								<option value="F">여자</option>
							</select>
						</td>
						<th>이메일 : </th>
						<td><input type="email" name="email"/></td>
					</tr>
					<tr>
						<th>전화번호* : </th>
						<td colspan="2"><input type="text" name="phoneNum"/></td>
						<th>주소 : </th>
						<td colspan="4"><input type="text" name="address"/></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="resumeDev">
			<div class="resumeTitle">학력사항</div>
			<div class="resumeContext">
				<table>
					<tr>
						<th>최종학력* : </th>
						<td>
							<select name="schoolType">
								<option value="C2">2년제</option>
								<option value="C3">3년제</option>
								<option value="C4">4년제</option>
								<option value="HI">고등학교</option>
							</select>
						</td>
						<th>학교명* : </th>
						<td><input type="text" name="schoolName"/></td>
						<th>졸업상태* : </th>
						<td>
							<select name="schoolStatus">
								<option value="A">재학</option>
								<option value="B">휴학</option>
								<option value="C">졸업</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>전공명 : </th>
						<td><input type="text" name="majorName"/></td>
						<th>학점 : </th>
						<td><input type="text" name="grade"/></td>
						<th>총점 : </th>
						<td><input type="text" name="totalPoint"/></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="resumeDev">
			<div class="resumeTitle">경력사항</div>
			<div class="resumeContext">
				<table>
					<tr>
						<th>회사명 : </th>
						<td><input type="text" name="companyName"/></td>
						<th>연차 : </th>
						<td><input type="text" name="career"/></td>
						<th>현재상태 : </th>
						<td>
							<select name="careerStatus">
								<option value="A">재직</option>
								<option value="B">휴직</option>
								<option value="C">퇴직</option>
							</select>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
		<div>
			<button>cancle</button>
			<button>delete</button>
			<button id="btnSubmit">submit</button>
		</div>
</div>
</section>

<script type="text/javascript">
	document.getElementById("btnSubmit").onclick = (e) =>{
		document.resumeFrm.submit();
	};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
