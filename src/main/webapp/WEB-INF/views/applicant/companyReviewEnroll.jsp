<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/views/common/applicantLoginHeader.jsp" %> --%>
<%
	ApplicantMember applicantMember = (ApplicantMember) request.getAttribute("applicantMember");
	CompanyReview companyReview = (CompanyReview) request.getAttribute("companyReview");
	CompanyReviewExt companyReviewExt = (CompanyReviewExt) request.getAttribute("companyReviewExt"); 
%>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/common.css" />
	<div id="account">
		<a href ="" id="writeResume">이력서 작성</a>
		<a href ="" id="info">회원정보</a>
		<a href ="" id="logOut">로그아웃</a>
	</div>
	<div id="logodiv">
		<img id="logo" alt="" src="<%= request.getContextPath() %>/images/zoominlogo.jpg">
	</div>
	<div id="searchBoxdiv">
		<input id="searchBox" type="text">
		 <button class="custom-btn btn-3"><span>검색</span></button>
	</div>
<section id="company-review-view-container">
	<h1>회사 리뷰 등록</h1>

	<form 
		name="companyReviewEnrollFrm"
		action="<%= request.getContextPath() %>/CompanyReviewEnrollServlet"
		method="post"
	>
		<table id="tbl-company-review">
			<tr>
				<th>작성자 번호</th>
				<td>
					1<%-- <input type="text" name="writer" value="<%= applicantMember.getUid() %>" readonly/> --%>
				</td>
			</tr>
			<tr>
				<th>회사명</th>
				<td>2</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					
					<textarea name="content" id="content" cols ="50" rows="10"/></textarea>
					<p>
		                <span id="counter"></span> / <span id="max-counter"></span>
		            </p>
				</td>
			</tr>
			<tr>
	            <th>평점</th>
	            <td><input type='radio' value='1' name='stars'/>1점</td>
	            <td><input type='radio' value='2' name='stars'/>2점</td>
	            <td><input type='radio' value='3' name='stars'/>3점</td>
	            <td><input type='radio' value='4' name='stars'/>4점</td>
	            <td><input type='radio' value='5' name='stars'/>5점</td>
	        </tr>
	        <tr>
	            <th>워라벨</th>
	            <td><input type='radio' value='1' name='wlb'/>1점</td>
	            <td><input type='radio' value='2' name='wlb'/>2점</td>
	            <td><input type='radio' value='3' name='wlb'/>3점</td>
	            <td><input type='radio' value='4' name='wlb'/>4점</td>
	            <td><input type='radio' value='5' name='wlb'/>5점</td>
	        </tr>
	        <tr>
	            <th>승진 가능성</th>
	            <td><input type='radio' value='1' name='levup'/>1점</td>
	            <td><input type='radio' value='2' name='levup'/>2점</td>
	            <td><input type='radio' value='3' name='levup'/>3점</td>
	            <td><input type='radio' value='4' name='levup'/>4점</td>
	            <td><input type='radio' value='5' name='levup'/>5점</td>
	        </tr>
	        <tr>
	            <th>업무 강도</th>
	            <td><input type='radio' value='1' name='work_inten'/>1점</td>
	            <td><input type='radio' value='2' name='work_inten'/>2점</td>
	            <td><input type='radio' value='3' name='work_inten'/>3점</td>
	            <td><input type='radio' value='4' name='work_inten'/>4점</td>
	            <td><input type='radio' value='5' name='work_inten'/>5점</td>
	        </tr>
	        <tr>
	            <th>발전 가능성</th>
	            <td><input type='radio' value='1' name='poten'/>1점</td>
	            <td><input type='radio' value='2' name='poten'/>2점</td>
	            <td><input type='radio' value='3' name='poten'/>3점</td>
	            <td><input type='radio' value='4' name='poten'/>4점</td>
	            <td><input type='radio' value='5' name='poten'/>5점</td>
	        </tr>
	        <tr>
	            <th>연봉 만족도</th>
	            <td><input type='radio' value='1' name="salary_satis"/>1점</td>
	            <td><input type='radio' value='2' name="salary_satis"/>2점</td>
	            <td><input type='radio' value='3' name="salary_satis"/>3점</td>
	            <td><input type='radio' value='4' name="salary_satis"/>4점</td>
	            <td><input type='radio' value='5' name="salary_satis"/>5점</td>
	        </tr>
		
		
		</table>
	</form>
	
	<div>
		<button>cancel</button>
		<button id="btnSubmit">submit</button>
	</div>
<style>
h1 {
	font-size: 100px;
}
</style>
<script type="text/javascript">
document.getElementById("btnSubmit").onclick = (e) =>{
	document.companyReviewEnrollFrm.submit();
};

const MAX_COUNTER = 30;
$(counter).html(0);
$("#max-counter").html(MAX_COUNTER);

$(content).keyup((e) => {
    const {target: {value}} = e;
    console.log(value);
    const len = value.length;
    const $counter = $(counter);
    $counter.html(len);

    const $submit = document.querySelector('#btnSubmit');
    if(len <= MAX_COUNTER){
        $counter.css('color', 'initial'); // 초기값 initial inherit  
        
    } 
    else {
        $counter.css('color', 'red');
        alert('너무 길어서 제출이 불가합니다.');
    }
})
</script>

</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>