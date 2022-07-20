<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
boolean result = (boolean) request.getAttribute("checkId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<div id="checkId-container">
		<%
		if (result) {
		%>
		<p>
			<span><%=request.getParameter("id")%></span>는 사용가능합니다.
		</p>
		<button type="button" onclick="popupClose();">닫기</button>
		<%
		} else {
		%>
		<p>
			<span id="checked"><%=request.getParameter("id")%></span>는 이미 사용중입니다.
		</p>
		<form action="<%=request.getContextPath()%>/recruit/checkId"
			name="checkIdRFrm">
			<input type="text" name="id" placeholder="아이디를 입력해주세요" /> <input
				type="submit" value="중복체크" />
		</form>
	
		<script>
			document.checkIdRFrm.onsubmit = (e) => {
				const frm = e.target;
				if(!/^[a-zA-Z0-9]{3,}$/.test(frm.id.value)){
					alert("유효한 아이디를 입력해주세요.");
					frm.id.select();
					return false;
				}
			};
			
		</script>
	<% }%>
	</div>
	<script>
	const popupClose = () => {
		const frm = opener.document.joinRFrm;
		frm.idValid.value = 1;
		frm.id.value = "<%= request.getParameter("id")%>";
		
		//창닫기
		self.close(); 
	};
	</script>

</body>
</html>