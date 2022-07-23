<%@page import="com.kh.zoomin.company.dto.Company"%>
<%@page import="com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview"%>
<%@page import="com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
Member rrlLm=(Member)session.getAttribute("loginMember");
if(rrlLm!=null&&rrlLm.getMemberType()==1){
%>
<%@ include file="/WEB-INF/views/recruit/recruitNavbar.jsp" %>
<%}else{ %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%} %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/recruit/board/recruit-review-view.css" />
<%
List<CompanyReview> companyReviewList=(List<CompanyReview>)request.getAttribute("companyReview");
List<SalaryReview> salaryReviewList=(List<SalaryReview>)request.getAttribute("salaryReview");
Company company = (Company)request.getAttribute("company");

String companyReviewPagebar=(String)request.getAttribute("companyReviewPagebarHTML");
String salaryReviewPagebar=(String)request.getAttribute("salaryReviewPagebarHTML");

%>

<div class="content-wrapper">
<h1 style="text-align:center;">[<%=company.getCompanyName() %>]</h1>
<section class="company-info">
<br>
<div id="comNo">
	사업자 번호 :
	<%=company.getCompanyNo() %>
</div>
<br>
<div id="comInfo">
	<%=company.getCompanyInfo() %>
</div>
</section>
<section class="company-review">
<h2>회사 리뷰</h2>
<%for(CompanyReview cr : companyReviewList){ %>
<table>
	<tbody>
		<tr>
			<td>작성자 : <%=cr.getUid() %></td>
			<td>평점 : <%=cr.getStars() %></td>
		</tr>
	</tbody>
</table>
<%} %>
<%=companyReviewPagebar %>
</section>
<section class="salary-review">
<h2>연봉 리뷰</h2>
<%for(SalaryReview sr : salaryReviewList){ %>
<table>
	<tbody>
		<tr>
			<td>작성자 : <%=sr.getUid() %></td>
			<td>연봉 : <%=sr.getSalary() %></td>
		</tr>
	</tbody>
</table>
<%} %>
<%=salaryReviewPagebar %>
</section>
</div>



</body>
</html>