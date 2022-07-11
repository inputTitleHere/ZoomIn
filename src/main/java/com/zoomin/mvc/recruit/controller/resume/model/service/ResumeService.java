package resume.model.service;

import java.sql.Connection;
import static common.JdbcTemplate.*;
import resume.model.dao.ResumeDao;
import resume.model.dto.Resume;

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

}
