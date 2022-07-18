<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.zoomin.applicant.resume.model.dto.Resume"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/resume.css" />
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<!-- section 태그 / div id="title" / h1 -> 변경 금지(공통 css 사용) -->

<%
	String result = (String)request.getAttribute("result");
	int categoryNumber = 0;
	String name = "";
	String birthday= "";
	String gender = "";
	String address = "";
	String schoolType= "";
	String schoolName = "";
	String schoolStatus = "";
	String majorName = "";
	double grade= 0.0;
	double totalPoint = 0.0;
	
	if(result == "ok"){
	categoryNumber = (int)request.getAttribute("categoryNumber");
	name= (String)request.getAttribute("name");
	birthday= (String)request.getAttribute("birthday");
	gender= (String)request.getAttribute("gender");
	address= (String)request.getAttribute("address");
	schoolType= (String)request.getAttribute("schoolType");
	schoolName= (String)request.getAttribute("schoolName");
	schoolStatus= (String)request.getAttribute("schoolStatus");
	majorName= (String)request.getAttribute("majorName");
	grade = (double)request.getAttribute("grade");
	totalPoint= (double)request.getAttribute("totalPoint");		
	}
	
%>

<section>
<div class="title">
	<h1>이력서 작성</h1>
</div>
<div class="context">
	<form id="resumeFrm" name = "resumeFrm" action="<%=request.getContextPath()%>/ResumeServlet" method="POST">
	<input type="hidden" name="result" value="<%=result%>"/>
		<div class="resumeDev">
			<div class="resumeTitle">인적사항</div>
			<div class="resumeContext">
				<table id="infoTable">
					<tr>
						<th>이름* : </th>
						<td><input type="text" name="name" value="<%=name%>"/></td>
						<th>생년월일* : </th>
						<td><input type="text" name="birthday" value="<%=birthday%>"/></td>
					</tr>
					
					<tr>
						<th>성별* : </th>
						<td>
							<select name="gender">
								<option value="M">남자</option>
								<option value="F">여자</option>
							</select>
						</td>
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
					</tr>
					<tr>
						<th>주소 : </th>
						<td colspan="3"><input type="text" name="address" id="address" value="<%=address%>"/></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="resumeDev">
			<div class="resumeTitle">학력사항</div>
			<div class="resumeContext">
				<table>
					<tr>
						<th>학교명* : </th>
						<td><input type="text" name="schoolName" value="<%=schoolName%>"/></td>
						<th>전공명 : </th>
						<td><input type="text" name="majorName" value="<%=majorName%>"/></td>
					</tr>
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
						<th>학점 : </th>
						<td><input type="text" name="grade" value="<%=grade%>"/></td>
						<th>총점 : </th>
						<td><input type="text" name="totalPoint" value="<%=totalPoint%>"/></td>
					</tr>
				</table>
			</div>
		</div>
		
		
	</form>
		<div id = "btndesign">
			<button id="btnResumeUpdate" class="custom-btn btn-3"><span>이력서 수정</span></button>
			<button id="btnResumeDelete" class="custom-btn btn-3"><span>이력서 삭제</span></button>
			<button id="btnResumeSubmit" class="custom-btn btn-3"><span>이력서 저장</span></button>
		</div>		
</div>
</section>
<form name="resumeDelFrm" action="<%= request.getContextPath() %>/ResumeDeleteServlet" method="POST"></form>

<script type="text/javascript">
	document.getElementById("btnResumeSubmit").onclick = (e) =>{
		document.resumeFrm.submit();
		alert("저장되었습니다!");
	};
	
	document.getElementById("btnResumeDelete").onclick = (e) =>{
			document.resumeDelFrm.submit();
			alert("삭제 되었습니다!");
	};
	
	document.getElementById("btnResumeUpdate").onclick = (e) =>{
		document.resumeFrm.submit();
		alert("수정되었습니다!");
	};
	
	$("select[name = categoryNumber]").change(function(){
		  console.log($("select[name = categoryNumber] option:selected").val()); //text값 가져오기
		});
	
	
	$(document).ready(function(){
		if("<%=result%>"== "ok"){
			$("select[name = categoryNumber]").val(<%=categoryNumber%>).prop("selected",true);
			$("select[name = gender]").val("<%=gender%>").prop("selected",true);
			$("select[name = schoolType]").val("<%=schoolType%>").prop("selected",true);
			$("select[name = schoolStatus]").val("<%=schoolStatus%>").prop("selected",true);			
			$("#btnResumeSubmit").hide();
		}else{
			$("#btnResumeUpdate").hide();
			$("#btnResumeDelete").hide();
		}
	});

	
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
