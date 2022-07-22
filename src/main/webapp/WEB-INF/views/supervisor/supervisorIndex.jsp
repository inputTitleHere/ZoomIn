<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>
	
		<!-- 관리자 메인페이지 -->
	<button id="btn-findData">데이터 불러오기</button>
	<script>
	//시험용 버튼
	document.querySelector("#btn-findData").addEventListener('click', (e) => {
		//location.href="<%= request.getContextPath() %>/supervisor/findData";
		findData();
	});
	</script>
    <div style="width: 900px; height: 900px;">
        <!--차트가 그려질 부분-->
        <canvas id="myChart"></canvas>
    </div>

	<script>
	<!-- data 불러오기 : 오늘포함 최근 1주일 date, date별 new방문자수, date별 new게시글 수  -->
	const findData = () => {
		$.ajax({
			url : '<%= request.getContextPath() %>/supervisor/findData',
			dataType:'json',
			success(response){
				console.log(response);
				const {visitList, boardList} = response;
				console.log(visitList);
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
	
	</script>


</body>
</html>