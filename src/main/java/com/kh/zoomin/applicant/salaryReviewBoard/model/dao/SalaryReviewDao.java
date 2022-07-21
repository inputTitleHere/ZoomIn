package com.kh.zoomin.applicant.salaryReviewBoard.model.dao;

import java.beans.PropertyChangeSupport;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import static com.kh.zoomin.common.JdbcTemplate.*;
import com.kh.zoomin.applicant.companyReviewBoard.model.dao.CompanyReviewDao;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.exception.CompanyReviewException;
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

	public List<SalaryReview> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SalaryReview> list = new ArrayList<>();
		// select * from (select row_number() over(order by reg_date desc) rnum, s.* from salary_review s) a where rnum between 1 and 5;
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SalaryReview salaryReview = handleSalaryReviewResultSet(rset);
				list.add(salaryReview);
			}
		} catch (SQLException e) {
			throw new SalaryReviewException("리뷰 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	private SalaryReview handleSalaryReviewResultSet(ResultSet rset) throws SQLException {
		int no = rset.getInt("no");
		int uid = rset.getInt("uid");
		String companyNo = rset.getString("company_no");
		int categoryNumber = rset.getInt("category_number");
		int salary = rset.getInt("salary");
		int workYear = rset.getInt("work_year");
		String jobPosition = rset.getString("job_position");
		Date regDate = rset.getDate("reg_date");
		return new SalaryReview(no, uid, companyNo, categoryNumber, salary, workYear, jobPosition, regDate);
	}

	public int getTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContent");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new CompanyReviewException("총 리뷰 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public SalaryReview findBySalaryReviewNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		SalaryReview salaryReview= null;
		// select * from salary_review where no = ?
		String sql = prop.getProperty("findBySalaryReviewNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				salaryReview = handleSalaryReviewResultSet(rset);
			}
		} catch (SQLException e) {
			throw new CompanyReviewException("연봉 리뷰 1건 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return salaryReview;
	}

}
