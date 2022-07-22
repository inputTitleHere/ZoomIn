<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/header.jsp" %>

	<h1>ZOOMIN!!!</h1>



<div class="simple-recruit-board">
<%@ include file="/WEB-INF/views/common/simpleRecruitBoard.jsp" %>
</div>

<!-- 일단 박우석 실험삼아 넣어보기 -->
<a href="<%= request.getContextPath() %>/review/company/companyReviewList">회사리뷰</a>

<!-- 일단 footer 없음 -->
</body>
</html>