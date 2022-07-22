package com.kh.zoomin.applicant.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.close;
import static com.kh.zoomin.common.JdbcTemplate.commit;
import static com.kh.zoomin.common.JdbcTemplate.getConnection;
import static com.kh.zoomin.common.JdbcTemplate.rollback;

import java.sql.Connection;

import com.kh.zoomin.applicant.member.model.dao.ApplicantDao;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.member.exception.MemberException;

public class ApplicantService {
	private ApplicantDao ad = new ApplicantDao();

	public ApplicantMember findAppliId(String id) {
		Connection conn = getConnection();
		ApplicantMember amember = ad.findAppliId(conn, id);
		close(conn);
		return amember;
	}

	//회원가입 (회원추가)
	public int addApplicant(ApplicantMember amember) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ad.addApplicant(conn, amember);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteMember(String id) {
		Connection conn = null; 
		int result = 0;
		try {
			conn = getConnection();
			result = ad.deleteApplicant(conn, id);
			if(result == 0)
				throw new MemberException("해당 회원은 존재하지 않습니다.");
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateApplicant(ApplicantMember amember) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ad.updateApplicant(conn, amember);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}

	public int updatePwApllicant(String id, String nextPw) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ad.updatePwApplicant(conn, id, nextPw);
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
