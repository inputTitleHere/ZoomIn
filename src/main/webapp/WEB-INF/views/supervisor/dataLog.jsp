<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.RmemberLog"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.AmemberLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
<%
	List<AmemberLog> amLogList = (List<AmemberLog>) request.getAttribute("amLogList"); 
	List<RmemberLog> rmLogList = (List<RmemberLog>) request.getAttribute("rmLogList"); 
%>
<style>
	#tbl-amember-log, #tbl-rmember-log{border-collapse: collapse; text-align:center; padding:none;}
	#tbl-amember-log tr{border-style: 1px, solid, black; padding:none;}
</style>
	<h1>DataLog탭</h1>
	
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
	
</body>
</html>