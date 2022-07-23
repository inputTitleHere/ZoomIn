<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.RmemberLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/supervisor/supervisor.css" />
<%
	List<RmemberLog> rmLogList = (List<RmemberLog>) request.getAttribute("rmLogList"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<table id="tbl-rmember-log">
		<h1>Recruiter Log Data</h1>
		<thead>
			<tr>
				<td>No</td>
				<td>uid</td>
				<td>회사번호</td>
				<td>name</td>
				<td>id</td>
				<td>email</td>
				<td>log</td>
				<td>log date</td>
			</tr>
		</thead>
		<tbody>
			<%
				if(rmLogList == null || rmLogList.isEmpty()){
			%>
				<tr>
					<td colspan="8" ailgn="center">조회 결과가 없습니다.</td>
				</tr>
			<%
				}
				else {
					for(RmemberLog rm : rmLogList){
			%>
				<tr>
					<td><%= rm.getNo() %></td>
					<td><%= rm.getUid() %></td>
					<td><%= rm.getCompanyNo() %></td>
					<td><%= rm.getName() %></td>
					<td><%= rm.getId() %></td>
					<td><%= rm.getEmail() %></td>
					<td><%= rm.getLog() %></td>
					<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rm.getLogDate()) %></td>
				</tr>
			<%
					}
				}
			%>
		</tbody>
	</table>
	<div id='super-pagebar'>
		<%= request.getAttribute("rmLogPagebar") %>
	</div>
	



</body>
</html>