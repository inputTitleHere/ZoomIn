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
import com.kh.zoomin.member.exception.MemberException;


public class ApplicantDao {

	private Properties prop = new Properties();

	// member-query.properties에서 불러오기
	public ApplicantDao() {
		String filename = ApplicantDao.class.getResource("/sql/zoomin/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// 로그인기능-로그인 아이디 인식
	public ApplicantMember findAppliId(String id, String password) {
		ApplicantMember amember = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//로그인 여부
		String messeage = new String();
		String page = new String();
		String sql = prop.getProperty("findAppliId");
		// (sql) = select * from applicant_member where id = ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(3, id);
			rset = pstmt.executeQuery();
			
			while (rset.next()) { 
				//다음행이 있을때 rset.next()에 의해 다음행 이동
				amember = handleMemberResultSet(rset);
			}

		} catch (SQLException e) {
			throw new MemberException("아이디가 존재하지 않습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return amember;
	}

	private ApplicantMember handleMemberResultSet(ResultSet rset) throws SQLException {
		int uid = rset.getInt("uid");
		String name = rset.getString("name");
		String id = rset.getString("id");
		String password = rset.getString("password");
		String phone = rset.getString("phone");
		String email = rset.getString("email");
		Date regDate = rset.getDate("reg_date");
		return new ApplicantMember(uid, name, id, password, phone, email, regDate);
	}


}
