package com.kh.zoomin.applicant.companyReviewBoard.model.dto;

import java.sql.Date;

public class CompanyReviewExt extends CompanyReview {
	
	private String companyName;
	
	public CompanyReviewExt() {
		super();
	}

	public CompanyReviewExt(int uid, String companyNo, int categoryNumber, String content, int stars,
			int workLifeBalance, int levelUp, int workIntensity, int potential, int salarySatisfaction, Date regDate) {
		super();
	}

	public CompanyReviewExt(String companyName) {
		super();
		this.companyName = companyName;
	}

	public CompanyReviewExt(int no, int uid, String companyNo, int categoryNumber, String content, int stars,
			int workLifeBalance, int levelUp, int workIntensity, int potential, int salarySatisfaction, Date regDate) {
		super(no, uid, companyNo, categoryNumber, content, stars, workLifeBalance, levelUp, workIntensity, potential,
				salarySatisfaction, regDate);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "CompanyReviewExt [companyName=" + companyName + "]";
	}
	
}
