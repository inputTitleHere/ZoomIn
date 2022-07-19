<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<section>
<div class="title" id="">
	<a id="recruitInfo">채용정보</a>
	<a id="companyInfo">기업정보</a>
	<a id="salaryInfo">연봉정보</a>
</div>
<div class="context">
	
</div>

</section>
<script>
	document.getElementById("recruitInfo").onclick = (e) =>{
		$(".context").empty();
		var info = `<%@ include file="/WEB-INF/views/applicant/recruitInfo.jsp" %>`;
		$(".context").append(info);
	};
	
	document.getElementById("companyInfo").onclick = (e) =>{
		$(".context").empty();
		var info = `<%@ include file="/WEB-INF/views/applicant/companyInfo.jsp" %>`;
		$(".context").append(info);
	};
	
	document.getElementById("salaryInfo").onclick = (e) =>{
		$(".context").empty();
		var info = `<%@ include file="/WEB-INF/views/applicant/salaryInfo.jsp" %>`;
		$(".context").append(info);
	};
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>