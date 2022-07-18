<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
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
	</div>
</header>
