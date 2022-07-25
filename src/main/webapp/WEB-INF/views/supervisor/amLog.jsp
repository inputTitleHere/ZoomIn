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
<section id="super-board">	
<div class="log-tbl">
	<table id="tbl-amember-log">
		<h2 class="h2">Applicant Log Data</h2>
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
</div>
	<div id='super-pagebar'>
		<%= request.getAttribute("amLogPagebar") %>
	</div>	
</section>

</body>
</html>