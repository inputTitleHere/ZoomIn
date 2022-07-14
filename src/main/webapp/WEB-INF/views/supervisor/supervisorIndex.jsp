<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
    
    <div id="btn-manage">
        <input type="button" value="회원관리" onclick="location.herf='<%= request.getContextPath()%>/supervisor/memberList';">
        <input type="button" value="게시판관리" onclick="location.herf='<%= request.getContextPath()%>/supervisor/BoardList';">
    </div>
</body>
</html>