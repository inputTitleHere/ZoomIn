<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />

</head>
<body>
<header>
	<div id="account">
		<a href ="<%= request.getContextPath() %>/recruit/login" id="logIn">구인자 로그인</a>
		<a href ="<%= request.getContextPath() %>/applicant/login" id="logIn">구직자 로그인</a>
		<a href ="" id="recruiterAsign">구인자 회원가입</a>
		<a href ="<%= request.getContextPath() %>/applicant/join" id="applicantAsign">구직자 회원가입</a>
	</div>
	<div id="logodiv">
		<a href="<%=request.getContextPath()%>/index.jsp">
			<img id="logo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
		</a>
	</div>
	<div id="searchBoxdiv">
		<input id="searchBox" type="text">
		<button class="custom-btn btn-3"><span>검색</span></button>
	</div>
</header>

<%-- 여기에 메뉴탭 넣기 --%>


