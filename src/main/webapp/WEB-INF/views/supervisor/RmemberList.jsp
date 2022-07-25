<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.Rmember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
<%
	List<Rmember> recruitMemberList = (List<Rmember>) request.getAttribute("recruitMemebrList");
%>

<body>
	
<div id="member-btn-container">
<input type="button" class="btn-member" id="aMember" value="구직자" onclick="location.href='<%= request.getContextPath()%>/supervisor/aMemberList';"/>
<input type="button" class="btn-member" id="rMember" value="구인자" onclick="location.href='<%= request.getContextPath()%>/supervisor/rMemberList';"/>
</div>

<section id="super-board">		
	<div id="recruitMember-container" class="member-container">
		<h2 class="h2">구인자 회원관리</h2>
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
							<td colspan="8" align="center">조회 결과가 없습니다.</td>
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
		</form>
			<div id='super-pagebar'>
				<%= request.getAttribute("rmPagebar") %>
			</div>
			<div class="under-tbl-btn">
			<input type="submit" class="btn" id="rm-delete" value="삭제" />
			<input type="button" class="btn" id="rm-log" onclick="javascript:rmlog();" value="로그보기" />
			</div>
		</div>
</section>	

<!-- 로그 폼 -->
<form action="<%= request.getContextPath()%>/supervisor/rMemberLog" name="rMemberLogFrm">
</form>	


<script>
  document.querySelector("#rm-delete").addEventListener('click', (e) => {
  	e.preventDefault()
  	if(confirm("정말 삭제하시겠습니까?"))
  		document.recruitFrm.submit();
  });
  
//로그보기
  const rmlog = () => {
  	const title = "RecruitMemberLog";
  	const spec = "width=1350px,height=500px";
  	const popup = open("", title, spec);
  	
  	const frm = document.rMemberLogFrm;
  	frm.target = title;
  	frm.submit();
  }
  
  
  
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
    </script>	
		
</body>
</html>