package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class CompanyReview {
	int no;
	String id;
	String content;
	Date regDate;
	
	public CompanyReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyReview(int no, String id, String content, Date regDate) {
		super();
		this.no = no;
		this.id = id;
		this.content = content;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CompanyReview [no=" + no + ", id=" + id + ", content=" + content + ", regDate=" + regDate + "]";
	}
	
	
}
