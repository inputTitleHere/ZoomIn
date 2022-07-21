package com.kh.zoomin.applicant.salaryReviewBoard.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
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

	public List<SalaryReview> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<SalaryReview> list = salaryReviewDao.findAll(conn, param);
		close(conn);
		return list;
	}

	public int getTotalContent() {
		Connection conn = getConnection();
		int totalContent = salaryReviewDao.getTotalContent(conn); 
		close(conn);
		return totalContent;
	}

	public SalaryReview findBySalaryReviewNo(int no) {
		Connection conn = getConnection();
		SalaryReview salaryReview = null;
		try {
			// 회사 리뷰테이블에서 조회
			salaryReview = salaryReviewDao.findBySalaryReviewNo(conn, no);
			commit(conn);
			
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return salaryReview;
	}
}
