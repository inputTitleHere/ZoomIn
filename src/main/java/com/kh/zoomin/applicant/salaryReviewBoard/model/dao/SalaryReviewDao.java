package com.kh.zoomin.applicant.salaryReviewBoard.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import static com.kh.zoomin.common.JdbcTemplate.*;
import com.kh.zoomin.applicant.companyReviewBoard.model.dao.CompanyReviewDao;
import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview;
import com.kh.zoomin.applicant.salaryReviewBoard.model.exception.SalaryReviewException;

public class SalaryReviewDao {

	private Properties prop = new Properties();
	
	public SalaryReviewDao() {
		String filename = CompanyReviewDao.class.getResource("/sql/zoomin/applicant/salaryreview-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertSalaryReview(Connection conn, SalaryReview salaryReview) {
		PreparedStatement pstmt = null;
		int result = 0;
		// insertSalaryReview = insert into salary_review values (seq_no, ?, ?, ?, ?, ?, ?, default)
		String sql = prop.getProperty("insertSalaryReview");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, salaryReview.getUid());
			pstmt.setString(2, salaryReview.getCompanyNo());
			pstmt.setInt(3, salaryReview.getCategoryNumber());
			pstmt.setInt(4, salaryReview.getSalary());
			pstmt.setInt(5, salaryReview.getWorkYear());
			pstmt.setString(6, salaryReview.getJobPosition());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new SalaryReviewException("연봉 리뷰 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

}
