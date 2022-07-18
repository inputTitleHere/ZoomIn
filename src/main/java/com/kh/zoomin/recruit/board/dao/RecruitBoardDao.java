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
		// [1번 2번] ? 에는 closure_date asc(마감임박) 또는 uid desc이 들어갈것(최신순) -> param에서 꺼낸다.
//		loadRecruitBoard=
//			select * from (select row_number() over(order by #) rnum, r.* rom recruit_board r where closure_date-sysdate>0) b where rnum between ? and ?
		String sql=prop.getProperty("loadRecruitBoard");
		RecruitBoardReadMode mode=(RecruitBoardReadMode)param.get("mode");
		if(mode==RecruitBoardReadMode.NEAR_CLOSURE) {
			sql=sql.replace("#", "closure_date asc");
		}else if(mode==RecruitBoardReadMode.MOST_RECENT){
			sql=sql.replace("#", "no desc");
		}else {
			sql=sql.replace("#", "closure_date asc"); // null이거나 이상한 값이면 그냥 기본 설정으로 진행
		}
		
		List<RecruitBoard> result=new ArrayList<RecruitBoard>();
		
		try {			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (Integer)param.get("start"));
			pstmt.setInt(2, (Integer)param.get("end"));
			rset=pstmt.executeQuery();
//			System.out.println("@RecruitBoardDao sql query = "+rset.getStatement().toString());
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

	public int totalRecruitBoardCount(Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		
		String sql=prop.getProperty("totalRecruitBoardCount");
		try {
			pstmt=conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			
			// 단일결과를 반환하므로 한번만 rset.next를 한다.
			rset.next();
			result = rset.getInt(1);
		}catch(SQLException e) {
			throw new RecruitBoardException("총 채용게시글수 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public RecruitBoard viewRecruitBoard(int boardNo, Connection conn) {
		RecruitBoard result=null;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		// viewRecruitBoard=select * from recruit_board where no=?
		String sql=prop.getProperty("viewRecruitBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				result=handleRecruitBoard(rset);
			}
			
		}catch(SQLException e) {
			throw new RecruitBoardException("단별 채용게시글 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public List<RecruitBoard> loadRecruiterBoard(Map<String, Object> param, Connection conn) {
		PreparedStatement pstmt =null;
		ResultSet rset=null;
		List<RecruitBoard> result=new ArrayList<RecruitBoard>();
		String sql=prop.getProperty("loadRecruiterBoard");
//		loadRecruiterBoard=select * from recruit_board where "uid"=? order by #
		
		// SQL 구문에 대한 상세 처리(#를 대체함)
		RecruitBoardReadMode mode=(RecruitBoardReadMode)param.get("recruiterMode"); // 20220717기준 무조건 null임. null타입도 casting가능함 그냥 null일뿐
		if(mode==RecruitBoardReadMode.NEAR_CLOSURE) {
			sql=sql.replace("#", "closure_date asc");
		}else if(mode==RecruitBoardReadMode.MOST_RECENT){
			sql=sql.replace("#", "no desc");
		}else {
			sql=sql.replace("#", "closure_date asc"); // null이거나 이상한 값이면 그냥 기본 설정으로 진행
		}
		
		try {			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (Integer)param.get("uid"));
			rset=pstmt.executeQuery();
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




