package com.kh.zoomin.supervisor.model.dao;

import static com.kh.zoomin.common.JdbcTemplate.close;
import static com.kh.zoomin.common.JdbcTemplate.getConnection;

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
import com.kh.zoomin.supervisor.model.dto.AmemberLog;
import com.kh.zoomin.supervisor.model.dto.ComLog;
import com.kh.zoomin.supervisor.model.dto.CompanyReview;
import com.kh.zoomin.supervisor.model.dto.RecLog;
import com.kh.zoomin.supervisor.model.dto.RecruitBoard;
import com.kh.zoomin.supervisor.model.dto.Rmember;
import com.kh.zoomin.supervisor.model.dto.RmemberLog;
import com.kh.zoomin.supervisor.model.dto.SalLog;
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
	
	private ApplicantMember handleAMemberRset(ResultSet rset) throws SQLException {
		int uid = rset.getInt("uid");
		String name = rset.getString("name");
		String id = rset.getString("id");
		String password = rset.getString("password");
		String phone = rset.getString("phone");
		String email = rset.getString("email");
		Date regDate = rset.getDate("reg_date");
		return new ApplicantMember(uid, name, id, password, phone, email, regDate);
	}
	
	
	private Rmember handleRmemberRset(ResultSet rset) throws SQLException {
		int uid = rset.getInt("uid");
		String companyNo = rset.getString("company_no");
		String comName = rset.getString("company_name");
		String recruiter = rset.getString("name");
		String id = rset.getString("id");
		String email = rset.getString("email");
		//Boolean supervisor = rset.getBoolean("supervisor");
		Date regDate = rset.getDate("reg_date");
		return new Rmember(uid, companyNo, comName, recruiter, id, email, regDate);
	}
	
	
	public List<ApplicantMember> findApplicantMemberAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ApplicantMember> applicantMemberList = new ArrayList<>();
		String sql = prop.getProperty("findApplicantMemberAll");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				applicantMemberList.add(handleAMemberRset(rset));
			}
		} catch (Exception e) {
			throw new SupervisorException("구인자 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return applicantMemberList;
	}

	public List<Rmember> findRecruitMemberAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Rmember> recruitMemberList = new ArrayList<>();
		String sql = prop.getProperty("findRecruitMemberAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				recruitMemberList.add(handleRmemberRset(rset));
			}
		} catch (Exception e) {
			throw new SupervisorException("구직자 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return recruitMemberList;
	}

//	public List<ApplicantMember> findApplicantMemberLike(Connection conn, Map<String, Object> param) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		List<ApplicantMember> applicantMemberList = new ArrayList<>();
//		String sql = prop.getProperty("findApplicantMemberLike");
//		//findApplicantMemberLike = select * from applicantMember where # like ?
//		
//		//# 컬럼명 처리하기
//		String col = (String) param.get("searchType");
//		String val = (String) param.get("searchKeyword");
//		sql = sql.replace("#", col);
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%" + val + "%");
//			rset = pstmt.executeQuery();
//			while(rset.next())
//				applicantMemberList.add(handleApplicantMemberRset(rset));			
//		} catch (SQLException e) {
//			throw new SupervisorException("관리자 회원검색 오류!", e);
//		} finally {
//			close(rset);
//			close(pstmt);
//		}				
//		return applicantMemberList;
//	}
//
//	public List<RecruitMember> findRecruitMemberLike(Connection conn, Map<String, Object> param) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		List<RecruitMember> recruitMemberList = new ArrayList<>();
//		String sql = prop.getProperty("findApplicantMemberLike");
//		//findRecruitMemberLike = select * from recruitMember where # like ?
//		
//		//# 컬럼명 처리하기
//		String col = (String) param.get("searchType");
//		String val = (String) param.get("searchKeyword");
//		sql = sql.replace("#", col);
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%" + val + "%");
//			rset = pstmt.executeQuery();
//			while(rset.next())
//				recruitMemberList.add(handleRecruitMemberRset(rset));			
//		} catch (SQLException e) {
//			throw new SupervisorException("관리자 회사검색 오류!", e);
//		} finally {
//			close(rset);
//			close(pstmt);
//		}				
//		return recruitMemberList;
//	}

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
		String category = rset.getString("domain");	//회사분류
		String companyName = rset.getString("company_name");
		String writer = rset.getString("id");
		int salary = rset.getInt("salary");
		int workYear = rset.getInt("work_year");
		String jobPosition = rset.getString("position_name");
		Date regDate = rset.getDate("reg_date");
		return new SalaryReview(no, category, companyName, writer, salary, workYear, jobPosition, regDate);
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
		String companyName = rset.getString("company_name");
		String content = rset.getString("content");
		String id = rset.getString("id");
		Date regDate = rset.getDate("reg_date");
		return new CompanyReview(no,companyName, content, id, regDate);
	}

	//채용게시글 전체조회
	public List<RecruitBoard> getRecBoardAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecruitBoard> recList = new ArrayList<>();
		String sql = prop.getProperty("getRecBoardAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				recList.add(handleRecBoardRset(rset));	
			}
		} catch (Exception e) {
			throw new SupervisorException("회사리뷰 전체 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return recList;
	}
	
	private RecruitBoard handleRecBoardRset(ResultSet rset) throws SQLException {
		int no = rset.getInt("no");
		String category = rset.getString("domain");
		String companyName = rset.getString("company_name");
		String recruiter = rset.getString("name");
		String title = rset.getString("title");
		Date regDate = rset.getDate("reg_date");
		Date closureDate = rset.getDate("closure_date");
		return new RecruitBoard(no, category, companyName, recruiter, title, regDate, closureDate);
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

	public int getTotalComRecruitCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalComRecruitCnt = 0;
		String sql = prop.getProperty("getTotalComRecruitCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalComRecruitCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("채용게시판 전체글 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalComRecruitCnt;
	}

	public int deleteComBoard(Connection conn, String[] comBoardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteComBoard");
		int result = 0;	//성공한 행의 개수
		int[] cnt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < comBoardNo.length; i++) {
				pstmt.setString(1, comBoardNo[i]);
				pstmt.addBatch();	//쿼리 pstmt에 쌓기
			}
			cnt = pstmt.executeBatch();		//성공하면 1을 반환
			for(int i = 0; i < cnt.length; i++) {
					result++;						
			}
			
		} catch (Exception e) {
			throw new SupervisorException("채용 게시글 삭제 오류!", e);		
		} finally {
			close(pstmt);
		}		
		return result;
	}

	//회사리뷰 게시글 삭제
	public int deleteComReview(Connection conn, String[] comBoardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteComReview");
		int result = 0;	//성공한 행의 개수
		int[] cnt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < comBoardNo.length; i++) {
				pstmt.setString(1, comBoardNo[i]);
				pstmt.addBatch();	//쿼리 pstmt에 쌓기
			}
			cnt = pstmt.executeBatch();		//성공하면 1을 반환
			for(int i = 0; i < cnt.length; i++) {
					result++;						
			}
			
		} catch (Exception e) {
			throw new SupervisorException("채용 게시글 삭제 오류!", e);		
		} finally {
			close(pstmt);
		}		
		return result;
	}

	public int getTotalAmCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalAmCnt = 0;
		String sql = prop.getProperty("getTotalAmCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalAmCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("구직자 전체 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalAmCnt;
	}
	
	public int getTotalRmCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalRmCnt = 0;
		String sql = prop.getProperty("getTotalRmCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalRmCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("구인자 전체 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalRmCnt;
	}

	//구직자 삭제
	public int deleteAmember(Connection conn, String[] amUid) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAmember");
		int result = 0;	//성공한 행의 개수
		int[] cnt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < amUid.length; i++) {
				pstmt.setString(1, amUid[i]);
				pstmt.addBatch();	//쿼리 pstmt에 쌓기
			}
			cnt = pstmt.executeBatch();		//성공하면 1을 반환
			for(int i = 0; i < cnt.length; i++) {
					result++;						
			}
			
		} catch (Exception e) {
			throw new SupervisorException("구직자 삭제 오류!", e);		
		} finally {
			close(pstmt);
		}		
		return result;
	}

	public int deleteRmember(Connection conn, String[] rmUid) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteRmember");
		int result = 0;	//성공한 행의 개수
		int[] cnt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < rmUid.length; i++) {
				pstmt.setString(1, rmUid[i]);
				pstmt.addBatch();	//쿼리 pstmt에 쌓기
			}
			cnt = pstmt.executeBatch();		//성공하면 1을 반환
			for(int i = 0; i < cnt.length; i++) {
					result++;						
			}
			
		} catch (Exception e) {
			throw new SupervisorException("구인자 삭제 오류!", e);		
		} finally {
			close(pstmt);
		}		
		return result;
	}

	public List<AmemberLog> getAmemberLogAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<AmemberLog> amLogList = new ArrayList<>();
		String sql = prop.getProperty("getAmemberLogAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				int uid = rset.getInt("uid");
				String name = rset.getString("name");
				String id = rset.getString("id");
				String phone = rset.getString("phone");
				String email = rset.getString("email");
				String log = rset.getString("log");
				Date logDate = rset.getDate("log_date");
				amLogList.add(new AmemberLog(no, uid, name, id, phone, email, log, logDate));
			}
				
		} catch (SQLException e) {
			throw new SupervisorException("구직자 log 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return amLogList;
	}

	public List<RmemberLog> getRmemberLogAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RmemberLog> rmLogList = new ArrayList<>();
		String sql = prop.getProperty("getRmemberLogAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				int uid = rset.getInt("uid");
				String companyNo = rset.getString("company_no");
				String name = rset.getString("name");
				String id = rset.getString("id");
				String email = rset.getString("email");
				String log = rset.getString("log");
				Date logDate = rset.getDate("log_date");
				rmLogList.add(new RmemberLog(no, uid, companyNo, name, id, email, log, logDate));
			}
				
		} catch (SQLException e) {
			throw new SupervisorException("구인자 log 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return rmLogList;
	}

	public List<SalLog> getSalLogAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SalLog> salLogList = new ArrayList<>();
		String sql = prop.getProperty("getSalLogAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				int uid = rset.getInt("uid");
				int boardNo = rset.getInt("board_no");
				String companyNo = rset.getString("company_no");
				String log = rset.getString("log");
				Date logDate = rset.getDate("log_date");
				salLogList.add(new SalLog(no, uid, boardNo, companyNo, log, logDate));
			}
				
		} catch (SQLException e) {
			throw new SupervisorException("연봉게시판 log 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return salLogList;
	}

	public List<RecLog> getRecLogAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecLog> recLogList = new ArrayList<>();
		String sql = prop.getProperty("getRecLogAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				int boardNo = rset.getInt("board_no");
				int uid = rset.getInt("uid");
				String companyNo = rset.getString("company_no");
				String title = rset.getString("title");
				String log = rset.getString("log");
				Date logDate = rset.getDate("log_date");
				recLogList.add(new RecLog(no, boardNo, uid, companyNo, title, log, logDate));
			}
				
		} catch (SQLException e) {
			throw new SupervisorException("채용게시판 log 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return recLogList;
	}

	public List<ComLog> getComLogAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ComLog> comLogList = new ArrayList<>();
		String sql = prop.getProperty("getComLogAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				int boardNo = rset.getInt("board_no");
				int uid = rset.getInt("uid");
				String companyNo = rset.getString("company_no");
				String log = rset.getString("log");
				Date logDate = rset.getDate("log_date");
				comLogList.add(new ComLog(no, boardNo, uid, companyNo,log, logDate));
			}
				
		} catch (SQLException e) {
			throw new SupervisorException("회사 리뷰게시판 log 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return comLogList;
	}

	public int getTotalAmLogCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalAmLogCnt = 0;
		String sql = prop.getProperty("getTotalAmLogCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalAmLogCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("구직자 로그 전체 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalAmLogCnt;
	}

	public int getTotalRmLogCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalRmLogCnt = 0;
		String sql = prop.getProperty("getTotalRmLogCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalRmLogCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("구인자 로그 전체 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalRmLogCnt;
	}

	public int getTotalSalLogCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalSalLogCnt = 0;
		String sql = prop.getProperty("getTotalSalLogCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalSalLogCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("연봉게시판 로그 전체 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalSalLogCnt;
	}

	public int getTotalComLogCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalComLogCnt = 0;
		String sql = prop.getProperty("getTotalComLogCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalComLogCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("회사리뷰게시판 로그 전체 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalComLogCnt;
	}

	public int getTotalRecLogCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalRecLogCnt = 0;
		String sql = prop.getProperty("getTotalRecLogCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalRecLogCnt = rset.getInt(1);
		} catch (SQLException e) {
			throw new SupervisorException("채용게시판 로그 전체 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalRecLogCnt;
	}



	

}
