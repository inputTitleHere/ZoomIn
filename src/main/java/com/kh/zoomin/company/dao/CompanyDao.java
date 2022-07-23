package com.kh.zoomin.company.dao;

import static com.kh.zoomin.common.JdbcTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.zoomin.applicant.companyReviewBoard.model.dao.CompanyReviewDao;
import com.kh.zoomin.applicant.companyReviewBoard.model.dto.CompanyReview;
import com.kh.zoomin.applicant.companyReviewBoard.model.exception.CompanyReviewException;
import com.kh.zoomin.company.dto.Company;
import com.kh.zoomin.company.exception.CompanyException;

public class CompanyDao {

	private Properties prop = new Properties();
	
	public CompanyDao() {
		String filename = CompanyDao.class.getResource("/sql/zoomin/company/company-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (Exception e) {
			throw new CompanyException(" properties failed to load", e);
		}
	}
	
	public List<Company> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Company> list = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String companyNo = rset.getString("company_no");
				String companyName = rset.getString("company_name");
				String companyInfo = rset.getString("company_info");
				Company company = new Company(companyNo, companyName, companyInfo);
				list.add(company);
			}
		} catch (SQLException e) {
			throw new CompanyReviewException("회사 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Company getCompanyByNo(Connection conn, String companyNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Company company = null;
		// select * from company_table where company_no = ?
		String sql = prop.getProperty("getCompanyByNo");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyNo);
			rset= pstmt.executeQuery();
			while(rset.next()) {
				company = handleCompany(rset);
			}
			
		} catch (SQLException e) {
			throw new CompanyReviewException("회사 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return company;
	}

	private Company handleCompany(ResultSet rset) throws SQLException {
		String companyNo = rset.getString("company_no");
		String companyName = rset.getString("company_name");
		String companyInfo = rset.getString("company_info");
		Company company = new Company(companyNo, companyName, companyInfo);
		return company;
	}

}
