package com.kh.zoomin.applicant.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;
import java.sql.Connection;

import com.kh.zoomin.applicant.member.model.dao.ApplicantDao;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;

public class ApplicantService {
	private ApplicantDao ad = new ApplicantDao();

	public ApplicantMember findAppliId(String id, String password) {
		Connection conn = getConnection();
		ApplicantMember amember = ad.findAppliId(conn, id, password);
		close(conn);
		return amember;
	}

	public static int insertApplicant(ApplicantMember amember) {
		
		return 0;
	}


}
