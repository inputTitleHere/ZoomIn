package com.kh.zoomin.applicant.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;
import java.sql.Connection;

import com.kh.zoomin.applicant.member.model.dao.ApplicantDao;
import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;

public class ApplicantService {

	public ApplicantMember findAppliId(String id, String password) {
		Connection conn = getConnection();
		ApplicantDao ad = new ApplicantDao();
		ApplicantMember amember = ad.findAppliId(id, password);
		close(conn);
		return amember;
	}


}
