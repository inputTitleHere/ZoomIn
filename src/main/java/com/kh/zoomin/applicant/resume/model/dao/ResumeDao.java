package com.kh.zoomin.applicant.resume.model.dao;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.applicant.resume.model.exception.ResumeException;


public class ResumeDao {

	private Properties prop = new Properties();
	
	public ResumeDao() {
		String filename = ResumeDao.class.getResource("/sql/resume/resume.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public int insertResume(Connection conn, Resume resume) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertResume"); 
		//미완성 커리값 대입
		// insert into tb_resume values(?*16)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resume.getId());
			pstmt.setString(2, resume.getName());
			pstmt.setString(3, resume.getBirthday());
			pstmt.setString(4, resume.getGender().name());
			pstmt.setString(5, resume.getEmail());
			pstmt.setString(6, resume.getPhoneNum());
			pstmt.setString(7, resume.getAddress());
			pstmt.setString(8, resume.getSchoolType().name());
			pstmt.setString(9, resume.getSchoolName());
			pstmt.setString(10, resume.getSchoolStatus().name());
			pstmt.setString(11, resume.getMajorName());
			pstmt.setDouble(12, resume.getGrade());
			pstmt.setDouble(13, resume.getTotalPoint());
			pstmt.setString(14, resume.getCompanyName());
			pstmt.setInt(15, resume.getCareer());
			pstmt.setString(16, resume.getCareerStatus() != null ? resume.getCareerStatus().name() : null);
			
			result = pstmt.executeUpdate();
		}
		catch(SQLException e){
			throw new ResumeException("이력서 등록 오류",e);
		}
		finally {
			close(pstmt);
		}
		return result;
	}
}
