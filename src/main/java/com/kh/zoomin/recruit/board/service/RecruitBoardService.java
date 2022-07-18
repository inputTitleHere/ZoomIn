package com.kh.zoomin.recruit.board.service;

import com.kh.zoomin.recruit.board.dao.RecruitBoardDao;
import com.kh.zoomin.recruit.board.dto.RecruitBoard;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class RecruitBoardService {

	private RecruitBoardDao rbd = new RecruitBoardDao();

	public List<RecruitBoard> loadRecruitBoardHeaders(Map<String, Object> param) {
		Connection conn = getConnection();
		List<RecruitBoard> result=null;
		result=rbd.loadRecruitBoardHeaders(param, conn);
		
		return result;
	}
	
	// ====================== 메소드 영역 ============================//
	
	
	
	
}