<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
	
		<!-- 관리자 랜딩페이지 -->
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(new Date());

	//오늘 업데이트 된 게시글 수 = todayComcnt + todaySalCnt
	int todayComCnt = session.getAttribute("todayComCnt") == null ? 0 : (int) session.getAttribute("todayComCnt");
	int todaySalCnt = session.getAttribute("todaySalCnt") == null ? 0 : (int) session.getAttribute("todaySalCnt");
%>	
	
<section id="chart-container">
    <div id="chart" >
    	<h1 id="chart-h1">최근 일주일간 방문자 & 게시글 수</h1>
        <canvas id="myChart">
	        <!--차트가 그려질 부분-->
        </canvas>
    	<button id="btn-sch-statistic">기간별 통계보기</button>
    </div>
</section>
	<script>
	<!-- data 불러오기 : 오늘포함 최근 1주일 date, date별 new방문자수, date별 new게시글 수  -->
	const findData = () => {
		$.ajax({
			url : '<%= request.getContextPath() %>/supervisor/findData',
			dataType:'json',
			success(response){
				//console.log(response);	{boardList: Array(4), visitList: Array(5)}
				const {visitList, boardList} = response;
				//console.log(visitList);	[{…}, {…}, {…}, {…}, {…}]
				let days = [];	//가로축
				let visitCnts = []; 	//세로축
				let boardCnts = [];
				
				visitList.forEach((data) => {
					//console.log(data);	//{day: '7월 14, 2022', cnt: 1}
					const {day, cnt} = data;					
					days.push(day.substr(3,2));		//일별로 자름
					visitCnts.push(cnt);		//방문자 수 
					})
				boardList.forEach((data) => {
					const {cnt} = data;
					boardCnts.push(cnt);
				})
				
					
			const context = document.getElementById('myChart').getContext('2d');
				
				const myChart = new Chart(context, {      	
		            type: 'bar', // 차트의 형태
		            data: { // 차트에 들어갈 데이터
		                labels: days,
		                datasets: [
		                    { //데이터
		                        label: '일일 방문자 수', //차트 제목
		                        fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
		                        data: visitCnts,
		                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
		                        borderColor: 'rgba(255, 99, 132, 1)',
		                        borderWidth: 2 //경계선 굵기
		                    } ,
		                    {
		                        label: '일일 게시글 수 ',
		                        fill: false,
		                        data: boardCnts,
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
			
			
			},
			error : console.log
		});		
	};
	

    window.onload = () => {
    	const todayVisitCnt = document.querySelector("#tbl-visit-statistic tr:nth-child(2) td");
    	todayVisitCnt.innerHTML = "<%= session.getAttribute("todayCount") == null ? "-" : session.getAttribute("todayCount") %>명";
    	
    	const totalVisitCnt = document.querySelector("#tbl-visit-statistic tr:nth-child(3) td");
    	totalVisitCnt.innerHTML = "<%= session.getAttribute("totalCount") == null? "-" : session.getAttribute("totalCount") %>명";
    	
    	const todayBoardCnt = document.querySelector("#tbl-board-statistic tr:nth-child(2) td");
    	todayBoardCnt.innerHTML = "<%= todayComCnt + todaySalCnt == 0 ? " - " : todayComCnt + todaySalCnt %>개";

    	const totalBoardCnt = document.querySelector("#tbl-board-statistic tr:nth-child(3) td");
    	totalBoardCnt.innerHTML = "<%= session.getAttribute("totalBoardCnt") == null ? " - " : session.getAttribute("totalBoardCnt") %>개";
    }
    </script>
	

	<script>
	window.onload = () => {
		findData();
	}
	document.querySelector("#btn-sch-statistic").addEventListener('click', (e) => {
		location.href="<%= request.getContextPath()%>/supervisor/Statistic";
	})
	</script>

</body>
</html>