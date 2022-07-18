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
	
	
}
