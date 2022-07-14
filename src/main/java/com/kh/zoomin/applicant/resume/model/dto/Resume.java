package com.kh.zoomin.applicant.resume.model.dto;

public class Resume {
	String id;
	String name;
	String birthday;
	Gender gender;
	String email;
	String phoneNum;
	String address;
	SchoolType schoolType;
	String schoolName;
	Status schoolStatus;
	String majorName;
	double grade;
	double totalPoint;
	String companyName;
	int career;
	Status careerStatus;
	
	public Resume() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resume(String id, String name, String birthday, Gender gender, String email, String phoneNum, String address,
			SchoolType schoolType, String schoolName, Status schoolStatus, String majorName, double grade,
			double totalPoint, String companyName, int career, Status careerStatus) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.schoolType = schoolType;
		this.schoolName = schoolName;
		this.schoolStatus = schoolStatus;
		this.majorName = majorName;
		this.grade = grade;
		this.totalPoint = totalPoint;
		this.companyName = companyName;
		this.career = career;
		this.careerStatus = careerStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCareer() {
		return career;
	}

	public void setCareer(int career) {
		this.career = career;
	}

	public Status getCareerStatus() {
		return careerStatus;
	}

	public void setCareerStatus(Status careerStatus) {
		this.careerStatus = careerStatus;
	}

	@Override
	public String toString() {
		return "Resume [id=" + id + ", name=" + name + ", birthday=" + birthday + ", gender=" + gender + ", email="
				+ email + ", phoneNum=" + phoneNum + ", address=" + address + ", schoolType=" + schoolType
				+ ", schoolName=" + schoolName + ", schoolStatus=" + schoolStatus + ", majorName=" + majorName
				+ ", grade=" + grade + ", totalPoint=" + totalPoint + ", companyName=" + companyName + ", career="
				+ career + ", careerStatus=" + careerStatus + "]";
	}
	
	
}
