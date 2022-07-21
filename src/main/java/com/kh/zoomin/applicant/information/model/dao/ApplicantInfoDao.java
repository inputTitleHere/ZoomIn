package com.kh.zoomin.applicant.information.model.dao;

import static com.kh.zoomin.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.zoomin.applicant.information.model.dto.CompanyInfo;
import com.kh.zoomin.applicant.resume.model.exception.ResumeException;


public class ApplicantInfoDao {
	private Properties prop = new Properties();

	
	public ApplicantInfoDao() {
		String filename = ApplicantInfoDao.class.getResource("/sql/zoomin/applicant/applicant-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<CompanyInfo> findByCompany(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CompanyInfo> companyInfo = new ArrayList<CompanyInfo>();
		String sql = prop.getProperty("findByCompany");	
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				companyInfo.add(handleCompanyResultSet(rset));
			}
			
		} catch (SQLException e) {
			throw new ResumeException("회사정보 출력 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return companyInfo;
	}


	private  CompanyInfo handleCompanyResultSet(ResultSet rset) throws SQLException {
		String companyNo = rset.getString("company_no");
		String companyName = rset.getString("company_name");
		String companyLogo = rset.getString("company_logo");
		String companyInfo = rset.getString("company_info");
		
		
		return new CompanyInfo(companyNo, companyName, companyLogo, companyInfo);
	}

	public int totalCompanyCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String sql = prop.getProperty("totalCompanyCount");	
	
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				count = rset.getInt("company_cnt");				
			}
		} catch (SQLException e) {
			throw new ResumeException("회사 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
}
