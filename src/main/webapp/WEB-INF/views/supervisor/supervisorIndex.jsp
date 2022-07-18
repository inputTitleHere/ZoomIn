<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>

<%
	int todayComCnt = session.getAttribute("todayComCnt") == null ? 0 : (int) session.getAttribute("todayComCnt");
	int todaySalCnt = session.getAttribute("todaySalCnt") == null ? 0 : (int) session.getAttribute("todaySalCnt");
%>

    <div id="btn-manage">
        <input type="button" value="회원관리" onclick="location.href='<%= request.getContextPath()%>/supervisor/memberList';">
        <input type="button" value="게시판관리" onclick="location.href='<%= request.getContextPath()%>/supervisor/BoardList';">
    </div>
    
    <h1>통계관리</h1>
    
    <div class="date-container">
	    <div id="statistic-date-start">
	        <input type="date">
	    </div>
	    <span> ~ </span> 
    	<div id="statistic-date-end">
	        <input type="date">
	    </div>
    </div>
    
    
    <div id="btn-statistic">
        <input type="button" value="방문자 수 조회" onclick="location.href='<%= request.getContextPath()%>/supervisor/visitMember';">
        <input type="button" value="게시글 수 조회" onclick="location.href='<%=request.getContextPath()%>/supervisor/countBoard';">
    </div>

    <table id="tbl-visit-statistic">
        <tr>
	         <th>오늘 방문자 수 </th>
	         <td><%= session.getAttribute("todayCount") == null ? "-" : session.getAttribute("todayCount") %>명</td>
        </tr>
		<tr>
           <th>총 방문자 수</th>
           <td><%= session.getAttribute("totalCount") == null? "-" : session.getAttribute("totalCount") %>명</td>
		</tr>            
    </table>
    
    <table id="tbl-board-statistic">
    	<tr>
    		<th>오늘 게시글 수</th>
    		<td><%= todayComCnt + todaySalCnt %>개</td>
    	</tr>
    	<tr>
    		<th>총 게시글 수</th>
    		<td><%= session.getAttribute("totalBoardCnt") %>개</td>
    	</tr>
    </table>
    
    
    <%-- 임시 테이블 스타일 --%>
    <style>
        #tbl-visit-statistic, #tbl-board-statistic{
            border: 1px solid black;
            border-collapse: collapse;
            margin: 20px;
        }
        tr, td, th{
            border: 1px solid black;
            padding: 30px;
        }
        .date-container{
        	display: flex;
        }
    </style>
    
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