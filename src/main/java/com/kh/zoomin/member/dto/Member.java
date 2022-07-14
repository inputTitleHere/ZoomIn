package com.kh.zoomin.member.dto;

public class Member {

	private int memberType; // 0,1,2 0(관리자) 1(구인자)2(구직자) 

	public Member(int memberType) {
		super();
		this.memberType = memberType;
	}

	public int getMemberType() {
		return memberType;
	}

	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}
	
	
	
}
