<%@page import="com.kh.zoomin.applicant.resume.model.dto.Gender"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.applicant.resume.model.dto.Resume"%>
<%@page import="com.kh.zoomin.applicant.resume.model.dto.SchoolType" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/recruit/recruitNavbar.jsp" %>
<%
// rl = ResumeList
List<Resume> rl = (List<Resume>)request.getAttribute("enrolledList");
int boardNo=(Integer)request.getAttribute("boardNo");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy'년' MM'월'");
String title=(String)request.getAttribute("title");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/recruit/board/viewEnrolled.css" />

<div class="enrolled-list-wrapper">

<h1><%=title %></h1>
<h2>지원자 일람</h2>
<%
if(rl.size()==0){
%>
<h1>아직 지원자가 없습니다.</h1>
<%
}
%>
<div class="resume-bar-wrapper">
	<table class="resume">
	<thead>
		<tr>
			<td>이름</td>
			<td>생년월</td>
			<td>성별</td>
			<td>학교</td>
			<td>전공</td>
			<td>학점</td>
			<td>이력서 보기</td>
		</tr>
	</thead>
		<tbody>

<%
for(Resume resume : rl){
String birthday=sdf.format(resume.getBirthday());
String schoolType=null;
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
%>
			<tr>
				<td id="name"><%=resume.getName()%></td>
				<td id="birthday"><%=birthday %></td>
				<td id="gender"><%=resume.getGender()==Gender.M?"남":"여" %></td>
				<td id="school-name"><%=resume.getSchoolName() %></td>
				<td id="major"><%=resume.getMajorName() %></td>
				<td id="grade"><%=String.format("%.2f",resume.getGrade()) %>/<%=String.format("%.2f",resume.getTotalPoint()) %></td>
				<td id="view-button-td">
					<button class="resume-button" onclick="location.href='<%=request.getContextPath()%>/recruit/board/viewEnrolledResume?uid=<%=resume.getUid()%>&boardNo=<%=boardNo%>'">이력서 보기</button>
				</td>
			</tr>
<%
}
%>
		</tbody>	
	</table>
</div>
</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
