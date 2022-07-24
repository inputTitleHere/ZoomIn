<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.zoomin.common.ZoominUtils"%>
<%@page import="com.kh.zoomin.recruit.board.dto.RecruitBoard"%>
<%@page import="com.kh.zoomin.company.dto.Company"%>
<%@page
	import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview"%>
<%@page
	import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    


<%
Member rrlLm = (Member) session.getAttribute("loginMember");
if (rrlLm != null && rrlLm.getMemberType() == 1) {
%>
<%@ include file="/WEB-INF/views/recruit/recruitNavbar.jsp"%>
<%} else {%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%}%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/recruit/board/recruit-review-view.css" />
<%
List<CompanyReview> companyReviewList = (List<CompanyReview>) request.getAttribute("companyReview");
List<SalaryReview> salaryReviewList = (List<SalaryReview>) request.getAttribute("salaryReview");
Company company = (Company) request.getAttribute("company");
List<RecruitBoard> rbl = (List<RecruitBoard>) request.getAttribute("recruitBoardList");

String companyReviewPagebar = (String) request.getAttribute("companyReviewPagebarHTML");
String salaryReviewPagebar = (String) request.getAttribute("salaryReviewPagebarHTML");

Date currDate = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yy년 MM월 dd일");

%>

<div class="content-wrapper">
	<h1 style="text-align: center;">
		[<%=company.getCompanyName()%>]
	</h1>
	<section class="company-info">
		<div>
			<%=company.getCompanyNo()%>
		</div>
		<div>
			<%=company.getCompanyInfo()%>
		</div>
	</section>
	<div class="review-wrapper">
		<section class="company-review">
			<h2>회사 리뷰</h2>
			<%
			if (rrlLm != null && rrlLm.getMemberType() == 2) {
				ApplicantMember rrlAm=(ApplicantMember)rrlLm;
				int uid=rrlAm.getUid();
				String companyNo=company.getCompanyNo();
			%>
			<button class="write-review-button" id="write-company-review-button" onclick="location.href='<%=request.getContextPath()%>/review/company/companyReviewEnroll?uid=<%=rrlAm.getUid()%>&companyNo=<%=companyNo%>'">회사 리뷰 작성하기</button>
			<%}//close IF %>
			
			<%
			for (CompanyReview cr : companyReviewList) {
			%>
			<table>
				<tbody>
					<tr>
						<td>작성자 : <%=cr.getUid()%></td>
						<td>평점 : <%=cr.getStars()%></td>
					</tr>
				</tbody>
			</table>
			<%
			}
			%>
			<%=companyReviewPagebar%>
		</section>
		<section class="salary-review">
			<h2>연봉 리뷰</h2>
			<%
			if (rrlLm != null && rrlLm.getMemberType() == 2) {
				ApplicantMember rrlAm=(ApplicantMember)rrlLm;
				int uid=rrlAm.getUid();
				String companyNo=company.getCompanyNo();
			%>
			<button class="write-review-button" id="write-company-review-button" onclick="location.href='<%=request.getContextPath()%>/review/salary/salaryReviewEnroll?uid=<%=rrlAm.getUid()%>&companyNo=<%=companyNo%>'">연봉 리뷰 작성하기</button>
			<%}//close IF %>
			
			<%
			for (SalaryReview sr : salaryReviewList) {
			%>
			<table>
				<tbody>
					<tr>
						<td>작성자 : <%=sr.getUid()%></td>
						<td>연봉 : <%=sr.getSalary()%></td>
					</tr>
				</tbody>
			</table>
			<%
			}
			%>
			<%=salaryReviewPagebar%>
		</section>
	</div>
	<div class="recruit-board-wrapper">
	<h2>이 회사의 채용</h2>
		<section id="recruit-board" class="recruit-board-section">
			<%
			if (rbl != null) {
				for (RecruitBoard recruitBoard : rbl) {
					// 마감일까지 남은 시간을 계산.
					String closureDate = sdf.format(recruitBoard.getClosureDate());
					int daysToClosuer = (int) Math
					.ceil(((double) recruitBoard.getClosureDate().getTime() - currDate.getTime()) / 1000 / 60 / 60 / 24);
					//System.out.println(daysToClosuer);
			%>
			<%-- 여기에 채용게시글의 리스트를 전개한다. --%>
			<table class="recruit-board-item">
				<tr>
					<td rowspan="2" class="board-company-logo">
						<%-- 여기에 원래 기업 아이콘을 삽입하도록 한다. 지금은 기업번호로 대체한다.--%> <%=recruitBoard.getCompanyNo()%>
					</td>
					<td colspan="3" class="board-title">
						<%-- 여기에는 Title을 넣는다. --%> <%-- 해당 게시글로 이동하는 링크도 만든다. --%> <a
						href="<%=request.getContextPath()%>/recruit/board/viewRecruitBoard?boardNo=<%=recruitBoard.getNo()%>"
						target="_blank"> <%=ZoominUtils.escapeXml(recruitBoard.getTitle())%>
					</a>
					</td>
					<td rowspan="1" class="board-remaining-days">
						<%-- 마감까지 남은 시간을 입력 --%> D-<%=daysToClosuer%>일 <br /> <span
						class="closure-date">마감일 : <%=closureDate%></span>
					</td>
				</tr>
				<tr class="small-info-text">
					<td>
						<%-- 경력사항 및 학력사항 --%> 경력사항 : <%=recruitBoard.getCareerYears()%> <br>
						학력사항 : <%=recruitBoard.getSchoolStatus()%>
					</td>
					<td>
						<%-- 근무형태 및 근무지역 --%> 근무형태 : <%=recruitBoard.getWorkType()%> <br />
						근무지역 : <%=recruitBoard.getOfficeLocation()%>
					</td>
					<td>
						<%-- 연봉정도 --%> 연봉 : <%=recruitBoard.getSalary()%>
					</td>
				</tr>

			</table>
			<%
			}
			}
			%>
		</section>
	</div>
</div>

</body>
</html>