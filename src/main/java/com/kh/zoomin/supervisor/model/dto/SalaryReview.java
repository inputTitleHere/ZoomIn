package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class SalaryReview {
	int no;
	String category;	//회사분류
	String companyName;
	String writer;
	int salary;
	int workYear;
	String jobPosition;
	Date regDate;
	
	public SalaryReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryReview(int no, String category, String companyName, String writer, int salary, int workYear,
			String jobPosition, Date regDate) {
		super();
		this.no = no;
		this.category = category;
		this.companyName = companyName;
		this.writer = writer;
		this.salary = salary;
		this.workYear = workYear;
		this.jobPosition = jobPosition;
		this.regDate = regDate;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getWorkYear() {
		return workYear;
	}

	public void setWorkYear(int workYear) {
		this.workYear = workYear;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	
	
}
