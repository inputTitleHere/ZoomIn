package com.kh.zoomin.applicant.member.model.dao;

import static com.kh.zoomin.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.zoomin.applicant.member.model.dto.ApplicantMember;
import com.kh.zoomin.applicant.member.model.exception.ApplicantException;

public class ApplicantDao {
	
	private Properties prop = new Properties();

	//member-query.properties에서 불러오기
	public ApplicantDao() {
		String filename = ApplicantDao.class.getResource("/sql/zoomin/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ApplicantMember findById(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ApplicantMember amember = null;
		String sql = prop.getProperty("findById");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				amember = handleApplicantResultSet(rset);
				
			}
		} catch (SQLException e) {
			throw new ApplicantException("회원 아이디 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return amember;
	}

	private ApplicantMember handleApplicantResultSet(ResultSet rset) throws SQLException {
		int uid = rset.getInt("uid");
		String memberName = rset.getString("member_name");
		String memberId = rset.getString("member_id");
		String password = rset.getString("password");
		String phone = rset.getString("phone");
		String email = rset.getString("email");
		Date regDate = rset.getDate("reg_date");
		return new ApplicantMember(uid, memberName, memberId, password, phone, email, regDate);
		
	}

}
