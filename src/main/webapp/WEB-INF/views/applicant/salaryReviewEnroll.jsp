<%@page import="com.kh.zoomin.recruit.member.model.dto.RecruitMember"%>
<%@page import="com.kh.zoomin.member.dto.Member"%>
<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@page import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %>
<%
String msg = (String) session.getAttribute("msg");
//System.out.println("msg@jsp = " + msg);
if(msg != null) session.removeAttribute("msg"); 
Member loginMember = (Member) session.getAttribute("loginMember");
System.out.println("loginMember = " + loginMember);
ApplicantMember am=null;
RecruitMember rm=null;
if(loginMember instanceof ApplicantMember){
	am=(ApplicantMember)loginMember;
}else if(loginMember instanceof RecruitMember){
	rm=(RecruitMember)loginMember;

}


Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie c : cookies){
		String name = c.getName();
		String value = c.getValue();
		//System.out.println("[cookie] " + name + " = " + value);
		
		}
}

Member apMember = (Member) session.getAttribute("loginMember");
ApplicantMember applicantMember = null;
if(apMember instanceof ApplicantMember){
	applicantMember = (ApplicantMember) apMember;
}
int uid = Integer.parseInt(request.getParameter("uid"));
String companyNo = request.getParameter("companyNo");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/applicant/salaryReviewEnroll.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<section id="salary-review-view-container">
	<h2>-연봉 리뷰 등록-</h2>
	
	<form 
		action="<%= request.getContextPath() %>/review/salary/salaryReviewEnroll" 
		name="salaryReviewEnrollFrm" 
		method="post"
	>
		<table id="tbl-salary-review">
			<tr>
				<th>작성자 번호</th>
				<td><%-- <%= applicantMember.getUid() %> --%>
					<input type="text" name="uid" value="<%= am.getUid() %>" readonly/>
				</td>
			</tr>
			<tr>
				<th>회사명</th>
				<td><%-- value="<%= companyNo %>"  --%>
					<input type="text" name="company_no" />
				</td>
			</tr>
			<tr>
				<th>분야</th>
				<td>
					<!-- <input type="text" name ="category_number"/> -->
					<select name="category_number" id="category_number">
						<option disabled selected value="">---카테고리 선택---</option>
						<option value="1">인사팀</option>
						<option value="2">회계/총무팀</option>
						<option value="3">마케팅팀</option>
						<option value="4">영업팀</option>
						<option value="5">생산/관리팀</option>
						<option value="6">연구개발팀</option>
						<option value="7">기술팀</option>
						<option value="8">서비스팀</option>
						<option value="9">인터넷팀</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>연차</th>
				<td>
					<input type="text" name="work_year"/>
				</td>
			</tr>
			<tr>
				<th>직급</th>
				<td>
					<select name="job_position" id="aaaa">
						<option value="1">사원</option>
						<option value="2">주임</option>
						<option value="3">대리</option>
						<option value="4">과장</option>
						<option value="5">차장</option>
						<option value="6">부장</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>연봉</th>
				<td>
					<input type="text" name="salary"/>만원
				</td>
			</tr>
		</table>
	</form>
	
	<div id="end">
		<button onclick="location.href='<%= request.getContextPath() %>'">취소</button>
<%-- 		<button onclick="location.href='<%= request.getContextPath() %>/review/salary/salaryReviewList'">취소</button> --%>
		<button id="btnSubmit">리뷰 등록하기</button>
	</div>
</section>
<script>
document.querySelector("#btnSubmit").onclick = () =>{
	document.salaryReviewEnrollFrm.submit();
};

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>