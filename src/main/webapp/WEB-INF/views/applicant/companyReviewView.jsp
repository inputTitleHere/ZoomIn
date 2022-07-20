<%@page import="java.util.List"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%> 
<%
	CompanyReview cr = (CompanyReview) request.getAttribute("companyReview");
	List<CompanyReview> list = (List<CompanyReview>) request.getAttribute("list");
	List<CompanyReview> board = (List<CompanyReview>) request.getAttribute("board");
	CompanyReviewExt companyReviewExt = (CompanyReviewExt) request.getAttribute("ext");
%>
<section id="company-review-view-container">
	<h1>리뷰 상세보기</h1>
	<!-- companyReviewViewServlet에 int no= 9; 지정후, 웹에서 9번 글 찾아서 들어옴 -->
	<h2>시발 드디어 들어왔따!!!</h2>
	<div class="edit-button">
		<button id="update-button" onclick="updateBoard()">수정</button>
		<button id="update-button" onclick="deleteBoard()">삭제</button>
	</div>
	<table class="company-review-list">
		<tr>
			<th>uid</th>
			<!-- <th>회사명</th> -->
			<th>분야</th>
			<th>별점</th>
			<th>워라벨</th>
			<th>승진 가능성</th>
			<th>업무 강도</th>
			<th>회사 잠재력</th>
			<th>연봉 만족도</th>
			<th>내용</th>
			<th>등록 날짜</th>
		</tr>	
<%
	if(list != null && !list.isEmpty()){
		for(CompanyReview companyReview : list){
%>
		<tr>
			<%-- <td>
				<% if(companyReview.getCompanyNo() == cr.getCompanyNo()) %>
					
					<%= cr.getCompanyName() %>
			</td> --%>
			<td>
				<%= cr.getUid() %>
			</td>
			<td>
				<% if(cr.getCategoryNumber()==1) %>IT/웹/통신
				<% if(cr.getCategoryNumber()==2) %>미디어/디자인
				<% if(cr.getCategoryNumber()==3) %>의료/제약/복지	
				<% if(cr.getCategoryNumber()==4) %>건설업
				<% if(cr.getCategoryNumber()==5) %>서비스업
				<% if(cr.getCategoryNumber()==6) %>은행/금융업
				<% if(cr.getCategoryNumber()==7) %>유통/무역/운송
				<% if(cr.getCategoryNumber()==8) %>제조/화학
				<% if(cr.getCategoryNumber()==9) %>기관/협회
				<% if(cr.getCategoryNumber()==110) %>교육업
			</td>
			<td><%= cr.getStars() %></td>
			<td><%= cr.getWorkLifeBalance() %></td>
			<td><%= cr.getLevelUp() %></td>
			<td><%= cr.getWorkIntensity() %></td>			
			<td><%= cr.getPotential() %></td>
			<td><%= cr.getSalarySatisfaction() %></td>
			<td><%= cr.getContent() %></td>
			<td><%= cr.getRegDate() %></td>
		</tr>
<%  
		}
	} else {
%>
		<tr>
			<td colspan="6">조회된 게시글이 없습니다.</td>
		</tr>
<%  } %>
</table>
</section>

<script>
const deleteBoard = () => {
	confirm("삭제하시겠습니까?");
	document.boardDelFrm.submit();
};

const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/review/company/companyReviewUpdate?no=<%= cr.getNo() %>"
};
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>