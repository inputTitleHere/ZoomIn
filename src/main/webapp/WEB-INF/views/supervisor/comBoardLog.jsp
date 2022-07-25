<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.ComLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/supervisor/supervisor.css" />
<%
	List<ComLog> comLogList = (List<ComLog>) request.getAttribute("comLogList"); 
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
	<table id="tb-com-log" calss="table-log">
		<h2>회사리뷰게시판 로그</h2>
		<thead>
			<tr>
				<td>no</td>
				<td>board_no</td>
				<td>uid</td>
				<td>사업자번호</td>
				<td>log</td>
				<td>log_Date</td>
			</tr>
		</thead>
		<tbody>
			<%
				if(comLogList == null || comLogList.isEmpty()){
			%>
				<tr>
					<td colspan="6" ailgn="center">조회 결과가 없습니다.</td>
				</tr>
			<%
				}
				else {
					for(ComLog com : comLogList){
			%>
				<tr>
					<td><%= com.getNo() %></td>
					<td><%= com.getBoardNo() %></td>
					<td><%= com.getUid() %></td>
					<td><%= com.getCompanyNo() %></td>
					<td><%= com.getLog() %></td>
					<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(com.getLogDate()) %></td>
				</tr>
			<%
					}
				}
			%>
		</tbody>
	</table>
</div>
	<div id='super-pagebar'>
		<%= request.getAttribute("comLogPagebar") %>
	</div>
</section>	


</body>
</html>