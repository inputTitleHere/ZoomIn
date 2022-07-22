package com.kh.zoomin.applicant.resume.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.zoomin.applicant.resume.model.dao.ResumeDao;
import com.kh.zoomin.applicant.resume.model.dto.Resume;

public class ResumeService {
	
	private ResumeDao resumeDao = new ResumeDao();
	/**
	 * DML요청 - service
	 * 1. Connection객체 생성
	 * 2. Dao요청 & Connection 전달
	 * 3. 트랜잭션처리(정상처리 commit, 예외발생시 rollback)
	 * 4. Connection객체 반환
	 * 
	 * @param member
	 * @return
	 */
	
	public int insertResume(Resume resume) {
		
		int result = 0;
		Connection conn = getConnection();
		try {
			result = resumeDao.insertResume(conn,resume);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	}
	
	public Resume findByResume(int uid) {
		Connection conn = getConnection();
		Resume resume = resumeDao.findByResume(conn,uid);
		close(conn);
		return resume;
	}
	
	public int deleteResume(int uid) {

		int result= 0;
		Connection conn = getConnection();
		try {
			result = resumeDao.deleteResume(conn,uid);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	}
	
	public int updateResume(Resume resume) {
		int result= 0;
		Connection conn = getConnection();
		try {
			result = resumeDao.updateResume(conn,resume);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	}
	// 백승윤 START
	public List<Resume> findResumeByCategory(Map<String, Object> param) {
		List<Resume> result=null;
		Connection conn = getConnection();
		result=resumeDao.findResumeByCategory(param,conn);
		close(conn);
		return result;
	}
	// 백승윤 END

	public int findResumeCountByCategory(int category) {
		int result=0;
		Connection conn = getConnection();
		result=resumeDao.findResumeCountByCategory(category,conn);
		close(conn);
		return result;
	}
}
