package com.kh.zoomin.applicant.resume.model.dto;

public class Resume {
	int no;
	int uid;
	String name;
	String birthday;
	Gender gender;
	String address;
	int interestJob;
	SchoolType schoolType;
	String schoolName;
	Status schoolStatus;
	String majorName;
	double grade;
	double totalPoint;
	public Resume() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Resume(int no, int uid, String name, String birthday, Gender gender, String address, int interestJob,
			SchoolType schoolType, String schoolName, Status schoolStatus, String majorName, double grade,
			double totalPoint) {
		super();
		this.no = no;
		this.uid = uid;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.interestJob = interestJob;
		this.schoolType = schoolType;
		this.schoolName = schoolName;
		this.schoolStatus = schoolStatus;
		this.majorName = majorName;
		this.grade = grade;
		this.totalPoint = totalPoint;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getInterestJob() {
		return interestJob;
	}
	public void setInterestJob(int interestJob) {
		this.interestJob = interestJob;
	}
	public SchoolType getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(SchoolType schoolType) {
		this.schoolType = schoolType;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Status getSchoolStatus() {
		return schoolStatus;
	}
	public void setSchoolStatus(Status schoolStatus) {
		this.schoolStatus = schoolStatus;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public double getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(double totalPoint) {
		this.totalPoint = totalPoint;
	}
	
	
}
