package com.kh.zoomin.applicant.companyReviewBoard.Service;

import java.sql.Connection;
import static com.kh.zoomin.common.JdbcTemplate.*;
import com.kh.zoomin.applicant.companyReviewBoard.model.dao.CompanyReviewDao;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;


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

	
}
