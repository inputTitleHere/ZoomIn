package com.kh.zoomin.recruit.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;

import com.kh.zoomin.recruit.member.model.dao.RecruitDao;
import com.kh.zoomin.recruit.member.RecruitMember;

public class RecruitService {
	private RecruitDao rd = new RecruitDao();

	//로그인 및 아이디 중복체크 검사 
	public RecruitMember findrecruId(String id) {
		Connection conn = getConnection();
		RecruitMember rmember = rd.findrecruId(conn, id);
		close(conn);
		return rmember;

	}

	//회원가입
	public int addRecruiter(RecruitMember rmember) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = rd.addRecruiter(conn, rmember);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


}
