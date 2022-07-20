<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/applicant.css" />
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<section>
<div class="title" id="subtitle">
	<a id="recruitInfo">채용정보</a>
	<a id="companyInfo">기업정보</a>
	<a id="salaryInfo">연봉정보</a>
</div>
<div class="context">
	
</div>

</section>
<script>
	document.getElementById("recruitInfo").onclick = (e) =>{
	/* 	$(".context").empty();
		$(".context").append(info);*/
	}; 
	
	document.getElementById("companyInfo").onclick = (e) =>{
		$(".context").empty();
		$.ajax({
			url:"<%=request.getContextPath()%>/CompanyBoardList",
			success(response){
				console.log(response);
				var cInfo = "<table>\n";
		      	for(var i =0; i < response.length/3; i++){
		      			cInfo += "<tr>\n";
		      		for(var j =0; j < response.length/3; j++){
		      				cInfo += "<td>\n";
		      				cInfo += JSON.stringify(response[i*3+j])+"\n";
		      				cInfo += "</td>\n";
		      		}
		      			cInfo += "</tr>\n";
		      	}
		      		cInfo += "</table>\n";
		      		
		      		$(".context").append(cInfo);
			},
			error:console.log
		});
	};
	
	document.getElementById("salaryInfo").onclick = (e) =>{
		/* $(".context").empty();
		$(".context").append(info); */
	};
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

