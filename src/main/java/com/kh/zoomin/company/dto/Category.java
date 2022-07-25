package com.kh.zoomin.company.dto;

public class Category {
	String no;
	String domain;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Category(String no, String domain) {
		super();
		this.no = no;
		this.domain = domain;
	}

	
	

	public String getNo() {
		return no;
	}



	public void setNo(String no) {
		this.no = no;
	}



	public String getDomain() {
		return domain;
	}



	public void setDomain(String domain) {
		this.domain = domain;
	}



	@Override
	public String toString() {
		return "Category [no=" + no + ", domain=" + domain + "]";
	}
	
	
}
