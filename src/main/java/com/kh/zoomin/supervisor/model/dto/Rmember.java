package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class Rmember {
	int uid;
	String comNo;
	String comName;
	String recruiter;
	String id;
	String email;
	Date regDate;
	
	public Rmember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rmember(int uid, String comNo, String comName, String recruiter, String id, String email, Date regDate) {
		super();
		this.uid = uid;
		this.comNo = comNo;
		this.comName = comName;
		this.recruiter = recruiter;
		this.id = id;
		this.email = email;
		this.regDate = regDate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getComNo() {
		return comNo;
	}

	public void setComNo(String comNo) {
		this.comNo = comNo;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}
