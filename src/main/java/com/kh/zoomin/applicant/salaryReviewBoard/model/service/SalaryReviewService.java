package com.kh.zoomin.applicant.salaryReviewBoard.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;

import com.kh.zoomin.applicant.salaryReviewBoard.model.dao.SalaryReviewDao;
import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview;

public class SalaryReviewService {

	private SalaryReviewDao salaryReviewDao = new SalaryReviewDao();
	
	public int insertSalaryReviewService(SalaryReview salaryReview) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = salaryReviewDao.insertSalaryReview(conn, salaryReview);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
}
