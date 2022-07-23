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

import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.recruit.board.dto.RecruitBoard;
import com.kh.zoomin.recruit.board.dto.RecruitBoardReadMode;
import com.kh.zoomin.recruit.board.exception.RecruitBoardException;

public class RecruitBoardDao {
	private Properties prop = new Properties();

	public RecruitBoardDao() {
		String filename = RecruitBoardDao.class.getResource("/sql/zoomin/recruit/board/recruit-board-query.properties")
				.getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			throw new RecruitBoardException("RecruitBoard properties failed to load", e);
		}
	}

	/**
	 * RECRUIT_BOARD에 대한 쿼리문 처리를 진행하는 메소드입니다.
	 * 
	 * @param rset
	 * @return
	 * @throws SQLException
	 */
	private RecruitBoard handleRecruitBoard(ResultSet rset) throws SQLException {
		int no = rset.getInt("no");
		int uid = rset.getInt("uid");
		int categoryNumber = rset.getInt("category_number");
		String companyNo = rset.getString("company_no");
		String title = rset.getString("title");
		String careerYears = rset.getString("career_years_req");
		String schoolStatus = rset.getString("school_status");
		String workType = rset.getString("work_type");
		String officeLocation = rset.getString("office_location");
		String salary = rset.getString("salary");
		String content = rset.getString("content");
		Date closure_date = rset.getDate("closure_date");
		Date reg_date = rset.getDate("reg_date");

		return new RecruitBoard(no, uid, categoryNumber, companyNo, title, careerYears, schoolStatus, workType,
				officeLocation, salary, content, closure_date, reg_date);
	}

	/**
	 * 파라미터로 전달된 값 만큼 데이터베이스에서 읽어옵니다.
	 * 
	 * @param param
	 * @param conn
	 * @return
	 */
	public List<RecruitBoard> loadRecruitBoardHeaders(Map<String, Object> param, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// [1번 2번] ? 에는 closure_date asc(마감임박) 또는 uid desc이 들어갈것(최신순) -> param에서 꺼낸다.
//		loadRecruitBoard=
//			select * from (select row_number() over(order by #) rnum, r.* rom recruit_board r where closure_date-sysdate>0) b where rnum between ? and ?
		String sql = prop.getProperty("loadRecruitBoard");
		RecruitBoardReadMode mode = (RecruitBoardReadMode) param.get("mode");
		if (mode == RecruitBoardReadMode.NEAR_CLOSURE) {
			sql = sql.replace("#", "closure_date asc");
		} else if (mode == RecruitBoardReadMode.MOST_RECENT) {
			sql = sql.replace("#", "no desc");
		} else {
			sql = sql.replace("#", "closure_date asc"); // null이거나 이상한 값이면 그냥 기본 설정으로 진행
		}

		List<RecruitBoard> result = new ArrayList<RecruitBoard>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (Integer) param.get("start"));
			pstmt.setInt(2, (Integer) param.get("end"));
			rset = pstmt.executeQuery();
//			System.out.println("@RecruitBoardDao sql query = "+rset.getStatement().toString());
			// 쿼리 결과 처리
			while (rset.next()) {
				result.add(handleRecruitBoard(rset));
			}
		} catch (SQLException e) {
			throw new RecruitBoardException("채용게시판 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int totalRecruitBoardCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("totalRecruitBoardCount");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			// 단일결과를 반환하므로 한번만 rset.next를 한다.
			rset.next();
			result = rset.getInt(1);
		} catch (SQLException e) {
			throw new RecruitBoardException("총 채용게시글수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public RecruitBoard viewRecruitBoard(int boardNo, Connection conn) {
		RecruitBoard result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// viewRecruitBoard=select * from recruit_board where no=?
		String sql = prop.getProperty("viewRecruitBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				result = handleRecruitBoard(rset);
			}

		} catch (SQLException e) {
			throw new RecruitBoardException("단별 채용게시글 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public List<RecruitBoard> loadRecruiterBoard(Map<String, Object> param, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecruitBoard> result = new ArrayList<RecruitBoard>();
		String sql = prop.getProperty("loadRecruiterBoard");
//		loadRecruiterBoard=select * from recruit_board where "uid"=? order by #

		// SQL 구문에 대한 상세 처리(#를 대체함)
		RecruitBoardReadMode mode = (RecruitBoardReadMode) param.get("recruiterMode"); // 20220717기준 무조건 null임. null타입도
																						// casting가능함 그냥 null일뿐
		if (mode == RecruitBoardReadMode.NEAR_CLOSURE) {
			sql = sql.replace("#", "closure_date asc");
		} else if (mode == RecruitBoardReadMode.MOST_RECENT) {
			sql = sql.replace("#", "no desc");
		} else {
			sql = sql.replace("#", "closure_date asc"); // null이거나 이상한 값이면 그냥 기본 설정으로 진행
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (Integer) param.get("uid"));
			rset = pstmt.executeQuery();
			// 쿼리 결과 처리
			while (rset.next()) {
				RecruitBoard rb=handleRecruitBoard(rset);
				result.add(rb);
			}
		} catch (SQLException e) {
			throw new RecruitBoardException("채용게시판 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int insertRecruitBoard(RecruitBoard rb, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertRecruitBoard");
		// insert into RECRUIT_BOARD values(seq_recruit_board.nextval,
		// 1?,2?,3?,4?,5?,6?,7?,8?,9?,10?,11?,default)
		// 1uid,2category_number,3company_no,4title,5career_years_req,6school_status,7work_type,8office_location,9salary,10content,11closure_date,(default)reg_date
		System.out.println(rb);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb.getUid());
			pstmt.setInt(2, rb.getCategoryNumber());
			pstmt.setString(3, rb.getCompanyNo());
			pstmt.setString(4, rb.getTitle());
			pstmt.setString(5, rb.getCareerYears());
			pstmt.setString(6, rb.getSchoolStatus());
			pstmt.setString(7, rb.getWorkType());
			pstmt.setString(8, rb.getOfficeLocation());
			pstmt.setString(9, rb.getSalary());
			pstmt.setString(10, rb.getContent());
			pstmt.setDate(11, rb.getClosureDate());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RecruitBoardException("채용글 삽입 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteRecruitBoard(int no, Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteRecruitBoard");
		// delete from RECRUIT_BOARD where no=?
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RecruitBoardException("채용글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public RecruitBoard findBoardByNo(int no, Connection conn) {
		RecruitBoard result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findBoardByNo");
		// select * from recruit_board where no = ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				result = handleRecruitBoard(rset);
			}
		} catch (SQLException e) {
			throw new RecruitBoardException("단일 채용글 조회 오류 ", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int updateRecruitBoard(RecruitBoard rb, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateRecruitBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rb.getTitle());
			pstmt.setInt(2, rb.getCategoryNumber());
			pstmt.setString(3, rb.getCareerYears());
			pstmt.setString(4, rb.getSchoolStatus());
			pstmt.setString(5, rb.getWorkType());
			pstmt.setString(6, rb.getOfficeLocation());
			pstmt.setString(7, rb.getSalary());
			pstmt.setString(8, rb.getContent());
			pstmt.setDate(9, rb.getClosureDate());
			pstmt.setInt(10, rb.getNo());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RecruitBoardException("채용게시판 업데이트 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public boolean isRecruitBoardFaved(int boardNo, int uid, Connection conn) {
		boolean isFaved=false;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		// select count(*) from FAVOURITE where boardNo=? and "uid"=?
		String sql=prop.getProperty("isFaved");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, uid);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				int count=rset.getInt(1);
				if(count>0) {
					isFaved=true;
				}
			}
			
		}catch(SQLException e) {
			throw new RecruitBoardException("찜 조회 오류",e);
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return isFaved;
	}

	public boolean isRecruitBoardEnrolled(int boardNo, int uid, Connection conn) {
		boolean isEnrolled=false;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		// select count(*) from ENROLL_TABLE where boardNo=? and "uid"=?
		String sql=prop.getProperty("isEnrolled");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, uid);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				int count=rset.getInt(1);
				if(count>0) {
					isEnrolled=true;
				}
			}
			
		}catch(SQLException e) {
			throw new RecruitBoardException("지원하기 조회 오류",e);
			
		}finally {
			close(rset);
			close(pstmt);
		}
		return isEnrolled;
	}

	public int setFavourite(int boardNo, int applicantUid, Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		//insert into FAVOURITE values(?(uid), ?(boardNo))
		String sql = prop.getProperty("setFavourite");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, applicantUid);
			pstmt.setInt(2, boardNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new RecruitBoardException("찜하기 set 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int removeFavourite(int boardNo, int applicantUid, Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		// removeFavourite=delete from FAVOURITE where "uid"=? and recruit_board_no=?
		String sql = prop.getProperty("removeFavourite");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, applicantUid);
			pstmt.setInt(2, boardNo);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RecruitBoardException("찜 취소하기 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int setEnroll(int boardNo, int applicantUid, Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		//insert into ENROLL_TABLE values(?(uid), ?(boardNo))
		String sql = prop.getProperty("setEnroll");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, applicantUid);
			pstmt.setInt(2, boardNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new RecruitBoardException("이력서 등록하기 set 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int removeEnroll(int boardNo, int applicantUid, Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		// removeEnroll=delete from ENROLL_TABLE where "uid"=? and recruit_board_no=?
		String sql = prop.getProperty("removeEnroll");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, applicantUid);
			pstmt.setInt(2, boardNo);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RecruitBoardException("찜 취소하기 오류",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	


}
