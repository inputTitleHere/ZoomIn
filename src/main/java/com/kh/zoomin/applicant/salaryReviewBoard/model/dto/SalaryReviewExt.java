package com.kh.zoomin.applicant.salaryReviewBoard.model.dto;

import java.sql.Date;

public class SalaryReviewExt extends SalaryReview {
	private String jobCategory;

	public SalaryReviewExt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryReviewExt(int no, int uid, String companyNo, int categoryNumber, int salary, int workYear,
			String jobPosition, Date regDate) {
		super(no, uid, companyNo, categoryNumber, salary, workYear, jobPosition, regDate);
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

	@Override
	public String toString() {
		return "SalaryReviewExt [jobCategory=" + jobCategory + "]";
	}
	
	
	
}
