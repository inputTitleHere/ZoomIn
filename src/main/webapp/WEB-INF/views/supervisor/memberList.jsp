<%@page import="com.kh.zoomin.recruit.member.RecruitMember"%>
<%@page import="com.kh.zoomin.applicant.member.model.dto.ApplicantMember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>

<%--
	List<ApplicantMember> applicantMemberList = (List<ApplicantMember>) request.getAttribute("applicantMemebrList");
	List<RecruitMember> recruitMemberList = (List<RecruitMember>) request.getAttribute("recruitMemebrList");
	System.out.println(applicantMemberList);
	String type = request.getParameter("searchType");
	String kw = request.getParameter("searchKeyword");	
--%>
<%--  
<style>
div.search-applicant{
	display: <%= type == null || "applicant".contains(type) ? "inline-block" : "none" %>;
}
div.search-recruit{
	display: <%= type == null || "recruit".contains(type) ? "inline-block" : "none" %>;
}
</style>
 --%>
	<section id="memberList-container">
		<%-- 회원검색 --%>
		<div id="search-container">
	        <select id="searchMemberType">
	        	<option value="none">회원타입</option>
	            <option value="aplicantMember">구직자</option>
	            <option value="recruitMember">구인자</option>
	        </select>
			
	        <!--구직자검색 : 아이디, 이름-->
	        <div id="search-applicantId" class="search-applicant">
	            <form action="<%= request.getContextPath()%>/supervisor/memberFinder" method="post">
	                <input type="hidden" name="searchType" value="applicant-id">
	                <input type="text" name="searchKeyword" size="30" placeholder="아이디를 입력하세요."value="">
	                <button type="submit">검색</button>
	            </form>
	        </div>	
	        <div id="search-applicantName" class="search-applicant">
	            <form action="<%= request.getContextPath()%>/supervisor/memberFinder" method="post">
	                <input type="hidden" name="searchType" value="applicant_name">
	                <input type="text" name="searchKeyword" size="30" placeholder="이름을 입력하세요."value="">
	                <button type="submit">검색</button>
	            </form>
	        </div>
	        	
	        <!--구인자검색 : 회사명, 회사번호-->
	        <div id="search-recruitName" class="search-recruit">
	            <form action="<%= request.getContextPath()%>/supervisor/memberFinder" method="post">
	                <input type="hidden" name="searchType" value="recruit-name">
	                <input type="text" name="searchKeyword" size="30" placeholder="회사명을 입력하세요."value="">
	                <button type="submit">검색</button>
	            </form>
	        </div>	
	        <div id="search-recruitNo" class="search-recruit">
	            <form action="<%= request.getContextPath()%>/supervisor/memberFinder" method="post">
	                <input type="hidden" name="searchType" value="recruit-no">
	                <input type="text" name="searchKeyword" size="30" placeholder="회사번호를 입력하세요."value="">
	                <button type="submit">검색</button>
	            </form>
	        </div>
    	</div>
	
	
		<section id="recruitMember-container">
		<h2>구인자 회원관리</h2>
			<table id="tbl-recruitMember">
				<thead>
					<tr>
						<th>UID</th>
						<th>회사번호</th>
						<th>회사명</th>
						<th>ID</th>
						<th>PASSWORD</th>
						<th>E-MAIL</th>
						<th>가입일</th> <%-- 날짜형식 yyyy-MM-dd --%>
					</tr>
				</thead>
				<tbody>
					<%--
						if(recruitMemberList == null || recruitMemberList.isEmpty()) {
					--%>
						<tr>
							<td colspan="7" align="center">조회 결과가 없습니다.</td>
						</tr>
					<%--
						}
						else {
							for(RecruitMember rm : recruitMemberList){
					--%>
						<tr>
		
						</tr>					
					<%--
							}
						}
					--%>
				</tbody>
			</table>
		</section>
		
		<section id="applicantMember-container">
		<h2>구직자 회원관리</h2>
			<table id="tbl-applicantMember">
				<thead>
					<tr>
						<td>UID</td>
						<td>이름</td>
						<td>ID</td>
						<td>PASSWORD</td>
						<td>전화번호</td>
						<td>이메일</td>
						<td>가입일</td>
					</tr>
				</thead>
				<tbody>
				<%--
					if(applicantMemberList == null || applicantMemberList.isEmpty()) {
				--%>
					<tr>
						<td colspan="7" align="center">조회 결과가 없습니다.</td>
					</tr>
				<%--
					}
					else {
						for(ApplicantMember am : applicantMemberList){
				--%>
					<tr>

					</tr>
				<%--
						}
					}
				--%>
				</tbody>
			</table>
		<style>
        #tbl-applicantMember, #tbl-recruitMember{
            border: 1px solid black;
            border-collapse: collapse;
            margin: 5px 5px;
        }
        tr, td, th{
            border: 1px solid black;
            padding: 10px;
        }
    </style>
		
		
		</section>
		
	</section>
</body>
</html>