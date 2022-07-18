<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 게시판 관리</title>
<style>
    form{
        display: flex;
        margin: 0 10px;
    }
    div{
        margin: 0 20px;
    }
</style>
</head>
<body>
	<h1>관리자 게시판 관리</h1>
    
    <section id="search-container">
        <form action="<%= request.getContextPath()%>/supervisor/boardFinder" method="post">

            <div id="select-board">
                <select id="board-type">
                    <option value="none">== 게시판선택 ==</option>
                    <option value="salaryBoard">연봉</option>
                    <option value="companyBoard">채용</option>
                    <option value="reviewBoard">리뷰</option>
                </select>
            </div>
            <div id="select-keyword">
                <select id="board-keyword">
                    <option value="none">== 선택 ==</option>
                    <option value="boardWriter">작성자</option>
                    <option value="boardContent">내용</option>
                    <option value="boardTitle">제목</option>
                </select>
            </div>
            <div id="board-search-keyword">
                <input type="text" name="searchKeyword" size="30" placeholder="검색어를 입력하세요."value="">
                <button type="submit">검색</button>
            </div>
        </form>
    </section>
</body>
</html>