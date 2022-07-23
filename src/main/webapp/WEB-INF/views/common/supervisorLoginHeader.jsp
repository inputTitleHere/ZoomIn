<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/supervisor/supervisor.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<header>
	<div id="account">
		<a href ="<%= request.getContextPath() %>/applicant/logout" id="logOut">로그아웃</a>                                                                                  
	</div>
	<div id="superlogodiv">
		<img id="superlogo" alt="관리자 랜딩페이지로" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
		<script>
			document.querySelector("#superlogo").addEventListener('click', (e) => {
				location.href = "<%= request.getContextPath() %>/supervisor/supervisorView"
			});
		</script>
	</div>
	
	<nav>
		<ul class="super-nav">
			<li class="magage-home"><a href="<%= request.getContextPath()%>">홈으로</a></li>
			<li class="magage-member"><a href="<%= request.getContextPath()%>/supervisor/aMemberList">회원관리</a></li>
			<li class="magage-board"><a href="<%= request.getContextPath()%>/supervisor/salBoardList">게시판관리</a></li>
			<li class="magage-satatistic"><a href="<%= request.getContextPath()%>/supervisor/Statistic">통계관리</a></li>
			<li class="magage-Datalog"><a href="<%= request.getContextPath()%>/supervisor/Datalog">수정/삭제 데이터 관리</a></li>
		</ul>
	</nav>
	
</header>
