package com.kh.zoomin.applicant.information.model.dto;

public class CompanyInfo {
	private String companyNo;
	private String companyName;
	private String companyLogo;
	private String companyInfo;
	public CompanyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyInfo(String companyNo, String companyName, String companyLogo, String companyInfo) {
		super();
		this.companyNo = companyNo;
		this.companyName = companyName;
		this.companyLogo = companyLogo;
		this.companyInfo = companyInfo;
	}
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
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	@Override
	public String toString() {
		return "CompanyInfo [companyNo=" + companyNo + ", companyName=" + companyName + ", companyLogo=" + companyLogo
				+ ", companyInfo=" + companyInfo + "]";
	}
	
	
}
