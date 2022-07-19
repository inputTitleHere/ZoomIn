<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
    
<%
	int todayComCnt = session.getAttribute("todayComCnt") == null ? 0 : (int) session.getAttribute("todayComCnt");
	int todaySalCnt = session.getAttribute("todaySalCnt") == null ? 0 : (int) session.getAttribute("todaySalCnt");
%>

	  <h1>관리자 통계관리</h1>
		
		<form action="" name="frm">
			<%--날짜 입력 --%>
			<div class="date-container">
			    <div id="statistic-date-start">
			    <label for="dateStart">시작일</label>
			        <input type="date" name="dateStart" value=<%= request.getAttribute("dateStart") %>>
			    </div>

		    	<div id="statistic-date-end">
		    	<label for="dateEnd">종료일</label>
			        <input type="date" name="dateEnd" value=<%= request.getAttribute("dateEnd") %>>
			    </div>
		    </div>
		    
		    <div id="btn-statistic">
				<button type="submit" formaction="<%= request.getContextPath()%>/supervisor/visitMember" >방문자 조회</button>
		        <button type="submit" formaction="<%=request.getContextPath()%>/supervisor/countBoard">게시글 조회</button>	
	        </div>	
		</form>
		
    <section class="tbl-container">
	<table id="tbl-visit-statistic">
        <tr>
        	<th>날짜별 조회</th>
        	<td><%= session.getAttribute("visitCount") == null ? "-" : session.getAttribute("visitCount") %>명</td>
        </tr>
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
    		<th>날짜별 조회</th>
    		<td></td>
    	</tr>
    	<tr>
    		<th>오늘 게시글 수</th>
    		<td><%= todayComCnt + todaySalCnt %>개</td>
    	</tr>
    	<tr>
    		<th>총 게시글 수</th>
    		<td><%= session.getAttribute("totalBoardCnt") %>개</td>
    	</tr>
    </table>
    </section>
    
    
    
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
</body>
</html>