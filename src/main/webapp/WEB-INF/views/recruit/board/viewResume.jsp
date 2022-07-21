<%@page import="com.kh.zoomin.applicant.resume.model.dto.Gender"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.applicant.resume.model.dto.Resume"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
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
<h2><%=title %> </h2>
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
					성별 : <%=resume.getGender()==Gender.M?"남":"여" %>
				</td>
				<td>
					생년월 : <%=birthday%>
				</td>
				<td>
					주소 : <%=resume.getAddress() %>
				</td>
			</tr>
			<tr>
				<td>
					학교 : <%=resume.getSchoolName() %>
				</td>
				<td>
					학제 : <%=schoolType %>
				</td>
				<td>
					학력 : <%=schoolStatus %>
				</td>
			</tr>
			<tr>
				<td>
					전공명: <%=resume.getMajorName()%>
				</td>
				<td>
					학점 : <%=resume.getGrade() %> / <%=resume.getTotalPoint() %>
				</td>
			</tr>
		</tbody>
	</table>

</div>

</body>
</html>