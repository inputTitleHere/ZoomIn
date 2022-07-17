package com.kh.zoomin.applicant.member.model.dto;

import java.sql.Date;

import com.kh.zoomin.member.dto.Member;

public class ApplicantMember extends Member{

	private String uid;
	private String memberId;
	private String password;
	private String phone;
	private String email;
	private Date regDate;
	
	public ApplicantMember() {
		super(2);
		// TODO Auto-generated constructor stub
	}

	public ApplicantMember(String uid, String memberId, String password, String phone, String email, Date regDate) {
		super(2);
		this.uid = uid;
		this.memberId = memberId;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "ApplicantMember [uid=" + uid + ", memberId=" + memberId + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + ", regDate=" + regDate + "]";
	}
	
	

}
