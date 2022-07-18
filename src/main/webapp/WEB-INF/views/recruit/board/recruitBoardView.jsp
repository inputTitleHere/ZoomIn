<%@page import="com.kh.zoomin.recruit.board.dto.RecruitBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
RecruitBoard rb = (RecruitBoard) request.getAttribute("recruitBoard");
%>
<link href="<%=request.getContextPath() %>/css/recruit/board/recruit-board-view.css" rel="stylesheet" type="text/css">
<section id="recruit-board-view">
	<%-- title 및 content에 대한 escapeXML처리함. --%>
	<h1><%=rb.getTitle()%></h1>
	<div class="small-info-wrapper">
		<div class="small-info">
			<table>
				<tbody>
					<tr>
						<td class="small-info-title">경력사항</td>
						<td class="small-info-data">: <%=rb.getCareerYears()%></td>
					</tr>
					<tr>
						<td class="small-info-title">학력사항</td>
						<td class="small-info-data">: <%=rb.getSchoolStatus()%></td>
					</tr>
					<tr>
						<td class="small-info-title">근무형태</td>
						<td class="small-info-data">: <%=rb.getWorkType()%></td>
					</tr>
					<tr>
						<td class="small-info-title">근무지역</td>
						<td class="small-info-data">: <%=rb.getOfficeLocation()%></td>
					</tr>
					<tr>
						<td class="small-info-title">연봉</td>
						<td class="small-info-data">: <%=rb.getSalary()%></td>
					</tr>
				</tbody>
			</table>
			<div class="button-wrapper">
				<%-- placeholde용 버튼들 --%>
				<div id="fav-button">채용글 찜하기</div>
				<div id="enroll-button">당장 입사 지원</div>
			</div>
		</div><%-- small-info --%>
	</div>
	<%-- 메인 컨텐트 영역 --%>
	<div class="content-wrapper">
		<%=rb.getContent()%>
	</div>

</section>


<br />
<br />
<br />

</body>
</html>