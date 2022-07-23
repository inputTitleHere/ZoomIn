<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="com.kh.zoomin.recruit.board.dto.RecruitBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<%
Member rbvLm = (Member) session.getAttribute("loginMember");
ApplicantMember rbvAm=null;
RecruitMember rbvRm=null;

if(rbvLm instanceof ApplicantMember){
	rbvAm=(ApplicantMember)rbvLm;
}else if(rbvLm instanceof RecruitMember){
	rbvRm=(RecruitMember)rbvLm;

}


if(rbvLm!=null&&rbvLm.getMemberType()==1){
%>
<%@ include file="/WEB-INF/views/recruit/recruitNavbar.jsp" %>
<%
}else{
%>
<%-- <%@ include file="/WEB-INF/views/applicant/applicantNav.jsp" %> --%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%} %>


<%
RecruitBoard rb = (RecruitBoard) request.getAttribute("recruitBoard");
boolean isFaved=(request.getAttribute("isFaved")==null?false:(boolean)request.getAttribute("isFaved"));
boolean isEnrolled=(request.getAttribute("isEnrolled")==null?false:(boolean)request.getAttribute("isEnrolled"));
//System.out.println("@recruitBoardView.jsp : isFaved="+isFaved+", isEnrolled="+isEnrolled);
%>
<link href="<%=request.getContextPath() %>/css/recruit/board/recruit-board-view.css" rel="stylesheet" type="text/css">
<section id="recruit-board-view">
	<%-- title 및 content에 대한 escapeXML처리함. --%>
	<%
	//관리자 일때 수정, 삭제버튼 보이도록 했습니다(이윤정)
	if(rbvLm!=null&&rbvLm.getMemberType()==1){
	if((rbvRm!=null && rb.getUid()==rbvRm.getUid()) || (rbvRm!=null && rbvRm.isSupervisor())){
	%>
	<div class="edit-button-wrapper">
		<button type="button" class="edit-button" id="update-button" onclick="updateBoard()">수정</button>
		<button type="button" class="edit-button" id="delete-button" onclick="deleteBoard()">삭제</button>
	</div>
	<%
	}}
	%>
	<h1><%=rb.getTitle()%></h1>
	<div class="small-info-wrapper">
		<div class="small-info">
			<table>
				<tbody>
					<tr>
						<td class="small-info-title">경력사항</td>
						<td class="small-info-data">: <%=rb.getCareerYears()%></td>
					</tr>
					<tr>
						<td class="small-info-title">학력사항</td>
						<td class="small-info-data">: <%=rb.getSchoolStatus()%></td>
					</tr>
					<tr>
						<td class="small-info-title">근무형태</td>
						<td class="small-info-data">: <%=rb.getWorkType()%></td>
					</tr>
					<tr>
						<td class="small-info-title">근무지역</td>
						<td class="small-info-data">: <%=rb.getOfficeLocation()%></td>
					</tr>
					<tr>
						<td class="small-info-title">연봉</td>
						<td class="small-info-data">: <%=rb.getSalary()%></td>
					</tr>
				</tbody>
			</table>
			<%if(rbvLm != null &&rbvLm.getMemberType()==2){ %>
			<div class="button-wrapper">
				<%-- placeholde용 버튼들 --%>
				<div id="fav-button-wrapper">
					<form action="" class="fav-frm">
						<input type="hidden" value="<%=((ApplicantMember)rbvLm).getUid() %>" name="uid" id="uid" />
						<input type="hidden" value="<%=rb.getNo() %>" name="boardNo" id="boardNo" />
						<input type="hidden" value="<%=isFaved%>" name="isFaved" id="isFaved" style="display:none;"/>
						<button class="register-buttons <%=isFaved?"active":""%>" id="fav-button">찜하기</button>
					</form>
				</div>
				<div id="enroll-button-wrapper">
					<form action="" class="enroll-frm">		
						<input type="hidden" value="<%=((ApplicantMember)rbvLm).getUid() %>" name="uid" id="uid" />
						<input type="hidden" value="<%=rb.getNo() %>" name="boardNo" id="boardNo" />
						<input type="hidden" value="<%= isEnrolled%>" name="isEnrolled" id="isEnrolled"/>
						<button class="register-buttons <%=isEnrolled?"active":""%>" id="enroll-button">지원하기</button>
					</form>
				</div>
			</div>
			<%}else if(rbvRm!=null && rb.getUid()==rbvRm.getUid()){ %>
			<div class="view-enroll-wrapper">
				<form action="<%=request.getContextPath()%>/recruit/board/viewEnrolled" method="post">
					<input type="hidden" value="<%=rb.getNo()%>" name="boardNo" id="view-enroll-boardNo" />
					<input type="hidden" value="<%=rb.getTitle() %>" name="title" id="view-enroll-title"/>
					<button class="view-enroll-button">지원자 조회하기</button>
				</form>
			</div>
			<%} %>
		</div><%-- small-info --%>
	</div>
	<%-- 메인 컨텐트 영역 --%>
	<div class="content-wrapper">
		<%=rb.getContent()%>
	</div>

</section>


<form action="<%=request.getContextPath()%>/recruit/board/updateRecruitBoard"
	name="boardUpdateFrm" method="post">
	<input type="hidden" name="no" value="<%=rb.getNo()%>" />
</form>

<script>
const deleteBoard=()=>{
	confirm("채용글을 정말 삭제하시겠습니까? 이 행동은 취소될 수 없습니다.");
	document.boardDelFrm.submit();
};

const updateBoard=()=>{
	location.href="<%=request.getContextPath()%>/recruit/board/updateRecruitBoard?No=<%=rb.getNo()%>";
}

window.addEventListener('load',()=>{
	const favFrms=document.querySelectorAll(".fav-frm");
  const enrollFrms=document.querySelectorAll(".enroll-frm");
	
	favFrms.forEach((item)=>{
		item.addEventListener('submit',(e)=>{
      e.preventDefault();
			favourite(e);
		})		
	})
	enrollFrms.forEach((item)=>{
		item.addEventListener('submit',(e)=>{
			e.preventDefault();
			enroll(e);
		})
	})
	
})



const favourite=(e)=>{
	//console.log(e.target.boardNo.value);
	//console.log(e.target.uid.value);
	//console.log(e.target.isFaved.value);
	// ajax처리할것.
	$.ajax({
			url:'<%=request.getContextPath()%>/recruit/board/addFavourite',
			data:{
				"boardNo":e.target.boardNo.value,
				"uid":e.target.uid.value,
				"isFaved":e.target.isFaved.value
			},
			method:'post',
      success(response){
        //console.log("response:"+response);
        const button   = document.querySelector("#fav-button");
        const favValue = document.querySelector("#isFaved");
        if(response==='true'){
        	//console.log("attempt to add Active");
        	button.classList.add("active");
        	favValue.value=response;
        }else{
        	//console.log("attempt to remove Active");
        	button.classList.remove("active");
        	favValue.value=response;
        }
      },
			error:console.log
	});
};
const enroll=(e)=>{
  // e으로 form이 들어옴
  console.log(e.target.boardNo.value);
	console.log(e.target.uid.value);
	// ajax처리
	$.ajax({
		url:'<%=request.getContextPath()%>/recruit/board/addEnroll',
		data:{
			"boardNo":e.target.boardNo.value,
			"uid":e.target.uid.value,
			"isEnrolled":e.target.isEnrolled.value
		},
		method:'post',
  success(response){
    console.log("response:"+response);
    const button   = document.querySelector("#enroll-button");
    const enroll = document.querySelector("#isEnrolled");
    if(response==='true'){
    	console.log("attempt to add Active");
    	button.classList.add("active");
    	enroll.value=response;
    }else{
    	console.log("attempt to remove Active");
    	button.classList.remove("active");
    	enroll.value=response;
    }
  },
		error:console.log
	});
};


</script>

<br />
<br />
<br />

</body>
</html>