package com.kh.zoomin.applicant.resume.model.dao;

import static com.kh.zoomin.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;



import com.kh.zoomin.applicant.resume.model.dto.Gender;
import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.applicant.resume.model.dto.SchoolType;
import com.kh.zoomin.applicant.resume.model.dto.Status;
import com.kh.zoomin.applicant.resume.model.exception.ResumeException;
import com.kh.zoomin.recruit.board.exception.RecruitBoardException;


public class ResumeDao {

	private Properties prop = new Properties();
	
	public ResumeDao() {
		String filename = ResumeDao.class.getResource("/sql/zoomin/applicant/applicant-query.properties").getPath();
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
		double grade = rset.getDouble("grade");
		double totalPoint = rset.getDouble("total_point");
		
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
	// 백승윤 START
	/**
	 * 구인자가 자기 채용글에 지원한 구직자들의 리스트를 추출하는 쿼리입니다. 
	 * @param boardNo
	 * @param conn
	 * @return
	 */
	public List<Resume> loadEnrolledList(int boardNo, Connection conn) {
		List<Resume> result = new ArrayList<Resume>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		select * from RESUME where "uid" in (select "uid" from ENROLL_TABLE where recruit_board_no=?);
		String sql = prop.getProperty("loadEnrolledList");
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				result.add(handleResumeResultSet(rset));
			}
		}catch (SQLException e) {
			throw new RecruitBoardException("지원자 리스트 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}


	public List<Resume> findResumeByCategory(Map<String, Object> param, Connection conn) {
		List<Resume> result=new ArrayList<Resume>();
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		// select * from (select row_number() over(order by resume_no desc) rnum, c.* from RESUME c where category_number=?) b where b.rnum between ? and ? order by b.rnum asc
		String sql = prop.getProperty("findResumeByCategory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (int)param.get("category"));
			pstmt.setInt(2, (int)param.get("start"));
			pstmt.setInt(3, (int)param.get("end"));
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				result.add(handleResumeResultSet(rset));
			}
			
		}catch(SQLException e) {
			throw new ResumeException("이력서 분야별 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}


	public int findResumeCountByCategory(int category, Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rset= null;
//		select count(*) from Resume where category_number=?
		String sql = prop.getProperty("getResumeCountByCategory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, category);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				result=rset.getInt(1);
			}
		}catch(SQLException e) {
			throw new ResumeException("카테고리별 이력서 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	// 백승윤 END

}
