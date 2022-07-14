<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />

</head>
<body>
<header>
	<div id="account">
		<a href ="" id="logIn">로그인</a>
		<a href ="" id="recruiterAsign">구인자 회원가입</a>
		<a href ="" id="applicantAsign">구직자 회원가입</a>
	</div>
	<div id="logodiv">
		<img id="logo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
	</div>
	<div id="searchBoxdiv">
		<input id="searchBox" type="text">
		<button class="custom-btn btn-3"><span>검색</span></button>
	</div>
</header>

<%-- 여기에 메뉴탭 넣기 --%>

<div class="content-wrapper">

  <div class="recruit-board-wrapper">
    <table class="recruit-board-table">
      <tbody></tbody>
    </table>
  </div>

</div>


<script>
// AJAX script for loading first window : 채용글 목록

window.addEventListener('load',()=>{
	indexRecruiterBoard();
})


const indexRecruiterBoard=()=>{
	$.ajax({
		url:"<%=request.getContextPath()%>/recruit/board/indexRecruitBoard",
		success(response){
			console.log(response);
      const tbody = document.querySelector('.recruit-board-table tbody');
			response.forEach((rbl)=>{
				const tr = document.createElement('tr');
        const td = document.createElement('td');
        td.append(rbl.)
			})
		},
		error:console.log
	});
};



</script>
