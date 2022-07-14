<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/resume.css" />
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<!-- section 태그 / div id="title" / h1 -> 변경 금지(공통 css 사용) -->


<section>
<div class="title">
	<h1>이력서 작성</h1>
</div>
<div class="context">
	<form id="resumeFrm" name = "resumeFrm" action="<%=request.getContextPath()%>/ResumeServlet" method="POST">

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
					</tr>
					<tr>
						<th>주소 : </th>
						<td colspan="4"><input type="text" name="address"/></td>
					</tr>
					<th>희망직종* : </th>
						<td>
							<select name="categoryNumber">
								<option value="1">IT/웹/통신</option>
								<option value="2">미디어/디자인</option>
								<option value="3">의료/제약/복지</option>
								<option value="4">건설업</option>
								<option value="5">서비스업</option>
								<option value="6">은행/금융업</option>
								<option value="7">유통/무역/운송</option>
								<option value="8">제조/화학</option>
								<option value="9">기관/협회</option>
								<option value="10">교육업</option>
							</select>
						</td>
				</table>
			</div>
		</div>
		<div class="resumeDev">
			<div class="resumeTitle">학력사항</div>
			<div class="resumeContext">
				<table>
					<tr>
						<th>학력구분* : </th>
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
						<th>최종학력* : </th>
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
			<div class="resumeContext"></div>
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
	
	$("select[name = interestJob]").change(function(){
		  console.log($("select[name = interestJob] option:selected").val()); //text값 가져오기
		});
</script>
<%@ include file="/WEB-INF/views/common/commonFooter.jsp" %>
