package com.kh.zoomin.applicant.member.model.dto;

import java.sql.Date;

import com.kh.zoomin.member.dto.Member;

public class ApplicantMember extends Member{

	private int uid;
	private String name;
	private String id;
	private String password;
	private String phone;
	private String email;
	private Date regDate;

	public ApplicantMember() {
		super(2);
	}

	public ApplicantMember(int uid, String name, String id, String password, String phone,
			String email, Date regDate) {
		super(2);
		this.uid = uid;
		this.name = name;
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
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

	public String getId() {
		return id;
	}

	public void setMemberId(String id) {
		this.id = id;
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
		return "ApplicantMember [uid=" + uid + ", name=" + name + ", id=" + id + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + ", regDate=" + regDate + "]";
	}
	
	
}
