package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class RecLog {
	int no;
	int boardNo;
	int uid;
	String companyNo;
	String title;
	String log;
	Date logDate;
	
	public RecLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecLog(int no, int boardNo, int uid, String companyNo, String title, String log, Date logDate) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.uid = uid;
		this.companyNo = companyNo;
		this.title = title;
		this.log = log;
		this.logDate = logDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
