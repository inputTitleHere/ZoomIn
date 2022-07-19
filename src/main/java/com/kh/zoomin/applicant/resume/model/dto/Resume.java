package com.kh.zoomin.applicant.resume.model.dto;

import java.util.Date;

public class Resume {
	private int no;
	private int uid;
	private int categoryNumber;
	private String name;
	private Date birthday;
	private Gender gender;
	private String address;
	private SchoolType schoolType;
	private String schoolName;
	private Status schoolStatus;
	private String majorName;
	private double grade;
	private double totalPoint;
	
	
	public Resume() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Resume(int uid, int categoryNumber, String name, Date birthday, Gender gender, String address, 
			SchoolType schoolType, String schoolName, Status schoolStatus, String majorName, double grade,
			double totalPoint) {
		super();
		
		this.uid = uid;
		this.categoryNumber = categoryNumber;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
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
	public int getCategoryNumber() {
		return categoryNumber;
	}
	public void setCategoryNumber(int categoryNumber) {
		this.categoryNumber = categoryNumber;
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
