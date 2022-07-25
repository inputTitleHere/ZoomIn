<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.applicant.resume.model.dto.Resume"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/recruit/recruitNavbar.jsp" %>

<%
List<Resume> resumeList = (List<Resume>)request.getAttribute("resumeList");
String pagebar = (String)request.getAttribute("pagebar");
int category=(int)request.getAttribute("category");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
%>

<link	href="<%=request.getContextPath()%>/css/recruit/board/headhunt.css" rel="stylesheet" type="text/css">

<div class="content-wrapper">
<section class="category-selector">
	<form action="<%=request.getContextPath()%>/recruit/headhunt/headhuntList" id="category-select-frm">
		<select name="category" id="category-select">
			<option value="1" <%=category==1?"selected":"" %>>1.IT/웹/통신</option>
			<option value="2" <%=category==2?"selected":"" %>>2.미디어/디자인</option>
			<option value="3" <%=category==3?"selected":"" %>>3.의료/제약/복지</option>
			<option value="4" <%=category==4?"selected":"" %>>4.건설업</option>
			<option value="5" <%=category==5?"selected":"" %>>5.서비스업</option>
			<option value="6" <%=category==6?"selected":"" %>>6.은행/금융업</option>
			<option value="7" <%=category==7?"selected":"" %>>7.유통/무역/운송</option>
			<option value="8" <%=category==8?"selected":"" %>>8.제조/화학</option>
			<option value="9" <%=category==9?"selected":"" %>>9.기관/협회</option>
			<option value="10" <%=category==10?"selected":"" %>>10.교육업</option>
		</select>
		<button id="search-button">이 분야의 이력서 조회하기</button>
	</form>
</section>
<section class="resume-list">
	<table>
		<thead>
			<tr>
				<td>이름</td>
				<td>생일</td>
				<td>성별</td>
				<td>학력</td>
				<td>학교</td>
				<td>재적</td>
				<td>전공</td>
				<td>학점</td>
			</tr>
		</thead>
		<%for(Resume resume : resumeList){ 
			String schoolType=null;
			String schoolStatus=null;

			switch(resume.getSchoolType()){
			case C2:{schoolType="2년제";break;}
			case C3:{schoolType="3년제";break;}
			case C4:{schoolType="4년제";break;}
			case HI:{schoolType="고졸";break;}
			}

			switch(resume.getSchoolStatus()){
			case A:{schoolStatus="재학";	break;}
			case B:{schoolStatus="휴학";	break;}
			case C:{schoolStatus="졸업";	break;}
			}

		%>
		
			<tr>
				<td><a href="<%=request.getContextPath() %>/recruit/board/viewEnrolledResume?uid=<%=resume.getUid()%>"><%=resume.getName() %></a></td>
				<td><%=sdf.format(resume.getBirthday()) %></td>
				<td><%=resume.getGender().toString() %></td>
				<td><%=schoolType %></td>
				<td><%=resume.getSchoolName() %></td>
				<td><%=schoolStatus %></td>
				<td><%=resume.getMajorName() %></td>
				<td><%=resume.getGrade() %> / <%=resume.getTotalPoint() %></td>
			</tr>
		
		<%} %>
	</table>
	<br />
	<%=pagebar %>
</section>


</div>


</body>
</html>