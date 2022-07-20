package com.kh.zoomin.applicant.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.close;
import static com.kh.zoomin.common.JdbcTemplate.commit;
import static com.kh.zoomin.common.JdbcTemplate.rollback;
import static com.kh.zoomin.common.JdbcTemplate.getConnection;

import java.sql.Connection;

import com.kh.zoomin.applicant.member.model.dao.ApplicantDao;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;

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



}
