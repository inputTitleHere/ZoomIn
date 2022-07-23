<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.RecLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/supervisor/supervisor.css" />
<%
	List<RecLog> recLogList = (List<RecLog>) request.getAttribute("recLogList"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용게시판 데이터 관리</title>
</head>
<body>
		<table id="tb-rec-log">
		<h1>채용정보게시판 로그</h1>
		<thead>
			<tr>
				<td>no</td>
				<td>board_no</td>
				<td>uid</td>
				<td>사업자번호</td>
				<td>Title</td>
				<td>log</td>
				<td>log_Date</td>
			</tr>
		</thead>
		<tbody>
			<%
				if(recLogList == null || recLogList.isEmpty()){
			%>
				<tr>
					<td colspan="7" ailgn="center">조회 결과가 없습니다.</td>
				</tr>
			<%
				}
				else {
					for(RecLog rec : recLogList){
			%>
				<tr>
					<td><%= rec.getNo() %></td>
					<td><%= rec.getBoardNo() %></td>
					<td><%= rec.getUid() %></td>
					<td><%= rec.getCompanyNo() %></td>
					<td><%= rec.getTitle() %></td>
					<td><%= rec.getLog() %></td>
					<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rec.getLogDate()) %></td>
				</tr>
			<%
					}
				}
			%>
		</tbody>
	</table>
	<div id='super-pagebar'>
		<%= request.getAttribute("recLogPagebar") %>
	</div>
</body>
</html>