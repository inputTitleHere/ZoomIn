package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class SalaryReview {
	int no;
	String writer;
	String companyNo;
	String Category;	//도메인
	int salary;
	int workYear;
	String jobPosition;
	Date regDate;
	
	public SalaryReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryReview(int no, String writer, String companyNo, String category, int salary, int workYear,
			String jobPosition, Date regDate) {
		super();
		this.no = no;
		this.writer = writer;
		this.companyNo = companyNo;
		Category = category;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
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

	@Override
	public String toString() {
		return "SalaryReview [no=" + no + ", writer=" + writer + ", companyNo=" + companyNo + ", Category=" + Category
				+ ", salary=" + salary + ", workYear=" + workYear + ", jobPosition=" + jobPosition + ", regDate="
				+ regDate + "]";
	}
	
	
}
