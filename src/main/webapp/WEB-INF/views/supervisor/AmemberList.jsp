<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
<%
	List<ApplicantMember> applicantMemberList = (List<ApplicantMember>) request.getAttribute("applicantMemebrList");
%>

<body>


<div id="member-btn-container">
<input type="button" class="btn-member" id="aMember" value="구직자" onclick="location.href='<%= request.getContextPath()%>/supervisor/aMemberList';"/>
<input type="button" class="btn-member" id="rMember" value="구인자" onclick="location.href='<%= request.getContextPath()%>/supervisor/rMemberList';"/>
</div>


<section id="super-board">
	<div id="applicantMember-container" class="member-container">
		<h2 class="h2">구직자 회원관리</h2>
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
		</form>
			<div id='super-pagebar'>
				<%= request.getAttribute("amPagebar") %>
			</div>
		<div class="under-tbl-btn">
			<input type="submit" class="btn" id="am-delete" value="삭제" />
			<input type="button" class="btn" id="am-log" onclick="javascript:amlog();" value="로그보기" />
		</div>
			
		</div>
</section>		
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
	</section>
<!-- 로그 폼 -->
<form action="<%= request.getContextPath()%>/supervisor/aMemberLog" name="aMemberLogFrm">
</form>	

<script>
document.querySelector("#am-delete").addEventListener('click', (e) => {
	e.preventDefault()
	if(confirm("정말 삭제하시겠습니까?"))
		document.applicantFrm.submit();
});

//로그보기
const amlog = () => {
	const title = "ApplicantMemberLog";
	const spec = "width=1200px,height=500px";
	const popup = open("", title, spec);
	
	const frm = document.aMemberLogFrm;
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


</body>
</html>