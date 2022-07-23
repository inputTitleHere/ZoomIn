package com.kh.zoomin.recruit.board.service;

import com.kh.zoomin.applicant.resume.model.dao.ResumeDao;
import com.kh.zoomin.applicant.resume.model.dto.Resume;
import com.kh.zoomin.common.ZoominUtils;
import com.kh.zoomin.recruit.board.dao.RecruitBoardDao;
import com.kh.zoomin.recruit.board.dto.RecruitBoard;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class RecruitBoardService {

	private RecruitBoardDao rbd = new RecruitBoardDao();

	public List<RecruitBoard> loadRecruitBoardHeaders(Map<String, Object> param) {
		Connection conn = getConnection();
		List<RecruitBoard> result=null;
		result=rbd.loadRecruitBoardHeaders(param, conn);
		close(conn);
		return result;
	}

	/**
	 * DB의 RECRUIT_BOARD에서 모든 채용글의 개수를 세어옵니다. 
	 * @return
	 */
	public int totalRecruitBoardCount() {
		int result=0;
		Connection conn = getConnection();
		result=rbd.totalRecruitBoardCount(conn);
		close(conn);
		return result;
	}

	public RecruitBoard viewRecruitBoard(int boardNo) {
		RecruitBoard result=null;
		Connection conn = getConnection();
		result=rbd.viewRecruitBoard(boardNo, conn);
		
		result.setTitle(ZoominUtils.escapeXml(result.getTitle()));
//		result.setContent(ZoominUtils.convertLineFeedToBr(ZoominUtils.escapeXml(result.getContent()))); // XML기호 해체 및 개행문자 HTML형식으로 처리.(컨텍스트 스타릴링 활용을 위해 보류)
		
		close(conn);
		return result;
	}

	public List<RecruitBoard> loadRecruiterBoard(Map<String, Object> param) {
		List<RecruitBoard> result=null;
		Connection conn = getConnection();		
		result = rbd.loadRecruiterBoard(param,conn);
		close(conn);
		return result;
	}

	public int insertRecruitBoard(RecruitBoard rb) {
		Connection conn = getConnection();
		int result=0;
		try {
			result=rbd.insertRecruitBoard(rb, conn);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int deleteRecruitBoard(int no) {
		int result=0;
		Connection conn=getConnection();
		try{
			result=rbd.deleteRecruitBoard(no, conn);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public RecruitBoard findBoardByNo(int no) {
		RecruitBoard result=null;
		Connection conn = getConnection();
		result=rbd.findBoardByNo(no,conn);
		close(conn);
		return result;
	}

	public int updateRecruitBoard(RecruitBoard rb) {
		int result=0;
		Connection conn = getConnection();
		try {
			result = rbd.updateRecruitBoard(rb,conn);			
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);			
		}
		
		
		return result;
	}

	public boolean isRecruitBoardFaved(int boardNo, int uid) {
		boolean result=false;
		Connection conn =getConnection();
		result=rbd.isRecruitBoardFaved(boardNo, uid, conn);
		
		close(conn);
		return result;
	}

	public boolean isRecruitBoardEnrolled(int boardNo, int uid) {
		boolean result=false;
		Connection conn =getConnection();
		result=rbd.isRecruitBoardEnrolled(boardNo, uid, conn);
		
		close(conn);
		return result;
	}

	public int setFavourite(int boardNo, int applicantUid) {
		int result =0;
		Connection conn = getConnection();
		try {
			result = rbd.setFavourite(boardNo, applicantUid, conn);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);			
		}
		return result;
	}

	public int removeFavourite(int boardNo, int applicantUid) {
		int result=0;
		Connection conn =getConnection();
		try {
			result=rbd.removeFavourite(boardNo, applicantUid, conn);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);			
		}
		return result;
	}

	public int setEnroll(int boardNo, int applicantUid) {
		int result =0;
		Connection conn = getConnection();
		try {
			result = rbd.setEnroll(boardNo, applicantUid, conn);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);			
		}
		return result;
	}

	public int removeEnroll(int boardNo, int applicantUid) {
		int result=0;
		Connection conn =getConnection();
		try {
			result=rbd.removeEnroll(boardNo, applicantUid, conn);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);			
		}
		return result;
	}

	public List<Resume> loadEnrolledList(int boardNo) {
		List<Resume> result=null;
		Connection conn = getConnection();
		result = new ResumeDao().loadEnrolledList(boardNo, conn);
		close(conn);
		return result;
	}

	public List<RecruitBoard> loadRecruitBoardByCompanyNo(String companyNo) {
		List<RecruitBoard> result=null;
		Connection conn = getConnection();
		result=rbd.loadRecruitBoardByCompanyNo(companyNo, conn);
		close(conn);
		return result;
	}
	
	// ====================== 메소드 영역 ============================//
	
	
	

}

