<%@page import="com.kh.zoomin.applicant.resume.model.dto.Gender"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.applicant.resume.model.dto.Resume"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/recruit/recruitNavbar.jsp" %>
<%
Resume resume = (Resume)request.getAttribute("resume");
String title = (String)request.getAttribute("title");
String birthday = new SimpleDateFormat("yyyy/MM").format(resume.getBirthday());

String schoolType=null;
String schoolStatus=null;

switch(resume.getSchoolType()){
case C2:{
	schoolType="2년제";
	break;
}
case C3:{
	schoolType="3년제";
	break;
}
case C4:{
	schoolType="4년제";
	break;
}
case HI:{
	schoolType="고졸";
	break;
}
}

switch(resume.getSchoolStatus()){
case A:{
	schoolStatus="재학";
	break;
}
case B:{
	schoolStatus="휴학";
	break;
}
case C:{
	schoolStatus="졸업";
	break;
}
}


%>
<link href="<%=request.getContextPath() %>/css/recruit/view-resume.css" rel="stylesheet" type="text/css">
<% if(title!=null){%>
<h2><%=title %> </h2>
<%} %>
<div class="resume-wrapper">

<div class="resume">
	<table>
		<tbody>
			<tr>
				<td>
					<h2>이름 : <%=resume.getName() %></h2>
				</td>
			</tr>
			<tr class="biography">
				<td>
					<span class="biography-title">성별 : </span><%=resume.getGender()==Gender.M?"남":"여" %>
				</td>
				<td>
					<span class="biography-title">생년월 : </span><%=birthday%>
				</td>
				<td>
					<span class="biography-title">주소 : </span><%=resume.getAddress() %>
				</td>
			</tr>
			<tr>
				<td>
					<span class="biography-title">학교 : </span><%=resume.getSchoolName() %>
				</td>
				<td>
					<span class="biography-title">학제 : </span><%=schoolType %>
				</td>
				<td>
					<span class="biography-title">학력 : </span><%=schoolStatus %>
				</td>
			</tr>
			<tr>
				<td>
					<span class="biography-title">전공명: </span><%=resume.getMajorName()%>
				</td>
				<td>
					<span class="biography-title">학점 : </span><%=resume.getGrade() %> / <%=resume.getTotalPoint() %>
				</td>
			</tr>
		</tbody>
	</table>

</div>
</div>

 <%@include file="/WEB-INF/views/common/footer.jsp" %>
