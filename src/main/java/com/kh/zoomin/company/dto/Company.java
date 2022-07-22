package com.kh.zoomin.company.dto;

public class Company {

	private String companyNo;
	private String companyName;
	private String companyInfo;
//	private String companyLogo;
	

	public Company() {
		super();
	}

	public Company(String companyNo, String companyName, String companyInfo) {
		super();
		this.companyNo = companyNo;
		this.companyName = companyName;
		this.companyInfo = companyInfo;
	}

	
	/*
	 * public Company(String companyNo, String companyName, String companyInfo,
	 * String companyLogo) { super(); this.companyNo = companyNo; this.companyName =
	 * companyName; this.companyInfo = companyInfo; this.companyLogo = companyLogo;
	 * }
	 */

//	public String getCompanyLogo() {
//		return companyLogo;
//	}
//	
//	public void setCompanyLogo(String companyLogo) {
//		this.companyLogo = companyLogo;
//	}
	
	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	@Override
	public String toString() {
		return "Company [companyNo=" + companyNo + ", companyName=" + companyName + ", companyInfo=" + companyInfo
				+ "]";
	}


//	@Override
//	public String toString() {
//		return "Company [companyNo=" + companyNo + ", companyName=" + companyName + ", companyInfo=" + companyInfo
//				+ ", companyLogo=" + companyLogo + "]";
//	}
	
	
	
}
