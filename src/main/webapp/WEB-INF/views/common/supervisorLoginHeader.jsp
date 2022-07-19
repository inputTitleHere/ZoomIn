<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/supervisor/supervisor.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<head>
<meta charset="UTF-8">
<title>관리자모드</title>

<style>
   #btn-manage{
       width: 200px;
       height: 100px;
   }
</style>

</head>
<body>
<header>
	<div id="account">
		<a href ="" id="logOut">로그아웃</a>
	</div>
	<div id="superlogodiv">
		<img id="superlogo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
		<script>
			document.querySelector("#superlogo").addEventListener('click', (e) => {
				location.href = "<%= request.getContextPath() %>/supervisor/supervisorView"
			});
		</script>
	</div>
	
	<nav>
		<ul class="super-nav">
			<li class="magage-member"><a href="<%= request.getContextPath()%>/supervisor/memberList">회원관리</a></li>
			<li class="magage-board"><a href="<%= request.getContextPath()%>/supervisor/BoardList">게시판관리</a></li>
			<li class="magage-satatistic"><a href="<%= request.getContextPath()%>/supervisor/Statistic">통계관리</a></li>
		</ul>
	</nav>
	
</header>
