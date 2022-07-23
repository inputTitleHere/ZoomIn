<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.AmemberLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/supervisor/supervisor.css" />
<%
	List<AmemberLog> amLogList = (List<AmemberLog>) request.getAttribute("amLogList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table id="tbl-amember-log">
		<h1>Applicant Log Data</h1>
		<thead>
			<tr>
				<td>No</td>
				<td>uid</td>
				<td>name</td>
				<td>id</td>
				<td>phone</td>
				<td>email</td>
				<td>log</td>
				<td>log date</td>
			</tr>
		</thead>
		<tbody>
			<%
				if(amLogList == null || amLogList.isEmpty()){
			%>
				<tr>
					<td colspan="8" ailgn="center">조회 결과가 없습니다.</td>
				</tr>
			<%
				}
				else {
					for(AmemberLog am : amLogList){
			%>
				<tr>
					<td><%= am.getNo() %></td>
					<td><%= am.getUid() %></td>
					<td><%= am.getName() %></td>
					<td><%= am.getId() %></td>
					<td><%= am.getPhone() %></td>
					<td><%= am.getEmail() %></td>
					<td><%= am.getLog() %></td>
					<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(am.getLogDate()) %></td>
				</tr>
			<%
					}
				}
			%>
		</tbody>
	</table>
	<div id='super-pagebar'>
		<%= request.getAttribute("amLogPagebar") %>
	</div>	


</body>
</html>