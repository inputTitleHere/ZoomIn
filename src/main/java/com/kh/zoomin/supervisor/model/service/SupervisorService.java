package com.kh.zoomin.supervisor.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;
import static com.kh.zoomin.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.recruit.member.RecruitMember;
import com.kh.zoomin.supervisor.model.dao.SupervisorDao;

public class SupervisorService {
	
	private SupervisorDao supervisorDao = new SupervisorDao();
	
	//DQL : select * from applicantMember
	public List<ApplicantMember> findApplicantMemberAll(){
		Connection conn = getConnection();
		List<ApplicantMember> applicantMemberList = supervisorDao.findApplicantMemberAll(conn);
		close(conn);
		return applicantMemberList;
	}
	
	//DQL : select * from recruitMember
	public List<RecruitMember> findRecruitMemberAll(){
		Connection conn = getConnection();
		List<RecruitMember> recruitMemberList = supervisorDao.findRecruitMemberAll(conn);
		close(conn);
		return recruitMemberList;
	}

	//DQL : select * from applicantMember where # like ?
	public List<ApplicantMember> findApplicantMemberLike(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ApplicantMember> applicantMemberList = supervisorDao.findApplicantMemberLike(conn, param);
		close(conn);
		return applicantMemberList;
	}

	//DQL : select * from recruitMember where # like ?
	public List<RecruitMember> findRecruitMemberLike(Map<String, Object> param) {
		Connection conn = getConnection();
		List<RecruitMember> recruitMemberList = supervisorDao.findRecruitMemberLike(conn, param);
		close(conn);
		return recruitMemberList;
	}

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
	
	
}
