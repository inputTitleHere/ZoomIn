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
		<label for="title">경력사항 : </label> <input type="text" name="title" id="title" placeholder="경력사항" /><br />
		<label for="title">학력사항 : </label> <input type="text" name="title" id="title" placeholder="학력사항" /><br />
		<label for="title">근무형태 : </label> <input type="text" name="title" id="title" placeholder="근무형태" /><br />
		<label for="title">근무지역 : </label> <input type="text" name="title" id="title" placeholder="근무지역" /><br />
		<label for="title">연봉 : </label> <input type="text" name="title" id="title" placeholder="연봉" /><br />
		<label for="title">내용 : </label> <textarea rows="10" cols="50" name="content" id="content" placeholder="내용을 입력하세요." required></textarea><br />
		<button id="submit-button">채용글 등록하기</button>
	</form>
</div>


</body>
</html>