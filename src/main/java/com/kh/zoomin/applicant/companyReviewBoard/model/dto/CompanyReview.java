package com.kh.zoomin.applicant.companyReviewBoard.model.dto;

import java.sql.Date;

public class CompanyReview {

	private int no;
	private int uid;
	private String companyNo;
	private int categoryNumber;
	private String content;
	private int stars;
	private int workLifeBalance;
	private int levelUp;
	private int workIntensity;
	private int potential;
	private int salarySatisfaction;
	private Date regDate;
	
	public CompanyReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyReview(int no, int uid, String companyNo, int categoryNumber, String content, int stars,
			int workLifeBalance, int levelUp, int workIntensity, int potential, int salarySatisfaction, Date regDate) {
		super();
		this.no = no; // 회사리뷰 고유번호
		this.uid = uid;
		this.companyNo = companyNo;
		this.categoryNumber = categoryNumber;
		this.content = content;
		this.stars = stars;
		this.workLifeBalance = workLifeBalance;
		this.levelUp = levelUp;
		this.workIntensity = workIntensity;
		this.potential = potential;
		this.salarySatisfaction = salarySatisfaction;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public int getWorkLifeBalance() {
		return workLifeBalance;
	}

	public void setWorkLifeBalance(int workLifeBalance) {
		this.workLifeBalance = workLifeBalance;
	}

	public int getLevelUp() {
		return levelUp;
	}

	public void setLevelUp(int levelUp) {
		this.levelUp = levelUp;
	}

	public int getWorkIntensity() {
		return workIntensity;
	}

	public void setWorkIntensity(int workIntensity) {
		this.workIntensity = workIntensity;
	}

	public int getPotential() {
		return potential;
	}

	public void setPotential(int potential) {
		this.potential = potential;
	}

	public int getSalarySatisfaction() {
		return salarySatisfaction;
	}

	public void setSalarySatisfaction(int salarySatisfaction) {
		this.salarySatisfaction = salarySatisfaction;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CompanyReview [no=" + no + ", uid=" + uid + ", companyNo=" + companyNo + ", categoryNumber="
				+ categoryNumber + ", content=" + content + ", stars=" + stars + ", workLifeBalance=" + workLifeBalance
				+ ", levelUp=" + levelUp + ", workIntensity=" + workIntensity + ", potential=" + potential
				+ ", salarySatisfaction=" + salarySatisfaction + ", regDate=" + regDate + "]";
	}
	
	
	
	
	
}
