
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.common.ZoominUtils"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.CompanyReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
<%
 List<CompanyReview> comList = (List<CompanyReview>) request.getAttribute("comList"); 
%>

</head>
<body>
<div id="board-btn-container">
<input type="button" class="btn-board" id="salBoard" value="연봉" onclick="location.href='<%= request.getContextPath()%>/supervisor/salBoardList';"/>
<input type="button" class="btn-board" id="comBoard" value="회사" onclick="location.href='<%= request.getContextPath()%>/supervisor/comBoardList';"/>
<input type="button" class="btn-board" id="recBoard" value="채용" onclick="location.href='<%= request.getContextPath()%>/supervisor/recBoardList';"/>
</div>
    <%-- 회사리뷰 게시판 --%>
<section id="super-board">
   <div id="comBoard-container" class="board-container">
	<h2 class="h2">회사 리뷰 게시판</h2>
	<form action="<%= request.getContextPath() %>/supervisor/comReviewDel" method="post" name="comDelFrm">
    <table id="tbl-company-board" class="tb-manage" >
    	<thead>
    		<tr>
    			<th><input type="checkbox" name="allChk" class="allChk" /></th>
    			<th>no</th>
    			<th>회사명</th>
    			<th>content</th>
    			<th>작성자</th>
    			<th>등록일</th>
    		</tr>
    	</thead>
    	<tbody>
    		<%
    			if(comList != null && !comList.isEmpty()){
    				for(CompanyReview com : comList){
    		%>	
    		<tr>
    			<td>
  					<input type="checkbox" name="chk" value="<%= com.getNo() %>" />
				</td>
    			<td><%= com.getNo() %></td>
    			<td><%= com.getCompanyName() %></td>
    			<td>
    				<a href="#"><%= ZoominUtils.escapeXml(com.getContent()) %></a>
    			</td>
    			<td><%= com.getId() %></td>
    			<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(com.getRegDate()) %></td>
    		</tr>
    		<%
    				}
    			}
    		%>
    	</tbody>	
    </table>
		</form>
			<div id='super-pagebar'>
				<%= request.getAttribute("comPagebar") %>
			</div>
	    <div class="under-tbl-btn">
			<input type="submit" class="btn" id="com-delete" value="삭제"/>
			<input type="button" class="btn" id="com-log" onclick="javascript:comlog();" value="로그보기"/>
		</div>
	</div>
  </section>

<form action="<%= request.getContextPath()%>/supervisor/comBoardLog" name="comLogFrm">
</form>
	
	<script>
	document.querySelector("#com-delete").addEventListener('click', (e) => {
    	e.preventDefault()
    	if(confirm("정말 삭제하시겠습니까?"))
    		document.comDelFrm.submit();
    });

	//로그보기 (새창 띄우기)
	const comlog = () => {
		const title = "CompanyBoardLog";
		const spec = "width=1200px,height=500px";
		const popup = open("", title, spec);
		
		const frm = document.comLogFrm;
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