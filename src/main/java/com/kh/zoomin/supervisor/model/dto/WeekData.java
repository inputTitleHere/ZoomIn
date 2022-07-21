package com.kh.zoomin.supervisor.model.dto;

import java.sql.Date;

public class WeekData {

	Date day;
	int cnt;
	
	public WeekData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeekData(Date day, int cnt) {
		super();
		this.day = day;
		this.cnt = cnt;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "WeekData [day=" + day + ", cnt=" + cnt + "]";
	}
	
	
}
