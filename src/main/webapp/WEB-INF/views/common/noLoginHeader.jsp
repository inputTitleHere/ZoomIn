<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
</head>
<body>
<header>
	<div id="account">

		<%-- 임시로 관리자 탭 만들었습니다. --%>
		<a href="<%= request.getContextPath() %>/supervisor/supervisorView">관리자</a>

		<a href ="<%= request.getContextPath() %>/recruit/login" id="logIn">구인자 로그인</a>
		<a href ="<%= request.getContextPath() %>/applicant/login" id="logIn">구직자 로그인</a>
		<a href ="<%= request.getContextPath() %>/recruit/join" id="recruiterAsign">구인자 회원가입</a>
		<a href ="<%= request.getContextPath() %>/applicant/join" id="applicantAsign">구직자 회원가입</a>

	</div>
	<div id="logodiv">
		<a href="<%=request.getContextPath()%>/index.jsp">
			<img id="logo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
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
    	console.log(request); //{term: 'a'} 사용자 입력값 = request객체의 term속성값 -> 서버로 보내서 서버에서 해당하는 이름들만 추려오기.
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
	
	
	document.querySelector("#search-btn").addEventListener('click', (e) => {
		const val = document.querySelector("#searchBox").value;
		location.href="<%= request.getContextPath() %>/search/company?userVal="+val;
	});
	
</script>

<%-- 여기에 메뉴탭 넣기 --%>


