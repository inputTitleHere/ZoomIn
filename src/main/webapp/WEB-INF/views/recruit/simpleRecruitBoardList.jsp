<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
RecruitMember rmtest = (RecruitMember)session.getAttribute("loginMember");
%>
<div class="recruit-board-list-wrapper">
<h1>등록한 채용게시글</h1>
<table class="my-recruit-board">
  <thead>
    <tr>
      <td>제목</td>
      <td>등록일</td>
      <td>마감일</td>
      <td>지원인원(TODO)</td>
    </tr>
  </thead>
	<tbody>
		<%-- 여기에 ajax 삽입. --%>
	</tbody>
</table>

</div>




<script>
window.addEventListener('load',()=>{
	indexRecruiterBoardList();
});

const indexRecruiterBoardList=()=>{
	$.ajax({
		url:"<%=request.getContextPath()%>/recruit/board/myRecruitBoardList",
		method:"post",
		data:{
			uid:'<%=rmtest.getUid()%>'
		},
		success(response){
			console.log(response);
			const tbody=document.querySelector(".my-recruit-board tbody");
			response.forEach((item)=>{
				const {no, uid, categoryNumber, companyNo, title, careerYears,schoolStatus, workType, officeLocation, salary, content,closureDate,regDate}=item;
				const newTr = document.createElement("tr");

        const titleTd = document.createElement('td');
        const link = document.createElement('a');
        link.href='<%=request.getContextPath()%>/recruit/board/viewRecruitBoard?boardNo='+no;
        link.append(title);
        titleTd.append(link);

        const regDateTd=document.createElement('td');
        regDateTd.append(regDate);

        const closureDateTd = document.createElement('td');
        closureDateTd.append(closureDate);

        const enrollCountTd=document.createElement('td');
        enrollCountTd.append("=TODO=");

        newTr.append(titleTd,regDateTd,closureDateTd,enrollCountTd);
				tbody.append(newTr);
			})
		},
		error:console.log
	});
};


</script>






