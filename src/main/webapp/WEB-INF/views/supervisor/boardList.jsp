<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.zoomin.supervisor.model.dto.SalaryReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
<%
	List<SalaryReview> salList = (List<SalaryReview>) request.getAttribute("salList"); 
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
    #tbl-salary-board{
    	border-collapse: collapse;
	    margin: 5px 5px;
	    display: flex;
	    justify-content: center;
	}
	tr, td, th{
	    border: 1px solid black;
	    padding: 10px;
	}
</style>
</head>
<body>
	<h1>관리자 게시판 관리</h1>
    
    <section id="search-container">
        <form action="<%= request.getContextPath()%>/supervisor/boardFinder">

            <div id="select-board">
                <select id="board-type">
                    <option value="none">== 게시판선택 ==</option>
                    <option value="salaryBoard">연봉</option>
                    <option value="companyBoard">채용</option>
                    <option value="reviewBoard">리뷰</option>
                </select>
            </div>
            <div id="select-keyword">
                <select id="board-keyword">
                    <option value="none">== 선택 ==</option>
                    <option value="boardWriter">작성자</option>
                    <option value="boardContent">내용</option>
                    <option value="boardTitle">제목</option>
                </select>
            </div>
            <div id="board-search-keyword">
                <input type="text" name="searchKeyword" size="30" placeholder="검색어를 입력하세요."value="">
                <button type="submit">검색</button>
            </div>
        </form>
    </section>
    
	<h1>연봉게시판</h1>
    <table id="tbl-salary-board">
    	<thead>
    		<tr>
    			<th>no</th>
    			<th>작성자</th>
    			<th>회사번호</th>
    			<th>카테고리</th>
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
    				<td><%= sal.getNo() %></td>
    				<td>
    					<a href="javascript:memberView();"><%= sal.getWriter() %></a>
    					<input type="hidden" name=<%= sal.getWriter() %> />   		
   					</td>
    				<td><%= sal.getCompanyNo() %></td>
    				<td><%= sal.getCategory() %></td>
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
    	</thead>
    </table>
    
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
    
    <script>
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
    </script>
    
    
    <h1>채용게시판</h1>
    <table id="tbl-company-board">
    	<thead>
    		<tr>
    			<th>no</th>
    			<th>작성자</th>
    			<th>content</th>
    			<th>등록일</th>
    		</tr>
    	</thead>
    </table>
    
    
    
    
    
    <h1>리뷰게시판</h1>
</body>
</html>