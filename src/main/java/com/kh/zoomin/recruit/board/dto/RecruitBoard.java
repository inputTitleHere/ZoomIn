package com.kh.zoomin.recruit.board.dto;

import java.sql.Date;

public class RecruitBoard {

	private int no; // 해당 게시글의 고유번호이다.
	private int uid; // 게시글 작성자(구인자)의 고유번호이다.
	private int categoryNumber; // 소속 카테고리 정보이다.
	private String companyNo; // 사업자 등록번호는 문자열로 저장하였다.
	private String title; //
	private String careerYears; //
	private String schoolStatus; //
	private String workType; //
	private String officeLocation; //
	private String salary; //
	private String content; //
	private Date closureDate; //
	private Date regDate; //

	public RecruitBoard() {
		super();
	}

	public RecruitBoard(int no, int uid, int categoryNumber, String company_no, String title, String careerYears,
			String schoolStatus, String workType, String officeLocation, String salary, String content,
			Date closureDate, Date regDate) {
		super();
		this.no = no;
		this.uid = uid;
		this.categoryNumber = categoryNumber;
		this.companyNo = company_no;
		this.title = title;
		this.careerYears = careerYears;
		this.schoolStatus = schoolStatus;
		this.workType = workType;
		this.officeLocation = officeLocation;
		this.salary = salary;
		this.content = content;
		this.closureDate = closureDate;
		this.regDate = regDate;
	}

	public RecruitBoard(RecruitBoard recruitBoard) {
		this.no = recruitBoard.getNo();
		this.uid = recruitBoard.getUid();
		this.categoryNumber = recruitBoard.getCategoryNumber();
		this.companyNo = recruitBoard.getCompanyNo();
		this.title = recruitBoard.getTitle();
		this.careerYears = recruitBoard.getCareerYears();
		this.schoolStatus = recruitBoard.getSchoolStatus();
		this.workType = recruitBoard.getWorkType();
		this.officeLocation = recruitBoard.getOfficeLocation();
		this.salary = recruitBoard.getSalary();
		this.content = recruitBoard.getContent();
		this.closureDate = recruitBoard.getClosureDate();
		this.regDate = recruitBoard.getRegDate();
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

	public int getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(int categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String company_no) {
		this.companyNo = company_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCareerYears() {
		return careerYears;
	}

	public void setCareerYears(String careerYears) {
		this.careerYears = careerYears;
	}

	public String getSchoolStatus() {
		return schoolStatus;
	}

	public void setSchoolStatus(String schoolStatus) {
		this.schoolStatus = schoolStatus;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(Date closureDate) {
		this.closureDate = closureDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public RecruitBoard setDefaults() {
		if ("".equals(careerYears)) {
			careerYears="경력무관";
		}
		if ("".equals(schoolStatus)) {
			schoolStatus="학력무관";
		}
		if ("".equals(workType)) {
			workType="미정";
		}
		if ("".equals(officeLocation)) {
			officeLocation="미정";
		}
		if ("".equals(salary)) {
			salary="회사내규";
		}
		return this;
	}

	@Override
	public String toString() {
		return "RecruitBoard [no=" + no + ", uid=" + uid + ", categoryNumber=" + categoryNumber + ", company_no="
				+ companyNo + ", title=" + title + ", careerYears=" + careerYears + ", schoolStatus=" + schoolStatus
				+ ", workType=" + workType + ", officeLocation=" + officeLocation + ", salary=" + salary + ", content="
				+ content + ", closureDate=" + closureDate + ", regDate=" + regDate + "]";
	}

	@Deprecated
	/**
	 * TODO : A method that returns an HTML style String to be used directly inside
	 * a JSP or other formats.
	 * 
	 * @return
	 */
	public String toHTML() {
		// todo
		return null;
	}

}
