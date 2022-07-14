package com.kh.zoomin.applicant.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;
import java.sql.Connection;

import com.kh.zoomin.applicant.member.model.dao.ApplicantDao;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;

public class ApplicantService {

	public ApplicantMember findById(String memberId) {
		Connection conn = getConnection();
		ApplicantDao ad = new ApplicantDao();
		ApplicantMember amember = ad.findById(conn, memberId);
		close(conn);
		return amember;
	}



}
