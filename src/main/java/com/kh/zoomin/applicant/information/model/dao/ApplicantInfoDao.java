package com.kh.zoomin.applicant.information.model.dao;

import static com.kh.zoomin.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;



import com.kh.zoomin.applicant.resume.model.dto.Gender;
import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.applicant.resume.model.dto.SchoolType;
import com.kh.zoomin.applicant.resume.model.dto.Status;
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

	
	public int insertResume(Connection conn, Resume resume) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertResume"); 
		//미완성 커리값 대입
		// insert into resume values(?*13)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,resume.getUid());
			pstmt.setInt(2, resume.getCategoryNumber());
			pstmt.setString(3, resume.getName());
			pstmt.setDate(4, new java.sql.Date(resume.getBirthday().getTime()));
			pstmt.setString(5, resume.getGender().name());
			pstmt.setString(6, resume.getAddress());
			pstmt.setString(7, resume.getSchoolType().name());
			pstmt.setString(8, resume.getSchoolName());
			pstmt.setString(9, resume.getSchoolStatus().name());
			pstmt.setString(10, resume.getMajorName());
			pstmt.setDouble(11, resume.getGrade());
			pstmt.setDouble(12, resume.getTotalPoint());
			
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


	public Resume findByResume(Connection conn, int uid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Resume resume = null;
		String sql = prop.getProperty("findByResume");	
		// select * from resume where "uid"= ?
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				resume = handleResumeResultSet(rset);
			}
			
		} catch (SQLException e) {
			throw new ResumeException("회원 아이디 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return resume;
	}


	private Resume handleResumeResultSet(ResultSet rset) throws SQLException {
		int uid = rset.getInt("uid");
		int categoryNumber = rset.getInt("category_number");
		String name = rset.getString("name");
		Date birthday = rset.getTimestamp("birthday");
		Gender gender = Gender.valueOf(rset.getString("gender"));
		String address = rset.getString("address");
		SchoolType schoolType = SchoolType.valueOf(rset.getString("school_type"));
		String schoolName = rset.getString("school_name");
		Status schoolStatus = Status.valueOf(rset.getString("school_status"));
		String majorName = rset.getString("major_name");
		int grade = rset.getInt("grade");
		int totalPoint = rset.getInt("total_point");
		
		return new Resume(uid, categoryNumber, name, birthday,
				gender, address, schoolType, schoolName, schoolStatus, majorName, grade, totalPoint);
	}


	public int deleteResume(Connection conn, int uid) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		String sql = prop.getProperty("deleteResume");	
		// delete from resume where "uid"= ?
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ResumeException("이력서 삭제 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updateResume(Connection conn, Resume resume) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateResume"); 
		//미완성 커리값 대입
		// update resume set category_number = ?, name = ?, birthday = ?, gender = ?, address = ?, school_type = ? ,
//					school_name =?, school_status = ?, major_name = ?, grade = ?, total_point = ? where "uid"= ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, resume.getCategoryNumber());
			pstmt.setString(2, resume.getName());
			pstmt.setDate(3, new java.sql.Date(resume.getBirthday().getTime()));
			pstmt.setString(4, resume.getGender().name());
			pstmt.setString(5, resume.getAddress());
			pstmt.setString(6, resume.getSchoolType().name());
			pstmt.setString(7, resume.getSchoolName());
			pstmt.setString(8, resume.getSchoolStatus().name());
			pstmt.setString(9, resume.getMajorName());
			pstmt.setDouble(10, resume.getGrade());
			pstmt.setDouble(11, resume.getTotalPoint());
			pstmt.setInt(12,resume.getUid());
			
			result = pstmt.executeUpdate();
		}
		catch(SQLException e){
			throw new ResumeException("이력서 수정 오류",e);
		}
		finally {
			close(pstmt);
		}
		return result;
	}

}
