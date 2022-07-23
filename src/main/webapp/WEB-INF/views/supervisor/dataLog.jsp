<%@page import="com.kh.zoomin.supervisor.model.dto.RecLog"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.ComLog"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.SalLog"%>
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
	List<SalLog> salLogList = (List<SalLog>) request.getAttribute("salLogList"); 
	List<ComLog> comLogList = (List<ComLog>) request.getAttribute("comLogList"); 
	List<RecLog> recLogList = (List<RecLog>) request.getAttribute("recLogList"); 
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
	<div id='pagebar'>
		<%= request.getAttribute("amLogPagebar") %>
	</div>
	
	
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
	<div id='pagebar'>
		<%= request.getAttribute("rmLogPagebar") %>
	</div>
	
	<table id="tb-sal-log">
		<h1>연봉게시판 로그</h1>
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
	<div id='pagebar'>
		<%= request.getAttribute("salLogPagebar") %>
	</div>
	
	
	<table id="tb-com-log">
		<h1>회사리뷰게시판 로그</h1>
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
	<div id='pagebar'>
		<%= request.getAttribute("comLogPagebar") %>
	</div>
	
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
	<div id='pagebar'>
		<%= request.getAttribute("recLogPagebar") %>
	</div>
	
</body>
</html>