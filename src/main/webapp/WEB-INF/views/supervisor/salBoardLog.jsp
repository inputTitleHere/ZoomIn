<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.SalLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/supervisor/supervisor.css" />
<%
	List<SalLog> salLogList = (List<SalLog>) request.getAttribute("salLogList");  
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
	<table id="tb-sal-log" calss="table-log">
		<h2>연봉게시판 로그</h2>
		<thead>
			<tr>
				<td>no</td>
				<td>uid</td>
				<td>board_no</td>
				<td>사업자번호</td>
				<td>log</td>
				<td>log_Date</td>
			</tr>
		</thead>
		<tbody>
			<%
				if(salLogList == null || salLogList.isEmpty()){
			%>
				<tr>
					<td colspan="6" ailgn="center">조회 결과가 없습니다.</td>
				</tr>
			<%
				}
				else {
					for(SalLog sal : salLogList){
			%>
				<tr>
					<td><%= sal.getNo() %></td>
					<td><%= sal.getUid() %></td>
					<td><%= sal.getBoardNo() %></td>
					<td><%= sal.getCompanyNo() %></td>
					<td><%= sal.getLog() %></td>
					<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(sal.getLogDate()) %></td>
				</tr>
			<%
					}
				}
			%>
		</tbody>
	</table>
</div>
	<div id='super-pagebar'>
		<%= request.getAttribute("salLogPagebar") %>
	</div>
</section>		

</body>
</html>