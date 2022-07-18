package com.kh.zoomin.recruit.board.dao;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.dto.RecruitBoardReadMode;
import com.kh.zoomin.recruit.board.exception.RecruitBoardException;


public class RecruitBoardDao {
	private Properties prop = new Properties();
	
	public RecruitBoardDao() {
		String filename=RecruitBoardDao.class.getResource("/sql/zoomin/recruit/board/recruit-board-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		}catch(IOException e) {
			throw new RecruitBoardException("RecruitBoard properties failed to load",e);
		}
	}
	
	/**
	 * RECRUIT_BOARD에 대한 쿼리문 처리를 진행하는 메소드입니다. 
	 * @param rset
	 * @return
	 * @throws SQLException 
	 */
	private RecruitBoard handleRecruitBoard(ResultSet rset) throws SQLException {
		int no = rset.getInt("no");
		int uid = rset.getInt("uid");
		int categoryNumber = rset.getInt("category_number");
		String companyNo=rset.getString("company_no");
		String title=rset.getString("title");
		String careerYears=rset.getString("career_years_req");
		String schoolStatus=rset.getString("school_status");
		String workType=rset.getString("work_type");
		String officeLocation=rset.getString("office_location");
		String salary=rset.getString("salary");
		String content=rset.getString("content");
		Date closure_date=rset.getDate("closure_date");
		Date reg_date = rset.getDate("reg_date");
		
		return new RecruitBoard(no, uid, categoryNumber, companyNo, title, careerYears, schoolStatus, workType, officeLocation, salary, content, closure_date, reg_date);
	}
	
	/**
	 * 파라미터로 전달된 값 만큼 데이터베이스에서 읽어옵니다. 
	 * @param param
	 * @param conn
	 * @return
	 */
	public List<RecruitBoard> loadRecruitBoardHeaders(Map<String, Object> param, Connection conn) {
		PreparedStatement pstmt =null;
		ResultSet rset=null;
		//loadRecruitBoard=select * from (select row_number() over(order by ? ?) rnum, r.* from recruit_board r) b where rnum between ? and ?
		// [1번 2번] ? 에는 closure_date asc(마감임박) 또는 uid desc이 들어갈것(최신순) -> param에서 꺼낸다.
		String sql=null;
		RecruitBoardReadMode mode=(RecruitBoardReadMode)param.get("mode");
		if(mode==RecruitBoardReadMode.NEAR_CLOSURE) {
			sql=prop.getProperty("loadRecruitBoardClosureDate");
		}else if(mode==RecruitBoardReadMode.MOST_RECENT){
			sql=prop.getProperty("loadRecruitBoardNewest");
		}else {
			throw new RecruitBoardException("채용게시판 조회 오류 : 이상한 enum값"); // 아마 문제 없을것
		}
		
		List<RecruitBoard> result=new ArrayList<RecruitBoard>();
		
		try {			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (Integer)param.get("start"));
			pstmt.setInt(2, (Integer)param.get("end"));
			rset=pstmt.executeQuery();
			System.out.println("@RecruitBoardDao sql query = "+rset.getStatement().toString());
			// 쿼리 결과 처리
			while(rset.next()) {
				result.add(handleRecruitBoard(rset));
			}
		}catch(SQLException e) {
			throw new RecruitBoardException("채용게시판 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}


	

}