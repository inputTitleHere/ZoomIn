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
	List<CompanyReview> comList = (List<CompanyReview>) request.getAttribute("comList"); 
	List<RecruitBoard> recList = (List<RecruitBoard>) request.getAttribute("recList"); 	
	String type = (String) session.getAttribute("type");  //sal,com,req
	if(type != null) session.removeAttribute("type");	//자바변수에 담고 폐기
%>

</head>
<body>

	<div class="select">
		<select id="searchType">
			<option value="none">게시판 유형을 선택하세요</option>
            <option value="sal">연봉리뷰게시판</option>        
            <option value="com">회사리뷰게시판</option>
            <option value="rec">채용게시판</option>
        </select>
	</div>
	
	<script>

	window.onload = (e) => {
		//처음로드시 모두 감춤
		document.querySelectorAll(".board-container").forEach((div, index) => {
			div.style.display = "none";
		});
		
		//세션값에 따라 보여줌(각 서블릿에서 세션보냄)
		const type = "<%= type %>";
			
		if(type != null){	
			let id = "com";
			switch(type){
			case "sal" : id = "sal"; break;
			case "com" : id = "com"; break;
			case "rec" : id = "rec"; break;
			}
			console.log("id = " + id)
			document.querySelector("#searchType").value = id;
			document.querySelector(`#\${id}Board-container`).style.display = "inline-block";
		}
		
		else { 
			document.querySelectorAll(".board-container").forEach((div, index) => {
				div.style.display = "none";
			});
		}	
			

	}
	
		//옵션태그 바뀔때마다 표 보여줌.
		document.querySelector("#searchType").addEventListener('change', (e) => {
			document.querySelectorAll(".board-container").forEach((div, index) => {
				div.style.display = "none";
			});
			let id;
			switch(e.target.value){
			case "sal" : id = "sal"; break;
			case "com" : id = "com"; break;
			case "rec" : id = "rec"; break;
			}
			document.querySelector(`#\${id}Board-container`).style.display = "inline-block";
		})	
	</script>
 	

<section id="super-board">
    <div id="salBoard-container" class="board-container">
	    <h2 class="h2">연봉게시판</h2>
	    <form action="<%= request.getContextPath() %>/supervisor/salReviewDel" method="post" name="salDelFrm">
	    <table id="tbl-salary-board">
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
		</div>
    </div>

    <script>
    document.querySelector("#sal-delete").addEventListener('click', (e) => {
    	e.preventDefault()
    	if(confirm("정말 삭제하시겠습니까?")){
    		document.querySelector("#searchType").value = "sal";
    		document.salDelFrm.submit();
    	}
    });
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
    
    
    <%-- 회사리뷰 게시판 --%>
    <div id="comBoard-container" class="board-container">
	<h2 class="h2">회사 리뷰 게시판</h2>
	<form action="<%= request.getContextPath() %>/supervisor/comReviewDel" method="post" name="comDelFrm">
    <table id="tbl-company-board">
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
		</div>
	</div>
	<script>
	document.querySelector("#com-delete").addEventListener('click', (e) => {
    	e.preventDefault()
    	if(confirm("정말 삭제하시겠습니까?"))
    		document.comDelFrm.submit();
    });
	</script>

	<%--채용게시판 --%>
	<div id="recBoard-container" class="board-container">
		<h2 class="h2">채용 게시판</h2>
		<form action="<%= request.getContextPath() %>/supervisor/comRecruitDel" method="post" name="recDelFrm">
		<div id="boder">
		<table id="tbl-recruit-board">
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
	<script>
	document.querySelector("#rec-delete").addEventListener('click', (e) => {
    	e.preventDefault()
    	if(confirm("정말 삭제하시겠습니까?"))
    		document.recDelFrm.submit();
    });
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
</section>  	
<style>

</style>
section#super-board{
	text-align: center;
}

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