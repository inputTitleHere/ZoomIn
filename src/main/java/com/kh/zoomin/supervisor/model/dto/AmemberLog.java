package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class AmemberLog {
	int no;
	int uid;
	String name;
	String id;
	String phone;
	String email;
	String log;
	Date logDate;
	
	public AmemberLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AmemberLog(int no, int uid, String name, String id, String phone, String email, String log, Date logDate) {
		super();
		this.no = no;
		this.uid = uid;
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.email = email;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "AmemberLog [no=" + no + ", uid=" + uid + ", name=" + name + ", id=" + id + ", phone=" + phone
				+ ", email=" + email + ", log=" + log + ", logDate=" + logDate + "]";
	}
	
	
}
