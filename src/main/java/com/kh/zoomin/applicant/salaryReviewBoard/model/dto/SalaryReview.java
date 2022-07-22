package com.kh.zoomin.applicant.salaryReviewBoard.model.dto;

import java.sql.Date;

public class SalaryReview {

	private int no;
	private int uid;
	private String companyNo;
	private int categoryNumber;
	private int salary;
	private int workYear;
	private String jobPosition;
	private Date regDate;
	
	public SalaryReview() {
		super();
	}

	public SalaryReview(int no, int uid, String companyNo, int categoryNumber, int salary, int workYear,
			String jobPosition, Date regDate) {
		super();
		this.no = no;
		this.uid = uid;
		this.companyNo = companyNo;
		this.categoryNumber = categoryNumber;
		this.salary = salary;
		this.workYear = workYear;
		this.jobPosition = jobPosition;
		this.regDate = regDate;
	}
	
	public SalaryReview(int uid, String companyNo, int categoryNumber, int salary, int workYear,
			String jobPosition, Date regDate) {
		super();
		this.uid = uid;
		this.companyNo = companyNo;
		this.categoryNumber = categoryNumber;
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

	public int getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(int categoryNumber) {
		this.categoryNumber = categoryNumber;
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
		return "SalaryReview [no=" + no + ", uid=" + uid + ", companyNo=" + companyNo + ", categoryNumber="
				+ categoryNumber + ", salary=" + salary + ", workYear=" + workYear + ", jobPosition=" + jobPosition
				+ ", regDate=" + regDate + "]";
	}
	
}
