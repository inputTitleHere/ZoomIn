<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.recruit.board.dto.RecruitBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/recruit/recruitNavbar.jsp" %>

<%
RecruitBoard rb = (RecruitBoard)request.getAttribute("recruitBoard");
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'hh:mm"); 
String closureTime = sdf.format(rb.getClosureDate());

Member lm = (Member)session.getAttribute("loginMember");
//RecruitMember rm=null;
if(lm instanceof RecruitMember){
	rm = (RecruitMember)session.getAttribute("loginMember");	
}

%>


<div class="write-recruit-board-wrapper">
	<form action="<%=request.getContextPath()%>/recruit/board/updateRecruitBoard" method="post" id="writeRecruitBoardFrm" name="writeRecruitBoardFrm">
		<input type="hidden" name="uid" value="<%= rm.getUid()%>" />
		<input type="hidden" name="companyNo" value="<%= rm.getCompanyNo()%>" />
		<input type="hidden" name="no" id="no" value="<%=rb.getNo()%>"/>
		<label for="title">제목 : </label> <input type="text" name="title" id="title" value="<%=rb.getTitle()%>" required /><br />
		<label for="category">분야 분류</label>
		<select name="category" id="category" required>
			<option disabled value="">---카테고리 선택---</option>
			<option value="1" <%=rb.getCategoryNumber()==1?"selected":""%>>1.IT/웹/통신</option>
			<option value="2" <%=rb.getCategoryNumber()==2?"selected":""%>>2.미디어/디자인</option>
			<option value="3" <%=rb.getCategoryNumber()==3?"selected":""%>>3.의료/제약/복지</option>
			<option value="4" <%=rb.getCategoryNumber()==4?"selected":""%>>4.건설업</option>
			<option value="5" <%=rb.getCategoryNumber()==5?"selected":""%>>5.서비스업</option>
			<option value="6" <%=rb.getCategoryNumber()==6?"selected":""%>>6.은행/금융업</option>
			<option value="7" <%=rb.getCategoryNumber()==7?"selected":""%>>7.유통/무역/운송</option>
			<option value="8" <%=rb.getCategoryNumber()==8?"selected":""%>>8.제조/화학</option>
			<option value="9" <%=rb.getCategoryNumber()==9?"selected":""%>>9.기관/협회</option>
			<option value="10" <%=rb.getCategoryNumber()==10?"selected":""%>>10.교육업</option>
		<%-- 여기에 option들 더 추가할 것. --%>
		</select><br />
		<%-- 직무테이블 임시 미루기 --%>
		
		<%-- 직무테이블 임시 미루기 --%>
		<label for="closureDate">마감일자 : </label><input type="datetime-local" id="closureDate" name="closureDate" value="<%=closureTime %>" required/><br /><%-- 최대/최소 및 초기날짜 설정 가능. --%>
		<label for="career">경력사항 : </label> <input type="text" name="career" id="career" value="<%=rb.getCareerYears() %>" /><br />
		<label for="schoolStatus">학력사항 : </label> <input type="text" name="schoolStatus" id="schoolStatus" value="<%=rb.getSchoolStatus() %>" /><br />
		<label for="workType">근무형태 : </label> <input type="text" name="workType" id="workType" value="<%=rb.getWorkType() %>" /><br />
		<label for="officeLocation">근무지역 : </label> <input type="text" name="officeLocation" id="officeLocation" value="<%=rb.getOfficeLocation() %>" /><br />
		<label for="salary">연봉 : </label> <input type="text" name="salary" id="salary" value="<%=rb.getSalary() %>" /><br />
		<label for="content">내용 : </label> <textarea rows="10" cols="50" name="content" id="content"required><%=rb.getContent() %></textarea><br />
		<button id="submit-button">채용글 등록하기</button>
	</form>
</div>

</body>
</html>