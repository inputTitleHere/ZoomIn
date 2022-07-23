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
%>

<style>
form{
    display: flex;
    margin: 0 10px;
}
div{
    margin: 0 20px;
}
#search-container{
	height: 20%;
}
#tbl-salary-board, #tbl-company-board, #tbl-recruit-board{
	border-collapse: collapse;
	 margin: 5px 5px;
	 display: flex;
	 justify-content: center;
}
tr, td, th{
    border: 1px solid black;
    padding: 10px;
}
/*페이지바*/
	div#pagebar{margin-top:10px; text-align:center;}
	div#pagebar span.cPage{color: #0066ff; margin-right: 5px;}
	div#pagebar a{margin-right: 5px;}
/*테이블 숨김처리*/
.salary-board-manage{
	display:block;
}
.company-board-manage{
	display:block;
}
.recruit-board-manage{
	display:block;
}
#salDelFrm{
	display: flex;
    margin: 0 10px;
    justify-content: center;
    flex-direction: column;
    align-content: space-around;
}
#comDelFrm{
	display: flex;
    margin: 0 10px;
    justify-content: center;
    flex-direction: column;
    align-content: space-around;
}
#reviewDelFrm{
	display: flex;
    margin: 0 10px;
    justify-content: center;
    flex-direction: column;
    align-content: space-around;
}
</style>
</head>
<body>

	<div class="btn-board" id="search-board">
		<button id="btn-salary">연봉게시판</button>
		<button id="btn-company">회사리뷰게시판</button>
		<button id="btn-recruit">채용게시판</button>
	</div>
	
	<script>
		document.querySelector("#btl-salary").addEventListener('click', (e) => {
			document.querySelector(".salary-board-manage").style.display = "block";
			document.querySelector(".company-board-manage").style.display = "none";
			document.querySelector(".recruit-board-manage").style.display = "none";
		});
		document.querySelector("#btl-company").addEventListener('click', (e) => {
			document.querySelector(".company-board-manage").style.display = "block";
			document.querySelector(".salary-board-manage").style.display = "none";
			document.querySelector(".recruit-board-manage").style.display = "none";
		});
		document.querySelector("#btl-recruit").addEventListener('click', (e) => {
			document.querySelector(".recruit-board-manage").style.display = "block";
			document.querySelector(".salary-board-manage").style.display = "none";
			document.querySelector(".company-board-manage").style.display = "none";
		});
		
	</script>


    <div class="salary-board-manage">
    <form action="<%= request.getContextPath() %>/supervisor/salReviewDel" method="post" name="salDelFrm">
    	<input type="button" id="btn-salDel" value="연봉게시판 삭제"/>
    <script>
    document.querySelector("#btn-salDel").addEventListener('click', (e) => {
    	if(confirm("정말 삭제하시겠습니까?"))
    		document.salDelFrm.submit();
    });

    </script>
    <h1>연봉게시판</h1>
    <table id="tbl-salary-board">
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
    </table>
	</form>
	    <div id='pagebar'>
			<%= request.getAttribute("salPagebar") %>
			
		</div>
    </div>
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
    <div class="company-board-manage">
	<form action="<%= request.getContextPath() %>/supervisor/comReviewDel" method="post" name="comDelFrm">
	<h1>회사 리뷰 게시판</h1>
    <table id="tbl-company-board">
    		<tr>
    			<th><input type="checkbox" name="allChk" class="allChk" /></th>
    			<th>no</th>
    			<th>회사명</th>
    			<th>content</th>
    			<th>작성자</th>
    			<th>등록일</th>
    		</tr>
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
    </table>
	    <div class="under-table">
			<div id='pagebar'>
				<%= request.getAttribute("comPagebar") %>
			</div>
			<input type="button" id="btn-comDel" value="회사리뷰삭제"/>
		</div>
		</form>
	</div>
	<script>
		document.querySelector("#btn-comDel").addEventListener('click', (e) => {
			if(confirm("정말 삭제하시겠습니까?"))
				document.comDelFrm.submit();
		});
	</script>

	<%--채용게시판 --%>
	<div class="recruit-board-manage">
		<form action="<%= request.getContextPath() %>/supervisor/comRecruitDel" method="post" name="recDelFrm">
		<h1>채용 게시판</h1>
		<table id="tbl-recruit-board">
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
		</table>
			<div class="under-table">
				<div id='pagebar'>
					<%= request.getAttribute("recPagebar") %>			
				</div>
	    		<input type="submit" id="btn-recDel" value="채용글삭제"/>
			</div>
		</form>
	</div>
	<script>
		document.querySelector("#btn-recDel").addEventListener('click', (e) => {
			if(confirm("정말 삭제하시겠습니까?"))
				document.recDelFrm.sumit();
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