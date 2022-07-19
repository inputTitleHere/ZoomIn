<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script ></script>
<div class="recruit-board-list-wrapper">



</div>

<script>
window.addEventHandler('load',()=>{
	$.ajax({
		url:"<%=request.getContextPath()%>/recruit/board/",
	});
});

</script>






