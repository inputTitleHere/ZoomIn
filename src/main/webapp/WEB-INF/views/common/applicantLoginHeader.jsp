<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/views/common/header.jsp" %>	 --%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common/common.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/applicant/applicantLoginHeader.css"/>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/search.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">

<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>

<body>
<header>
	<div style="width: 168%;" id="account">
		<div id="menu">
			<ul>
				<li>
					<span class="menu">MENU</span>
					<ul>
						<li><a href="<%=request.getContextPath()%>/ResumeServlet"	id="writeResume">이력서 작성</a></li>
						<li><a href="<%=request.getContextPath()%>/applicant/update" id="myPage">마이페이지</a></li>
						<li><a href="<%=request.getContextPath()%>/applicant/logout" id="logOut">로그아웃</a></li>
					</ul>
				</li>
			</ul>

		</div>
	</div>

		<div id="logodiv">
			<a href="<%= request.getContextPath() %>">
				<img id="logo" alt=""
					src="<%=request.getContextPath()%>/images/zoominlogo.jpg">
			</a>
		</div>
		
		<div id="searchBoxdiv">
			<input id="searchBox" type="text" name="userVal">
			<button class="custom-btn btn-3" id="search-btn"><span>검색</span></button>
		</div>
	</header>
<script>
	$("#searchBox").autocomplete({
		source(request, response){
    	//console.log(request); //{term: 'a'} 사용자 입력값 = request객체의 term속성값 -> 서버로 보내서 서버에서 해당하는 이름들만 추려오기.
    	const {term} = request;
    	if(!/.+/.test(term)) return;	//아무글자 한글자 이상
			
			$.ajax({
				url : "<%= request.getContextPath()%>/search",
				dataType : "json",
				data : {term},
				method : "get",
				success(resultList){
					console.log(resultList);
					response(resultList);
				},
				error(jqxhr, statusText, err){
	    			console.log(jqxhr, statusText, err);
	    		}
			});
		},
		focus(e, selected){
	    	 return false;	//focus가 일어나도 선택되지 않게 한다. 
		}
	});
	
	//버튼 클릭시 해당문자 포함하는 회사자동완성 검색
	document.querySelector("#search-btn").addEventListener('click', (e) => {
		const val = document.querySelector("#searchBox").value;
		location.href="<%= request.getContextPath() %>/search/company?userVal="+val;	
	});
	
</script>
