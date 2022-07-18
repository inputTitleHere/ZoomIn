package com.kh.zoomin.recruit.member.model.service;

import static com.kh.zoomin.common.JdbcTemplate.close;
import static com.kh.zoomin.common.JdbcTemplate.getConnection;

import java.sql.Connection;

import com.kh.zoomin.recruit.member.model.dao.RecruitDao;
import com.kh.zoomin.recruit.member.RecruitMember;

public class RecruitService {

	public RecruitMember findrecruId(String id) {
		Connection conn = getConnection();
		RecruitDao rd = new RecruitDao();
		RecruitMember rmember = rd.findrecruId(conn, id);
		close(conn);
		return rmember;

	}

}
