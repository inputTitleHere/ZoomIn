package com.kh.zoomin.applicant.companyReviewBoard.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static com.kh.zoomin.common.JdbcTemplate.*;
import com.kh.zoomin.applicant.companyReviewBoard.model.dao.CompanyReviewDao;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReviewExt;


public class CompanyReviewService {

	private CompanyReviewDao companyReviewDao = new CompanyReviewDao();
	
	public int insertCompanyReview(CompanyReview companyReview) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = companyReviewDao.insertCompanyReview(conn, companyReview);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public CompanyReview findByCompanyReviewNo(int no) {
		Connection conn = getConnection();
		CompanyReview companyReview = null;
		try {
			// 회사 리뷰테이블에서 조회
			companyReview = companyReviewDao.findByCompanyReviewNo(conn, no);
			commit(conn);
			
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return companyReview;
	}

	public int updateCompanyReview(CompanyReviewExt companyReview) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = companyReviewDao.updateCompanyReview(conn, companyReview);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteCompanyReview(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = companyReviewDao.deleteCompanyReview(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<CompanyReview> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<CompanyReview> list = companyReviewDao.findAll(conn, param);
		close(conn);
		return list;
	}

	public int getTotalContent() {
		Connection conn = getConnection();
		int totalContent = companyReviewDao.getTotalContent(conn); 
		close(conn);
		return totalContent;
	}

	public List<CompanyReview> loadCompanyReview(Map<String, Object> param) {
		Connection conn = getConnection();
		List<CompanyReview> companyReview = null;
		companyReview = companyReviewDao.loadCompanyReview(param, conn);
		close(conn);
		return companyReview;
	}

	// 백승윤 START
	public List<CompanyReview> findByCompanyNo(Map<String, Object> param) {
		List<CompanyReview> result=null;
		Connection conn = getConnection();
		result=companyReviewDao.findByCompanyNo(param, conn);
		close(conn);
		return result;
	}

	public int getTotalContent(String companyNo) {
		Connection conn = getConnection();
		int totalContent = companyReviewDao.getTotalContent(companyNo,conn); 
		close(conn);
		return totalContent;		
	}
	// 백승윤 END
	
}
