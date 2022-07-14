package com.kh.zoomin.applicant.companyReviewBoard.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import static com.kh.zoomin.common.JdbcTemplate.*;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.salaryReviewBoard.model.exception.CompanyReviewException;

public class CompanyReviewDao {

	private Properties prop = new Properties();

	public CompanyReviewDao() {
		String filename = CompanyReviewDao.class.getResource("/sql/zoomin/applicant/applicant-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertCompanyReview(Connection conn, CompanyReview companyReview) {
		PreparedStatement pstmt = null;
		int result = 0;
		// insertReview = insert into COMPANY_REVIEW values (seq_no, ?, ?, ?, ?, ?,?,?,?,?,?, default)
		String sql = prop.getProperty("insertCompanyReview");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, companyReview.getUid());
			pstmt.setString(2, companyReview.getCompanyNo());
			pstmt.setInt(3, companyReview.getCategoryNumber());
			pstmt.setString(4, companyReview.getContent());
			pstmt.setInt(5, companyReview.getStars());
			pstmt.setInt(6, companyReview.getWorkLifeBalance());
			pstmt.setInt(7, companyReview.getLevelUp());
			pstmt.setInt(8, companyReview.getWorkIntensity());
			pstmt.setInt(9, companyReview.getPotential());
			pstmt.setInt(10, companyReview.getSalarySatisfaction());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new CompanyReviewException("리뷰 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
}
