package com.kh.zoomin.supervisor.model.dao;
import static com.kh.zoomin.common.JdbcTemplate.close;

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

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.recruit.member.RecruitMember;
import com.kh.zoomin.supervisor.model.exception.SupervisorException;


public class SupervisorDao {

	private Properties prop = new Properties();
	
	public SupervisorDao() {
		String filename = SupervisorDao.class.getResource("/sql/zoomin/supervisor/supervisor-query.properties").getPath();
		//System.out.println("filename@SupervisorDao = " + filename);
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ApplicantMember handleApplicantMemberRset(ResultSet rset) {
		int uid = rset.getInt("uid");
		String name = rset.getString("name");
		String id = rset.getString("id");
		String password = rset.getString("password");
		String phone = rset.getString("phone");
		String email = rset.getString("email");
		Date regDate = rset.getDate("reg_date");
		return new ApplicantMember(uid, name, id, password, phone, email, regDate);
	}
	
	
	private RecruitMember handleRecruitMemberRset(ResultSet rset) {
		int uid = rset.getInt("uid");
		String companyNo = rset.getString("companyNo");
		String name = rset.getString("name");
		String id = rset.getString("id");
		String password = rset.getString("password");
		String email = rset.getString("email");
		Date regDate = rset.getDate("reg_date");
		return new RecruitMember(uid, companyNo, name, id, password, email, regDate);
	}
	
	
	public List<ApplicantMember> findApplicantMemberAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ApplicantMember> applicantMemberList = new ArrayList<>();
		String sql = prop.getProperty("findApplicantMemberAll");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ApplicantMember applicantMember = handleApplicantMemberRset(rset);
				applicantMemberList.add(applicantMember);
			}
		} catch (Exception e) {
			throw new SupervisorException("구인자 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return applicantMemberList;
	}

	public List<RecruitMember> findRecruitMemberAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecruitMember> recruitMemberList = new ArrayList<>();
		String sql = prop.getProperty("findRecruitMemberAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				RecruitMember recruitMember = handleRecruitMemberRset(rset);
				recruitMemberList.add(recruitMember);
			}
		} catch (Exception e) {
			throw new SupervisorException("구직자 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return recruitMemberList;
	}

	public List<ApplicantMember> findApplicantMemberLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ApplicantMember> applicantMemberList = new ArrayList<>();
		String sql = prop.getProperty("findApplicantMemberLike");
		//findApplicantMemberLike = select * from applicantMember where # like ?
		
		//# 컬럼명 처리하기
		String col = (String) param.get("searchType");
		String val = (String) param.get("searchKeyword");
		sql = sql.replace("#", col);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + val + "%");
			rset = pstmt.executeQuery();
			while(rset.next())
				applicantMemberList.add(handleApplicantMemberRset(rset));			
		} catch (SQLException e) {
			throw new SupervisorException("관리자 회원검색 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}				
		return applicantMemberList;
	}

	public List<RecruitMember> findRecruitMemberLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecruitMember> recruitMemberList = new ArrayList<>();
		String sql = prop.getProperty("findApplicantMemberLike");
		//findRecruitMemberLike = select * from recruitMember where # like ?
		
		//# 컬럼명 처리하기
		String col = (String) param.get("searchType");
		String val = (String) param.get("searchKeyword");
		sql = sql.replace("#", col);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + val + "%");
			rset = pstmt.executeQuery();
			while(rset.next())
				recruitMemberList.add(handleRecruitMemberRset(rset));			
		} catch (SQLException e) {
			throw new SupervisorException("관리자 회사검색 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}				
		return recruitMemberList;
	}

	//오늘의 방문자 수 
	public int getTodayCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int todayCnt = 0;
		String sql = prop.getProperty("getTodayVisitCnt");
		//System.out.println("sql = " + sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				todayCnt = rset.getInt(1);
		} catch (Exception e) {
			throw new SupervisorException("오늘 방문자 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return todayCnt;
	}

	//전체 방문자 수 조회
	public int getTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCnt = 0;
		String sql = prop.getProperty("getTotalVisitCnt");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalCnt = rset.getInt(1);
		} catch (Exception e) {
			throw new SupervisorException("전체 방문자 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalCnt;
	}

	//방문자 수 증가
	public int setCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("setTotalCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SupervisorException("방문자 수 증가 오류!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//회사리뷰게시판 조회
	public int getTodayComCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int todayComCnt = 0;
		String sql = prop.getProperty("getTodayComCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				todayComCnt = rset.getInt(1);
		} 
		catch (Exception e) {
			throw new SupervisorException("회사리뷰게시판 오늘의 게시글 수 조회 오류!", e);
		} 
		finally {
			close(rset);
			close(pstmt);
		}
		
			return todayComCnt;
		}

	//연봉게시판 조회
	public int getTodaySalCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int todaySalCnt = 0;
		String sql = prop.getProperty("getTodaySalCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				todaySalCnt = rset.getInt(1);
		} 
		catch (Exception e) {
			throw new SupervisorException("회사리뷰게시판 오늘의 게시글 수 조회 오류!", e);
		} 
		finally {
			close(rset);
			close(pstmt);
		}		
			return todaySalCnt;
	}

	public int getTotalBoardCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalBoardCnt = 0;
		String sql = prop.getProperty("getTotalBoardCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalBoardCnt = rset.getInt(1);
		} 
		catch (Exception e) {
			throw new SupervisorException("게시판 전체글 수 조회 오류!", e);
		} 
		finally {
			close(rset);
			close(pstmt);
		}		
			return totalBoardCnt;
	}

	//날짜별 방문자 수 조회
	public int getVisitCount(Connection conn, String dateStart, String dateEnd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int visitCnt = 0;
		String sql = prop.getProperty("getVisitCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateStart);
			pstmt.setString(2, dateEnd);
			rset = pstmt.executeQuery();
			if(rset.next())
				visitCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("날짜별 방문자수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return visitCnt;
	}

	//날짜별 게시글 수 조회
	public int getBoardCount(Connection conn, String dateStart, String dateEnd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int boardCnt = 0;
		String sql = prop.getProperty("getBoardCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateStart);
			pstmt.setString(2, dateEnd);
			rset = pstmt.executeQuery();
			if(rset.next())
				boardCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("날짜별 게시글 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return boardCnt;
	}
	

	

}
