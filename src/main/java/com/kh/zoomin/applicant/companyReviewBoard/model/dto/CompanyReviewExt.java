package com.kh.zoomin.applicant.companyReviewBoard.model.dto;

import java.sql.Date;

public class CompanyReviewExt extends CompanyReview {
	
	public CompanyReviewExt() {
		super();
	}

	public CompanyReviewExt(int no, String content, int stars, int workLifeBalance, int levelUp, int workIntensity,
			int potential, int salarySatisfaction, Date regDate) {
		super(no, salarySatisfaction, content, no, content, stars, workLifeBalance, levelUp, workIntensity, 
				potential, salarySatisfaction, regDate);
	}

	
}
