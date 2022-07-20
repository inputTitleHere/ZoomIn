package com.kh.zoomin.supervisor.model.dao;

import static com.kh.mvc.common.JdbcTemplate.close;
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

import com.kh.mvc.board.model.exception.BoardException;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.recruit.member.RecruitMember;
import com.kh.zoomin.supervisor.model.dto.CompanyReview;
import com.kh.zoomin.supervisor.model.dto.SalaryReview;
import com.kh.zoomin.supervisor.model.dto.WeekData;
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

	//일주일간 방문자 수 조회
	public List<WeekData> getVisitData(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<WeekData> visitList = new ArrayList<>();
		String sql = prop.getProperty("getVisitData");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Date day = rset.getDate("date");
				int cnt = rset.getInt("cnt");
				visitList.add(new WeekData(day, cnt));
			}
		} catch (Exception e) {
			throw new SupervisorException("일주일간 방문자 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}	
		return visitList;
	}
	
	//일주일간 게시글 수 조회
	public List<WeekData> getBoardData(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<WeekData> boardList = new ArrayList<>();
		String sql = prop.getProperty("getBoardData");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Date day = rset.getDate("date");
				int cnt = rset.getInt("cnt");
				boardList.add(new WeekData(day, cnt));
			}
		} catch (Exception e) {
			throw new SupervisorException("일주일간 게시글 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}	
		return boardList;
	}

	//연봉리뷰 전체조회
	public List<SalaryReview> getSalReviewAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SalaryReview> salList = new ArrayList<>();
		String sql = prop.getProperty("getSalReviewAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				salList.add(handleSalReviewRset(rset));	
			}
		} catch (Exception e) {
			throw new SupervisorException("연봉리뷰 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return salList;
	}

	private SalaryReview handleSalReviewRset(ResultSet rset) throws SQLException {
		int no = rset.getInt("no");
		String writer = rset.getString("id");
		String companyNo = rset.getString("company_no");
		String category = rset.getString("domain");	
		int salary = rset.getInt("salary");
		int workYear = rset.getInt("work_year");
		String jobPosition = rset.getString("position_name");
		Date regDate = rset.getDate("reg_date");
		return new SalaryReview(no, writer, companyNo, category, salary, workYear, jobPosition, regDate);
	}

	public List<CompanyReview> getComReviewAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CompanyReview> comList = new ArrayList<>();
		String sql = prop.getProperty("getComReviewAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				comList.add(handleComReviewRset(rset));	
			}
		} catch (Exception e) {
			throw new SupervisorException("회사리뷰 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return comList;
	}

	private CompanyReview handleComReviewRset(ResultSet rset) throws SQLException {
		int no = rset.getInt("no");
		String id = rset.getString("id");
		String content = rset.getString("content");
		Date regDate = rset.getDate("reg_date");
		return new CompanyReview(no, id, content, regDate);
	}

	public int getTotalSalReviewCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int getTotalSalReviewCnt = 0;
		String sql = prop.getProperty("getTotalSalReviewCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				getTotalSalReviewCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("연봉리뷰 전체글 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return getTotalSalReviewCnt;
	}

	public int getTotalComReviewCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int getTotalComReviewCnt = 0;
		String sql = prop.getProperty("getTotalComReviewCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				getTotalComReviewCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("회사리뷰 전체글 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return getTotalComReviewCnt;
	}

	//연봉게시글 삭제
	public int deleteSalReview(Connection conn, String[] salBoardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteSalReview");
		int result = 0;	//성공한 행의 개수
		int[] cnt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < salBoardNo.length; i++) {
				pstmt.setString(1, salBoardNo[i]);
				pstmt.addBatch();	//쿼리 pstmt에 쌓기
			}
			cnt = pstmt.executeBatch();		//성공하면 1을 반환
			for(int i = 0; i < cnt.length; i++) {
					result++;						
			}
			
		} catch (Exception e) {
			throw new SupervisorException("리뷰 게시글 삭제 오류!", e);		
		} finally {
			close(pstmt);
		}		
		return result;
	}



	

}
