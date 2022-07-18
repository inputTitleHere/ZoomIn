<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.zoomin.recruit.member.RecruitMember"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
RecruitMember rm = (RecruitMember)session.getAttribute("loginMember");
%>

<div class="write-recruit-board-wrapper">
	<form action="<%=request.getContextPath()%>/recruit/board/writeRecruitBoard" method="post" id="writeRecruitBoardFrm" name="writeRecruitBoardFrm">
		<input type="hidden" name="uid" value="<%= rm.getUid()%>" />
		<input type="hidden" name="compnayNo" value="<%= rm.getCompanyNo()%>" />
		<label for="title">제목 : </label> <input type="text" name="title" id="title" placeholder="채용글 제목을 입력해주세요." required /><br />
		<label for="category">분야 분류</label>
		<select name="category" id="category">
			<option disabled selected>---카테고리 선택---</option>
			<option value="1">1.IT/웹/통신</option>
			<option value="2">2.미디어/디자인</option>
		<%-- 여기에 option들 더 추가할 것. --%>
		</select>
		<label for="closureDate">마감일자 : </label><input type="date" id="closureDate" name="closureDate"/><%-- 최대/최소 및 초기날짜 설정 가능. --%>
		<label for="career">경력사항 : </label> <input type="text" name="career" id="career" placeholder="경력사항" /><br />
		<label for="schoolStatus">학력사항 : </label> <input type="text" name="schoolStatus" id="schoolStatus" placeholder="학력사항" /><br />
		<label for="workType">근무형태 : </label> <input type="text" name="workType" id="workType" placeholder="근무형태" /><br />
		<label for="officeLocation">근무지역 : </label> <input type="text" name="officeLocation" id="officeLocation" placeholder="근무지역" /><br />
		<label for="salary">연봉 : </label> <input type="text" name="salary" id="salary" placeholder="연봉" /><br />
		<label for="content">내용 : </label> <textarea rows="10" cols="50" name="content" id="content" placeholder="내용을 입력하세요." required></textarea><br />
		<button id="submit-button">채용글 등록하기</button>
	</form>
</div>


</body>
</html>