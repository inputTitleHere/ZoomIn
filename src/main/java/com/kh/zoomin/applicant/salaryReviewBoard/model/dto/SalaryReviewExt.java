package com.kh.zoomin.applicant.salaryReviewBoard.model.dto;

import java.sql.Date;

public class SalaryReviewExt extends SalaryReview {
	private String jobCategory;
	private String companyName;
	

	public SalaryReviewExt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryReviewExt(int no, int uid, String companyNo, int categoryNumber, int salary, int workYear,
			String jobPosition, Date regDate) {
		super(no, uid, companyNo, categoryNumber, salary, workYear, jobPosition, regDate);
	}
	
	/**
	 * 백승윤 추가 코드 07/24
	 * 
	 */
	public SalaryReviewExt(int no, int uid, String companyNo, int categoryNumber, int salary, int workYear,
			String jobPosition, Date regDate, String companyName) {
		super(no, uid, companyNo, categoryNumber, salary, workYear, jobPosition, regDate);
		this.companyName=companyName;
	}

	public SalaryReviewExt(SalaryReview s, String companyName) {
		super(s.getNo(),s.getUid(),s.getCompanyNo(),s.getCategoryNumber(),s.getSalary(),s.getWorkYear(),s.getJobPosition(),s.getRegDate());
		this.companyName=companyName;
	}


//	public SalaryReviewExt(int uid, String companyNo, int categoryNumber, int salary, int workYear, String jobPosition,
//			Date regDate) {
//		super();
//	}

	public SalaryReviewExt(String jobCategory) {
		super();
		this.jobCategory = jobCategory;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Override
	public String toString() {
		return "SalaryReviewExt [jobCategory=" + jobCategory + "]";
	}
	
	
	
}
