package com.kh.zoomin.recruit.member;

import com.kh.zoomin.member.dto.Member;

public class RecruitMember extends Member{
	
	private String companyNo;
	private int uid;
	
	/**
	 * 임시 채용자 계정.
	 */
	public RecruitMember() {
		super(1);
		companyNo="4923047853";
		uid=1;
	}

	@Override
	public String toString() {
		return "RecruitMember [companyNo=" + companyNo + ", uid=" + uid + "]";
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	
	
	
	
}

