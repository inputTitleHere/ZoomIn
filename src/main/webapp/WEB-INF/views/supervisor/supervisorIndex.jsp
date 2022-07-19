<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/supervisorLoginHeader.jsp" %>

	<!-- data 불러오기 : 오늘포함 최근 1주일 date, date별 new방문자수, date별 new게시글 수  -->




	<!-- 관리자 메인페이지 -->

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