package com.kh.zoomin.recruit.member.model.dao;

import static com.kh.zoomin.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.zoomin.recruit.member.model.dao.RecruitDao;
import com.kh.zoomin.recruit.member.model.dto.RecruitMember;
import com.kh.zoomin.member.exception.MemberException;

public class RecruitDao {
	
	private Properties prop = new Properties();
	
	// member-query.properties에서 불러오기
	public RecruitDao() {
		String filename = RecruitDao.class.getResource("/sql/zoomin/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RecruitMember findrecruId(Connection conn, String id) {
		RecruitMember rmember = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("findrecruId");
		// findrecruId(sql) = select * from recruit_member where id = ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			System.out.println("rset = " + rset);
			while (rset.next()) { 
				//다음행이 있을때 rset.next()에 의해 다음행 이동
				rmember = handleMemberResultSet(rset);
			}
			//System.out.println("rmember@dao = " + rmember);

		} catch (SQLException e) {
			throw new MemberException("아이디가 존재하지 않습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return rmember;
	}

	private RecruitMember handleMemberResultSet(ResultSet rset) throws SQLException {
		int uid = rset.getInt("uid");
		String companyNo = rset.getString("company_no");
		String name = rset.getString("name");
		String id = rset.getString("id");
		String password = rset.getString("password");
		String email = rset.getString("email");
		String isSupervisor = rset.getString("supervisor");
		Boolean supervisor = false;
		if ("Y".equals(isSupervisor)) {
			supervisor = true;
		} 
		Date regDate = rset.getDate("reg_date");
		return new RecruitMember(uid, companyNo, name, id, password, email, supervisor, regDate);

	}

	public int addRecruiter(Connection conn, RecruitMember rmember) {
		PreparedStatement pstmt = null;
		int result = 0; 
		String sql = prop.getProperty("addRecruiter");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rmember.getCompanyNo());
			pstmt.setString(2, rmember.getName());
			pstmt.setString(3, rmember.getId());
			pstmt.setString(4, rmember.getPassword());
			pstmt.setString(5, rmember.getEmail());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원가입 오류입니다.", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}



}
