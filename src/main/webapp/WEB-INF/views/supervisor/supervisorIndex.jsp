<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>

<%
	int todayComCnt = session.getAttribute("todayComCnt") == null ? 0 : (int) session.getAttribute("todayComCnt");
	int todaySalCnt = session.getAttribute("todaySalCnt") == null ? 0 : (int) session.getAttribute("todaySalCnt");
%>
    <h1>통계관리</h1>

    <div id="btn-manage">
        <input type="button" value="회원관리" onclick="location.href='<%= request.getContextPath()%>/supervisor/memberList';">
        <input type="button" value="게시판관리" onclick="location.href='<%= request.getContextPath()%>/supervisor/BoardList';">
    </div>
        
    <div class="date-container">
	    <div id="statistic-date-start">
	        <input type="date" name="dateStart">
	    </div>
	    <span> ~ </span> 
    	<div id="statistic-date-end">
	        <input type="date" name="dateEnd">
	    </div>
    </div>
    
    
    <div id="btn-statistic">
        <input type="button" value="방문자 수 조회" onclick="location.href='<%= request.getContextPath()%>/supervisor/visitMember';">
        <input type="button" value="게시글 수 조회" onclick="location.href='<%=request.getContextPath()%>/supervisor/countBoard';">
    </div>

    <table id="tbl-visit-statistic">
        <tr>
        	<th>날짜별 조회</th>
        	<td>명</td>
        </tr>
        <tr>
	         <th>오늘 방문자 수 </th>
	         <td><%= session.getAttribute("todayCount") == null ? "-" : session.getAttribute("todayCount") %>명</td>
        </tr>
		<tr>
           <th>총 방문자 수</th>
           <td><%= session.getAttribute("totalCount") == null? "-" : session.getAttribute("totalCount") %>명</td>
		</tr>            
    </table>
    
    <table id="tbl-board-statistic">
    	<tr>
    		<th>오늘 게시글 수</th>
    		<td><%= todayComCnt + todaySalCnt %>개</td>
    	</tr>
    	<tr>
    		<th>총 게시글 수</th>
    		<td><%= session.getAttribute("totalBoardCnt") %>개</td>
    	</tr>
    </table>
    
    
    <%-- 임시 테이블 스타일 --%>
    <style>
        #tbl-visit-statistic, #tbl-board-statistic{
            border: 1px solid black;
            border-collapse: collapse;
            margin: 20px;
        }
        tr, td, th{
            border: 1px solid black;
            padding: 30px;
        }
        .date-container{
        	display: flex;
        }
    </style>
    
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    
    <div style="width: 900px; height: 900px;">
        <!--차트가 그려질 부분-->
        <canvas id="myChart"></canvas>
    </div>

    <script type="text/javascript">
        const context = document
            .getElementById('myChart')
            .getContext('2d');
        const myChart = new Chart(context, {
            type: 'bar', // 차트의 형태
            data: { // 차트에 들어갈 데이터
                labels: [
                    //x 축
                    '12','13','14','15','16','17','18'
                ],
                datasets: [
                    { //데이터
                        label: '일일 방문자 수', //차트 제목
                        fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                        data: [
                            21,19,25,20,23,26,25 //x축 label에 대응되는 데이터 값
                        ],
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 2 //경계선 굵기
                    } ,
                    {
                        label: '일일 게시글 수 ',
                        fill: false,
                        data: [
                            8, 10, 12, 13, 5, 3, 2 
                        ],
                        backgroundColor: 'rgb(51, 102, 255, 0.2)',
                        borderColor: 'rgb(51, 102, 255, 1)',
                        borderWidth: 2
                    } 
                ]
            },
            options: {
                scales: {
                    yAxes:  [
                        {
                            ticks: {
                                beginAtZero: true
                            }
                        }
                    ]
                }
            }
        });
    </script>
    
    
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
</body>
</html>