package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class SalLog {
	int no;
	int uid;
	int boardNo;
	String companyNo;
	String log;
	Date logDate;
	
	public SalLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalLog(int no, int uid, int boardNo, String companyNo, String log, Date logDate) {
		super();
		this.no = no;
		this.uid = uid;
		this.boardNo = boardNo;
		this.companyNo = companyNo;
		this.log = log;
		this.logDate = logDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	
}
