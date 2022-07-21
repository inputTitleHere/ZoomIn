package com.kh.zoomin.applicant.resume.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;

import com.kh.zoomin.applicant.resume.model.dao.ResumeDao;
import com.kh.zoomin.applicant.resume.model.dto.Resume;

public class ResumeService {
	
	private ResumeDao ResumeDao = new ResumeDao();
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
			result = ResumeDao.insertResume(conn,resume);
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
		Resume resume = ResumeDao.findByResume(conn,uid);
		close(conn);
		return resume;
	}
	
	public int deleteResume(int uid) {

		int result= 0;
		Connection conn = getConnection();
		try {
			result = ResumeDao.deleteResume(conn,uid);
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
			result = ResumeDao.updateResume(conn,resume);
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

}
