package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class RecruitBoard {
	int no;
	String category;
	String companyName;
	String recruiter;
	String title;
	Date regDate;
	Date closureDate;
	
	public RecruitBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecruitBoard(int no, String category, String companyName, String recruiter, String title, Date regDate,
			Date closureDate) {
		super();
		this.no = no;
		this.category = category;
		this.companyName = companyName;
		this.recruiter = recruiter;
		this.title = title;
		this.regDate = regDate;
		this.closureDate = closureDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(Date closureDate) {
		this.closureDate = closureDate;
	}

	@Override
	public String toString() {
		return "RecruitBoard [no=" + no + ", category=" + category + ", companyNo=" + companyName + ", recruiter="
				+ recruiter + ", title=" + title + ", regDate=" + regDate + ", closureDate=" + closureDate + "]";
	}
	
	
}
