package com.kh.zoomin.supervisor.model.service;



import static com.kh.zoomin.common.JdbcTemplate.close;
import static com.kh.zoomin.common.JdbcTemplate.commit;
import static com.kh.zoomin.common.JdbcTemplate.getConnection;
import static com.kh.zoomin.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.recruit.member.model.dto.RecruitMember;
import com.kh.zoomin.supervisor.model.dto.RecruitBoard;
import com.kh.zoomin.supervisor.model.dto.Rmember;
import com.kh.zoomin.supervisor.model.dto.RmemberLog;
import com.kh.zoomin.supervisor.model.dao.SupervisorDao;
import com.kh.zoomin.supervisor.model.dto.AmemberLog;
import com.kh.zoomin.supervisor.model.dto.CompanyReview;
import com.kh.zoomin.supervisor.model.dto.SalaryReview;
import com.kh.zoomin.supervisor.model.dto.WeekData;

public class SupervisorService {
	
	private SupervisorDao supervisorDao = new SupervisorDao();
	
	//DQL : select * from applicantMember
	public List<ApplicantMember> findApplicantMemberAll(Map<String, Object> param){
		Connection conn = getConnection();
		List<ApplicantMember> applicantMemberList = supervisorDao.findApplicantMemberAll(conn, param);
		close(conn);
		return applicantMemberList;
	}
	
	//DQL : select * from recruitMember
	public List<Rmember> findRecruitMemberAll(Map<String, Object> param){
		Connection conn = getConnection();
		List<Rmember> recruitMemberList = supervisorDao.findRecruitMemberAll(conn, param);
		close(conn);
		return recruitMemberList;
	}

	//DQL : select * from applicantMember where # like ?
//	public List<ApplicantMember> findApplicantMemberLike(Map<String, Object> param) {
//		Connection conn = getConnection();
//		List<ApplicantMember> applicantMemberList = supervisorDao.findApplicantMemberLike(conn, param);
//		close(conn);
//		return applicantMemberList;
//	}
//
//	//DQL : select * from recruitMember where # like ?
//	public List<RecruitMember> findRecruitMemberLike(Map<String, Object> param) {
//		Connection conn = getConnection();
//		List<RecruitMember> recruitMemberList = supervisorDao.findRecruitMemberLike(conn, param);
//		close(conn);
//		return recruitMemberList;
//	}

	//today방문자 수 조회
	//select count(*) from visit where to_date(v_date, 'yyyy-mm-dd') = to_date(sysdate, 'yyyy-mm-dd')
	public int getTodayCount() {
		Connection conn = getConnection();
		int todayCnt = supervisorDao.getTodayCount(conn);
		close(conn);
		return todayCnt;
	}

	//총 방문자 수 조회
	public int getTotalCount() {
		Connection conn = getConnection();
		int totalCnt = supervisorDao.getTotalCount(conn);
		close(conn);
		return totalCnt;
	}

	//총 방문자 수 증가 : 테이블에 현재 날짜 값 추가
	//insert into visit values (sysdate)
	public int setCount() {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = supervisorDao.setCount(conn);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);			
		}
		return result;
	}

	//회사리뷰게시판 조회
	public int getTodayComCnt() {
		Connection conn = getConnection();
		int todayComCnt = supervisorDao.getTodayComCnt(conn);
		close(conn);
		return todayComCnt;
	}

	//연봉게시판 조회
	public int getTodaySalCnt() {
		Connection conn = getConnection();
		int todaySalCnt = supervisorDao.getTodaySalCnt(conn);
		close(conn);
		return todaySalCnt;
	}

	//전체 게시판 글 수 조회
	public int getTotalBoardCnt() {
		Connection conn = getConnection();
		int totalBoardCnt = supervisorDao.getTotalBoardCnt(conn);
		close(conn);
		return totalBoardCnt;
	}
	
	//날짜별 방문자 수 조회
	public int getVisitCount(String dateStart, String dateEnd) {
		Connection conn = getConnection();
		int visitCnt = supervisorDao.getVisitCount(conn, dateStart, dateEnd);
		close(conn);
		return visitCnt;
	}

	//날짜별 게시글 수 조회
	public int getBoardCount(String dateStart, String dateEnd) {
		Connection conn = getConnection();
		int boardCnt = supervisorDao.getBoardCount(conn, dateStart, dateEnd);
		close(conn);
		return boardCnt;
	}

	//일주일간 방문자 수 조회 
	public List<WeekData> getVisitData() {
		List<WeekData> visitList = new ArrayList<>();
		Connection conn = getConnection();
		visitList = supervisorDao.getVisitData(conn);
		close(conn);
		return visitList;
	}

	//일주일간 게시글 수 조회
	public List<WeekData> getBoardData() {
		List<WeekData> boardList = new ArrayList<>();
		Connection conn = getConnection();
		boardList = supervisorDao.getBoardData(conn);
		close(conn);
		return boardList;
	}

	//연봉리뷰 전체조회
	public List<SalaryReview> getSalReviewAll(Map<String, Object> param) {
		List<SalaryReview> salList = new ArrayList<>();
		Connection conn = getConnection();
		salList = supervisorDao.getSalReviewAll(conn, param);
		close(conn);
		return salList;
	}

	//회사리뷰 전체조회
	public List<CompanyReview> getComReviewAll(Map<String, Object> param) {
		List<CompanyReview> comList = new ArrayList<>();
		Connection conn = getConnection();
		comList = supervisorDao.getComReviewAll(conn, param);
		close(conn);
		return comList;
	}

	//채용게시판 전체조회
	public List<RecruitBoard> getRecBoardAll(Map<String, Object> param) {
		List<RecruitBoard> recList = new ArrayList<>();
		Connection conn = getConnection();
		recList = supervisorDao.getRecBoardAll(conn, param);
		close(conn);
		return recList;
	}
	
	//연봉리뷰 전체 게시글 수 조회
	public int getTotalSalReviewCnt() {
		Connection conn = getConnection();
		int totalSalReviewCnt = supervisorDao.getTotalSalReviewCnt(conn);
		close(conn);
		return totalSalReviewCnt;
	}

	//회사리뷰 전체 게시글 수 조회
	public int getTotalComReviewCnt() {
		Connection conn = getConnection();
		int totalComReviewCnt = supervisorDao.getTotalComReviewCnt(conn);
		close(conn);
		return totalComReviewCnt;
	}

	//연봉리뷰 게시글 삭제
	public int deleteSalReview(String[] salBoardNo) {		
		Connection conn = getConnection();
		int result = 0;
		try {
			result = supervisorDao.deleteSalReview(conn, salBoardNo);
			if(salBoardNo.length == result)
				commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public int getTotalComRecruitCnt() {
		Connection conn = getConnection();
		int totalComRecruitCnt = supervisorDao.getTotalComRecruitCnt(conn);
		close(conn);
		return totalComRecruitCnt;
	}

	//채용게시판삭제
	public int deleteComBoard(String[] comBoardNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = supervisorDao.deleteComBoard(conn, comBoardNo);
			if(comBoardNo.length == result)
				commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	//회사리뷰게시판 삭제
	public int deleteComReview(String[] comBoardNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = supervisorDao.deleteComReview(conn, comBoardNo);
			if(comBoardNo.length == result)
				commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public int getTotalAmCnt() {
		Connection conn = getConnection();
		int totalAmCnt = supervisorDao.getTotalAmCnt(conn);
		close(conn);
		return totalAmCnt;
	}

	public int getTotalRmCnt() {
		Connection conn = getConnection();
		int totalRmCnt = supervisorDao.getTotalRmCnt(conn);
		close(conn);
		return totalRmCnt;
	}

	//구직자 삭제 
	public int deleteAmember(String[] amUid) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = supervisorDao.deleteAmember(conn, amUid);
			if(amUid.length == result)
				commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	//구인자 삭제
	public int deleteRmember(String[] rmUid) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = supervisorDao.deleteRmember(conn, rmUid);
			if(rmUid.length == result)
				commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	
	public List<AmemberLog> getAmemberLogAll() {
		Connection conn = getConnection();
		List<AmemberLog> amLogList = supervisorDao.getAmemberLogAll(conn);
		close(conn);
		return amLogList;
	}

	public List<RmemberLog> getRmemberLogAll() {
		Connection conn = getConnection();
		List<RmemberLog> rmLogList = supervisorDao.getRmemberLogAll(conn);
		close(conn);
		return rmLogList;
	}

	
	
}
