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

<h1><%=title %></h1>
<h2>지원자 일람</h2>
<hr />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/recruit/board/viewEnrolled.css" />
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
<div class="resume-bar-wrapper">
	<table class="resume">
		<tbody>
			<tr>
				<td id="name"><%=resume.getName()%></td>
				<td id="birthday"><%=birthday %></td>
				<td id="gender"><%=resume.getGender()==Gender.M?"남":"여" %></td>
				<td id="school-name">학교 : <%=resume.getSchoolName() %></td>
				<td id="major">전공 : <%=resume.getMajorName() %></td>
				<td id="grade">학점 : <%=String.format("%.2f",resume.getGrade()) %>/<%=String.format("%.2f",resume.getTotalPoint()) %></td>
				<td id="view-button-td">
					<button id="view-button" onclick="location.href='<%=request.getContextPath()%>/recruit/board/viewEnrolledResume?uid=<%=resume.getUid()%>&boardNo=<%=boardNo%>'">이력서 보기</button>
				</td>
			</tr>
		</tbody>	
	</table>
</div>
<%
}
%>



</body>
</html>