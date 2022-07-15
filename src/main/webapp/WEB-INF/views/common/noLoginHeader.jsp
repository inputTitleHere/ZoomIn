<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />

<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script>

//구인자 로그인 유효성 
<% if((int) session.getAttribute("memberType") == 1) { %>
	document.applicantFrm.onsubmit = (e) => {
		const memberId = document.querySelector("#memberId");
		const password = document.querySelecotr("#password");
		
		//db에 입력된 아이디 최소글자가 3글자라 3글자 이상으로 설정
		if(!/^.{3,}$/.test(memberId.value)) {
			alert("유효한 아이디를 입력해주세요");
			memberId.select();
			return false;
		}
		
		//비번 1234통일했으므로 4글자 이상으로 설정
		if(!/^.{4,}$/.test(password.value)) {
			alert("유효한 비밀번호를 입력해주세요.");
			password.select();
			return false;
		}
	 };	

<% } %>
};
</script>
<head>
<meta charset="UTF-8">
<title>구인자 로그인</title>

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

<section>
<!-- 구인자 로그인 폼 시작 -->
	<form id="applicantFrm" name="applicantFrm" action="<%= request.getContextPath() %>/applicant/login" method="POST">
		<fieldset id="inputs">
	        <input id="username" type="text" onblur="if(this.value=='')this.value='Username';" onfocus="if(this.value=='Username')this.value='';" value="Username" />
	        <input id="password" type="text" onblur="if(this.value=='')this.value='Password';" onfocus="if(this.value=='Password')this.value='';" value="Password" />
	    </fieldset>
	    <fieldset id="actions">
	        <input type="button" value="구인자 로그인" 
	        	onclick="location.href='<%= request.getContextPath() %>/';"><!-- 여기부터 해야함 -->
	       
	    </fieldset>
	</form>

</section>


<%-- 여기에 메뉴탭 넣기 --%>

<div class="content-wrapper">

  <div class="recruit-board-wrapper">
    <table class="recruit-board-table">
      <thead>
        <tr>
          <td>마감임박 채용!</td>
          <td><a href="<%=request.getContextPath()%>/recruit/recruitBoardView">→ 더 많은 채용글 보러가기 ←</a></td>
        </tr>
      </thead>
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
        const {company_no,title, closureDate}=rbl;
				const tr = document.createElement('tr');
        tr.classList.add('recruit-board-tr')
        const companyLogoTd = document.createElement('td');
        companyLogoTd.classList.add('recruit-board-company-logo');
        //임시로 companyNo으로 대체함
        companyLogoTd.append(company_no);

        const titleTd=document.createElement('td');
        titleTd.classList.add('recruit-board-title');
        titleTd.append(title);

        const closureDateTd=document.createElement('td');
        closureDateTd.classList.add('recruit-board-closure-date');
        closureDateTd.append("마감일자 : "+closureDate);

        tr.append(companyLogoTd, titleTd, closureDateTd);
        tbody.append(tr);
			})
		},
		error:console.log
	});
};



</script>

