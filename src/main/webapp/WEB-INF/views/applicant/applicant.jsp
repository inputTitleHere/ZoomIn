<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/applicant.css" />
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<section id="applicantSection">
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
		applicantAjax(1);
	}; 
	
	document.getElementById("companyInfo").onclick = (e) =>{
		companyAjax(1);
	};
	
	document.getElementById("salaryInfo").onclick = (e) =>{
		/* $(".context").empty();
		$(".context").append(info); */
	};
	
	function applicantAjax(cPage){
		$(".context").empty();
		$.ajax({
			url:"<%=request.getContextPath()%>/applicant/applicantBoardList?cPage="+cPage,
			success(response){
				console.log(response);
				var info = response;
				$(".context").append(info);
				
				$(".paging").on("click",function(){
	      			var id = this.id;
	      			var page = id.substring(4);
	      			if(page == 0){
	      				page = -1;
	      			}
	      			applicantAjax(page);
	    			console.log("paging"+ page);
	    		});
			},
			error:console.log
		});
	}
	
	function companyAjax(cPage){
		$(".context").empty();
		$.ajax({
			url:"<%=request.getContextPath()%>/CompanyBoardList?cPage="+cPage,
			success(response){
				console.log(response);
				var cInfo = "<table class='allCompany'>\n";
		      	for(var i =0; i < 3; i++){
		      			cInfo += "<tr class='companyList'>\n";
		      		for(var j =0; j < 3; j++){
		      			if(response.result[i*3+j] == null){
		      				cInfo += "<td></td>";	
		      			}else{
		      				cInfo += "<td class='company'>\n";
		      				cInfo += response.result[i*3+j].companyName+"<br/>" +"<br/>" + response.result[i*3+j].companyInfo + "<br/>" +"<br/>" + response.result[i*3+j].companyNo + "<br/>" + "<br/>";
		      				cInfo += "</td>\n";		      				
		      			}
		      		}
		      			cInfo += "</tr>\n";
		      	}
		      		cInfo += "</table>\n";
		      		cInfo += response.pageBar;
		      		$(".context").append(cInfo);
		      		
		      		$(".paging").on("click",function(){
		      			var id = this.id;
		      			var page = id.substring(4);
		      			if(page == 0){
		      				page = -1;
		      			}
		      			companyAjax(page);
		    			console.log("paging"+ page);
		    		});
			},
			error:console.log
		});
	}
	
	$(document).ready(function(){
		applicantAjax(1);
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

