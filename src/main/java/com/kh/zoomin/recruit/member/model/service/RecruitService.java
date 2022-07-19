package com.kh.zoomin.recruit.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;

import com.kh.zoomin.recruit.member.model.dao.RecruitDao;
import com.kh.zoomin.recruit.member.RecruitMember;

public class RecruitService {
	private RecruitDao rd = new RecruitDao();

	public RecruitMember findrecruId(String id, String password) {
		Connection conn = getConnection();
		RecruitMember rmember = rd.findrecruId(conn, id, password);
		close(conn);
		return rmember;

	}


}
