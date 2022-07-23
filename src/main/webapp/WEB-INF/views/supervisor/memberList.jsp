<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.Rmember"%>
<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>

<%
	List<ApplicantMember> applicantMemberList = (List<ApplicantMember>) request.getAttribute("applicantMemebrList");
	List<Rmember> recruitMemberList = (List<Rmember>) request.getAttribute("recruitMemebrList");
%>

	<section id="memberList-container">
		<div class="btn-member">
			<input type="button" value="구직자 회원관리" id="am"/>
			<input type="button" value="구인자 회원관리" id="rm"/>
		</div>
		<script>
		document.querySelector("#am").addEventListener('click', (e) => {
			document.querySelector("#recruitMember-container").style.display = "none";
			document.querySelector("#applicantMember-container").style.display = "block";
		})
		document.querySelector("#rm").addEventListener('click', (e) => {
			document.querySelector("#applicantMember-container").style.display = "none";
			document.querySelector("#recruitMember-container").style.display = "block";
		})
		</script>

		<div id="recruitMember-container">
		<h2>구인자 회원관리</h2>
		<input type="submit" value="구인자 삭제" />
		<form action="<%= request.getContextPath() %>/supervisor/recruitMemberDelete" name="recruitFrm" method="post">
			<table id="tbl-recruitMember">
				<thead>
					<tr>
						<th><input type="checkbox" name="allChk" class="allChk" /></th>
						<th>UID</th>
						<th>회사번호</th>
						<th>회사명</th>
						<th>RECRUITER</th>
						<th>ID</th>
						<th>E-MAIL</th>
						<th>가입일</th> <%-- 날짜형식 yyyy-MM-dd --%>
					</tr>
				</thead>
				<tbody>
					<% 
						if(recruitMemberList == null || recruitMemberList.isEmpty()) {
					%>
						<tr>
							<td colspan="7" align="center">조회 결과가 없습니다.</td>
						</tr>
					<%
						}
						else {
							for(Rmember rm : recruitMemberList){
					%>
						<tr>
							<td>
    							<input type="checkbox" name="chk" value="<%= rm.getUid() %>" />
   							</td>
							<td><%= rm.getUid() %></td>
							<td><%= rm.getComNo() %></td>
							<td><%= rm.getComName() %></td>
							<td><%= rm.getRecruiter() %></td>
							<td><%= rm.getId() %></td>
							<td><%= rm.getEmail() %></td>
							<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rm.getRegDate()) %></td>
						</tr>					
					<%
							}
						}
					%>
				</tbody>
			</table>
			<input type="submit" value="구인자 삭제" />
		</form>
			<div id='pagebar'>
				<%= request.getAttribute("rmPagebar") %>
			</div>
		</div>
		
		<div id="applicantMember-container">
		<h2>구직자 회원관리</h2>
		<form action="<%= request.getContextPath() %>/supervisor/applicantMemberDelete" name="applicantFrm" method="post">
			<table id="tbl-applicantMember">
				<thead>
					<tr>
						<th><input type="checkbox" name="allChk" class="allChk" /></th>
						<td>UID</td>
						<td>이름</td>
						<td>ID</td>
						<td>전화번호</td>
						<td>이메일</td>
						<td>가입일</td>
					</tr>
				</thead>
				<tbody>
				<%
					if(applicantMemberList == null || applicantMemberList.isEmpty()) {
				%>
					<tr>
						<td colspan="7" align="center">조회 결과가 없습니다.</td>
					</tr>
				<%
					}
					else {
						for(ApplicantMember am : applicantMemberList){
				%>
					<tr>
						<td>
    						<input type="checkbox" name="chk" value="<%= am.getUid() %>" />
   						</td>
						<td><%= am.getUid() %></td>
						<td><%= am.getName() %></td>
						<td>
							<a href="javascript:amWritingView();"><%= am.getId() %></a> <%-- 아이디 클릭시 게시글 보기 --%>
							<input type="hidden" name="<%= am.getUid() %>" />	
						</td>
						<td><%= am.getPhone() %></td>
						<td><%= am.getEmail() %></td>
						<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(am.getRegDate()) %></td>
					</tr>
				<%
						}
					}
				%>
				</tbody>
			</table>
			<input type="submit" value="구직자 삭제" />
		</form>
			<div id='pagebar'>
				<%= request.getAttribute("amPagebar") %>
			</div>
			
		</div>
		
		<%-- 팝업폼 --%>
		<form action="<%= request.getContextPath()%>/supervisor/amWriting" name="amWritingFrm">
			<%
				if(applicantMemberList == null || applicantMemberList.isEmpty()) {
					for(ApplicantMember am : applicantMemberList){
			%>
			<input type="hidden" name="amUid" value="<%= am.getUid() %>" />	
			<%
					}
				}
			%>	
		</form>
	<style>
     #tbl-applicantMember, #tbl-recruitMember{
		 border: 1px solid black;
		 border-collapse: collapse;
		 margin: 5px 5px;
	 }
		tr, td, th{
		    border: 1px solid black;
		    padding: 10px;
		}
    </style>
	
	</section>
<script>
//전체선택
    document.querySelectorAll(".allChk").forEach((target) => {
       	const chk = document.querySelectorAll("[name=chk]");
       	const rowCnt = chk.length;
    	target.addEventListener('click', (e) => {
        	const check = target.checked;
        	if(check){
        		for(let i = 0; i < rowCnt; i++){
        			chk[i].checked = true;
        		}
        	} else {
        		for(let i = 0; i < rowCnt; i++){
        			chk[i].checked = false;
        		}
        	}
        })
    })
    
//구직자 아이디 클릭시 작성글 보기 (팝업)
const amWritingView = () => {
	const title = "applicantWriting";
	const spec = "width=700px,height=300px";
	const popup = open("", title, spec);
	
	const frm = document.amWritingFrm;
	frm.target = title;
	frm.submit();
}
    
</script>

	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>