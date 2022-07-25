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

import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReview;
import com.kh.zoomin.applicant.salaryReviewBoard.model.dto.SalaryReviewExt;
import com.kh.zoomin.applicant.salaryReviewBoard.model.exception.SalaryReviewException;

public class SalaryReviewDao {

	private Properties prop = new Properties();
	
	public SalaryReviewDao() {
		String filename = SalaryReviewDao.class.getResource("/sql/zoomin/applicant/salaryreview-query.properties").getPath();
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
		// 백승윤 - 7월 24일 수정
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SalaryReview> list = new ArrayList<>();
		// select * from (select row_number() over(order by reg_date desc) rnum, s.*,c.company_name from salary_review s join company_table c on s.company_no=c.company_no) a where rnum between ? and ?
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SalaryReview salaryReview = handleSalaryReviewResultSet(rset);
				String companyName=rset.getString("company_name");
				SalaryReviewExt sre = new SalaryReviewExt(salaryReview, companyName);
				list.add(sre);
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
			throw new SalaryReviewException("총 리뷰 조회 오류", e);
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
			throw new SalaryReviewException("연봉 리뷰 1건 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return salaryReview;
	}

	public int deleteSalaryReview(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		// delete from salary_review where no = ?
		String sql = prop.getProperty("deleteSalaryReview");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new SalaryReviewException("연봉 리뷰 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateSalaryReview(Connection conn, SalaryReviewExt salaryReview) {
		PreparedStatement pstmt = null;
		int result = 0;
		// update salary_review set category_number = ?, salary = ?, work_year = ?, job_position = ? where no = ?
		String sql = prop.getProperty("updateSalaryReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, salaryReview.getCategoryNumber());
			pstmt.setInt(2, salaryReview.getSalary());
			pstmt.setInt(3, salaryReview.getWorkYear());
			pstmt.setString(4, salaryReview.getJobPosition());
			pstmt.setInt(5, salaryReview.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new SalaryReviewException("연봉 리뷰 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 백승윤 START
	public List<SalaryReview> findByCompanyNo(Map<String, Object> param, Connection conn) {
		List<SalaryReview> result=new ArrayList<SalaryReview>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		// select * from (select row_number() over(order by reg_date desc) rnum, * from salary_review where company_no=?) where rnum between ? and ?
		String sql=prop.getProperty("findByCompanyNo");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("companyNo"));
			pstmt.setInt(2, (int)param.get("salaryStart"));
			pstmt.setInt(3, (int)param.get("salaryEnd"));
			rset=pstmt.executeQuery();
			while(rset.next()) {
				result.add(handleSalaryReviewResultSet(rset));
			}
		}catch (SQLException e) {
			throw new SalaryReviewException("사업자등록번호에 따른 연봉리뷰 조회 오류", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int getTotalContent(String companyNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
//		select count(*) from salary_review where company_no=?
		String sql = prop.getProperty("getTotalContentByCompanyNo");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new SalaryReviewException("총 리뷰 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	// 백승윤 END

}
