<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common/common.css" />
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">

#account {
	text-allign: center;
	width: 
}

ul li {
	list-style: none;
}

a {
	text-decoration: none;
	color: #333;
}

#manu_a {
	text-allign: center;
}

#menu {
	font:bold 15px "malgun gothic";
	width:150px;
	height: 50px;
	background: #cccccc05;
	color: black;
	line-height: 50px;
	margin: 0 auto;
	text-allign: center;

}

#menu > ul > li {
	float:left;
	width:150px;
	position:relative;
}

#menu > ul > li > ul {
	width: 130px;
	display: none;
	position: absolute;
	font-size: 14px;
	background: white;
}

#menu > ul > li:hover > ul {
	display:block;
}

#menu > ul > li > ul > li:hover {
	background: orange;
	transition: ease 2s;
}

</style>
<body>
	<header>
		<div style="width: 168%;" id="account">
			<div id="menu">
				<ul>
					<li><span class="menu">MENU</span>
						<ul>
							<li><a href="<%=request.getContextPath()%>/ResumeServlet"
								id="writeResume">이력서 작성</a></li>
							<li><a href="<%=request.getContextPath()%>/recruit/update"
								id="recruitUpdate">회원정보수정</a></li>
							<li><a href="<%=request.getContextPath()%>/recruit/delete"
								id="recruitDelete">회원탈퇴</a></li>
							<li><a href="<%=request.getContextPath()%>/recruit/logout"
								id="logOut">로그아웃</a></li>
						</ul>
					</li>
				</ul>

			</div>
		</div>
		</div>

		<div id="logodiv">
			<img id="logo" alt=""
				src="<%=request.getContextPath()%>/images/zoominlogo.jpg">
		</div>
		<div id="searchBoxdiv">
			<input id="searchBox" type="text">
			<button class="custom-btn btn-3">
				<span>검색</span>
			</button>

		</div>
	</header>