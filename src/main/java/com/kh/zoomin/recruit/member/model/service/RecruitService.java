package com.kh.zoomin.recruit.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.close;
import static com.kh.zoomin.common.JdbcTemplate.commit;
import static com.kh.zoomin.common.JdbcTemplate.getConnection;
import static com.kh.zoomin.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.zoomin.member.exception.MemberException;
import com.kh.zoomin.recruit.member.model.dao.RecruitDao;
import com.kh.zoomin.recruit.member.model.dto.RecruitMember;

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

	public int deleteRecruiter(String id) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = rd.deleteRecruiter(conn, id);
			//연결 후 아이디를 삭제
			//만약 결과가 없을때 회원존재x 
			if(result == 0)
				throw new MemberException("해당 회원은 존재하지 않습니다.");
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updatePwRecruiter(String id, String nextPw) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = rd.updatePwRecruiter(conn, id, nextPw);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<RecruitMember> loadPassword1234() {
		Connection conn = getConnection();
		List<RecruitMember> result=null;
		result=rd.loadPassword1234(conn);
		close(conn);
		return result;
	}

	public int setPassword1234(List<RecruitMember> rmember) {
		int result=0;
		Connection conn = getConnection();
		try {
			result = rd.setPassword1234(rmember,conn);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);			
		}
		return result;
	}


}
