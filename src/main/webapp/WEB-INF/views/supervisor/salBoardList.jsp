<%@page import="com.kh.zoomin.supervisor.model.dto.RecruitBoard"%>
<%@page import="com.kh.zoomin.common.ZoominUtils"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.CompanyReview"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.SalaryReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
<%
	List<SalaryReview> salList = (List<SalaryReview>) request.getAttribute("salList"); 

%>

</head>
<body>
<div id="board-btn-container">
<input type="button" class="btn-board" id="salBoard" value="연봉" onclick="location.href='<%= request.getContextPath()%>/supervisor/salBoardList';"/>
<input type="button" class="btn-board" id="comBoard" value="회사" onclick="location.href='<%= request.getContextPath()%>/supervisor/comBoardList';"/>
<input type="button" class="btn-board" id="recBoard" value="채용" onclick="location.href='<%= request.getContextPath()%>/supervisor/recBoardList';"/>
</div>



<section id="super-board">
    <div id="salBoard-container" class="board-container">
	    <h2 class="h2">연봉게시판</h2>
	    <form action="<%= request.getContextPath() %>/supervisor/salReviewDel" method="post" name="salDelFrm">
	    <table id="tbl-salary-board" class="tb-manage">
    		<thead>
    		<tr>
    			<th><input type="checkbox" name="allChk" class="allChk" /></th>
    			<th>no</th>
    			<th>카테고리</th>
    			<th>회사명</th>
    			<th>작성자</th>
    			<th>연봉(만원)</th>
    			<th>근속연수(년)</th>
    			<th>직급</th>
    			<th>등록일</th>
    		</tr>
    		</thead>
    		<tbody>
    		<%
    			if(salList != null && !salList.isEmpty()){
    				for(SalaryReview sal : salList){
    		%>		
    			<tr>
    				<td>
    					<input type="checkbox" name="chk" value="<%= sal.getNo() %>" />
   					</td>
    				<td>
    					<a href="#"><%= sal.getNo() %></a>
    				</td>
    				<td><%= sal.getCategory() %></td>
    				<td><%= sal.getCompanyName() %></td>
    				<td>
    					<a href="javascript:memberView();"><%= sal.getWriter() %></a>
    					<input type="hidden" name=<%= sal.getWriter() %> />   		
   					</td>  					
    				<td><%= sal.getSalary() %></td>
    				<td><%= sal.getWorkYear() %></td>
    				<td><%= sal.getJobPosition() %></td>
    				<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(sal.getRegDate()) %></td>
    			</tr>
    		<%
    				}
    			}
    			else {
    		%>
    			<tr>
    				<td colspan="8">조회된 게시글이 없습니다.</td>
    			</tr>
    		<% 	} %>
    		</tbody>
    	</table>
	</form>
	    <div id='pagebar'>
			<%= request.getAttribute("salPagebar") %>			
		</div>
		<div class="under-tbl-btn">
		<input type="submit" class="btn" id="sal-delete" value="삭제"/>
		<input type="button" class="btn" id="sal-log" onclick="javascript:sallog();" value="로그보기"/>
		</div>
    </div>
    
<!-- 로그보기 폼 -->
<form action="<%= request.getContextPath()%>/supervisor/salBoardLog" name="salLogFrm">
</form>	

    <script>
    document.querySelector("#sal-delete").addEventListener('click', (e) => {
    	e.preventDefault()
    	if(confirm("정말 삭제하시겠습니까?")){
    		document.querySelector("#searchType").value = "sal";
    		document.salDelFrm.submit();
    	}
    });
    
    
	//로그보기 (새창 띄우기)
	const sallog = () => {
		const title = "SalaryBoardLog";
		const spec = "width=1200px,height=500px";
		const popup = open("", title, spec);
		
		const frm = document.salLogFrm;
		frm.target = title;
		frm.submit();
	}
    </script>
    
    <%-- 회원정보조회(팝업) --%>
    <form action="<%= request.getContextPath() %>/supervisor/memberView" name="memberViewFrm">
    		<%
    			if(salList != null && !salList.isEmpty()){
    				for(SalaryReview sal : salList){
    		%>
    	<input type="hidden" name="memberId" value="<%= sal.getWriter() %>" />
    		<%
    				}
    			}
    		%>
    </form>
    
	
</section>  	


<script>
	//회원정보 상세보기
    const memberView = () => {
    	//팝업창 제어
    	const title = "memberViewPopup";
    	const spec = "width=500px,height=800px";
    	const popup = open("", title, spec);
    	
    	//form제어
    	const frm = document.memberViewFrm;
    	frm.target = title;
    	frm.submit();
    }
    
    //채용글 상세보기
    const recruitView = () => {
    	const title = "recruitViewPopup";
    	const spec = "width=1000px,height=800px";
    	const popup = open("", title, spec);
    	
    	const frm = document.recruitViewFrm;
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