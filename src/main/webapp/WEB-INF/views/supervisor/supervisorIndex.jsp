<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>

    <div id="btn-manage">
        <input type="button" value="회원관리" onclick="location.href='<%= request.getContextPath()%>/supervisor/memberList';">
        <input type="button" value="게시판관리" onclick="location.href='<%= request.getContextPath()%>/supervisor/BoardList';">
    </div>
    
    <h1>통계관리</h1>
    
    <div id="statistic-date">
        <input type="date">
    </div>
    
    <div id="btn-statistic">
        <input type="button" value="방문자 수 조회">
        <input type="button" value="게시글 수 조회">
    </div>

    <table id="tbl-statistic">
            <th>오늘 방문자 수 </th>
            <td><%= session.getAttribute("todayCount") %>명</td>
    </table>
    
    <%-- 임시 테이블 스타일 --%>
    <style>
        #tbl-statistic{
            border: 1px solid black;
            border-collapse: collapse;
            margin: 20px;
        }
        tr, td, th{
            border: 1px solid black;
            padding: 30px;
        }
    </style>
</body>
</html>