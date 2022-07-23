<%@page import="com.kh.zoomin.common.ZoominUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.RecruitBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
<%
	List<RecruitBoard> recList = (List<RecruitBoard>) request.getAttribute("recList"); 	
%>

</head>
<body>
<input type="button" class="btn-board" id="salBoard" value="연봉" onclick="location.href='<%= request.getContextPath()%>/supervisor/salBoardList';"/>
<input type="button" class="btn-board" id="comBoard" value="회사" onclick="location.href='<%= request.getContextPath()%>/supervisor/comBoardList';"/>
<input type="button" class="btn-board" id="recBoard" value="채용" onclick="location.href='<%= request.getContextPath()%>/supervisor/recBoardList';"/>


	<%--채용게시판 --%>
<section id="super-board">

	<div id="recBoard-container" class="board-container">
		<h2 class="h2">채용 게시판</h2>
		<form action="<%= request.getContextPath() %>/supervisor/comRecruitDel" method="post" name="recDelFrm">
		<div id="boder">
		<table id="tbl-recruit-board" class="tb-manage" >
			<thead>
			<tr>
				<th><input type="checkbox" name="allChk" class="allChk" /></th>
				<th>no</th>
				<th>카테고리</th>
				<th>회사명</th>
				<th>Recruiter</th>
				<th>title</th>
				<th>마감일</th>
				<th>등록일</th>
			</tr>
			</thead>
			<tbody>
			<%
    			if(recList != null && !recList.isEmpty()){
    				for(RecruitBoard rec : recList){
    		%>	
    		<tr>
    			<td>
  					<input type="checkbox" name="chk" value="<%= rec.getNo() %>" />
				</td>
    			<td><%= rec.getNo() %></td>
    			<td><%= rec.getCategory() %></td>
    			<td><%= rec.getCompanyName()%></td>
    			<td><%= rec.getRecruiter() %></td>
    			<td>
    				<a href="javascript:recruitView();"><%= ZoominUtils.escapeXml(rec.getTitle()) %></a>  			
   				</td>
    			<td><%= new SimpleDateFormat("yyyy-MM-dd").format(rec.getClosureDate()) %></td>
    			<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rec.getRegDate()) %></td>
    		</tr>
    		<%
    				}
    			}
    		%>
			</tbody>			
		</table>
		</div>
		</form>
				<div id='super-pagebar'>
					<%= request.getAttribute("recPagebar") %>			
				</div>
			<div class="under-tbl-btn">
	    		<input type="submit" class="btn" id="rec-delete" value="삭제"/>
			</div>
	</div>
</section>
	<script>
	document.querySelector("#rec-delete").addEventListener('click', (e) => {
    	e.preventDefault()
    	if(confirm("정말 삭제하시겠습니까?"))
    		document.recDelFrm.submit();
    });
	
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
	
	<%-- 채용정보조회(팝업) --%>
    		<%
	    		if(recList != null && !recList.isEmpty()){
					for(RecruitBoard rec : recList){
    		%>   	
    <form action="<%=request.getContextPath()%>/recruit/board/viewRecruitBoard?boardNo=<%=rec.getNo()%>" name="recruitViewFrm">
    		<input type="hidden" name="boardNo" value="<%= rec.getNo() %>"/>
    		<%
    				}
    			}
    		%>
    </form>
	
</body>
</html>