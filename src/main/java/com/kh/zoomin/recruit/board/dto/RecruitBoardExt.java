package com.kh.zoomin.recruit.board.dto;

import java.sql.Date;

public class RecruitBoardExt extends RecruitBoard {

	// 임시로 만들어둠 아직 사용하지 않음 2022/07/17
	private int enrolledCount;
	private boolean isFavourited;
	private boolean isEnrolled;
	
	
	public RecruitBoardExt() {
		super();
	}

	public RecruitBoardExt(int no, int uid, int categoryNumber, String company_no, String title, String careerYears,
			String schoolStatus, String workType, String officeLocation, String salary, String content,
			Date closureDate, Date regDate) {
		super(no, uid, categoryNumber, company_no, title, careerYears, schoolStatus, workType, officeLocation, salary,
				content, closureDate, regDate);
	}
	
	public RecruitBoardExt(int no, int uid, int categoryNumber, String company_no, String title, String careerYears,
			String schoolStatus, String workType, String officeLocation, String salary, String content,
			Date closureDate, Date regDate, int enrolledCount) {
		super(no, uid, categoryNumber, company_no, title, careerYears, schoolStatus, workType, officeLocation, salary,
				content, closureDate, regDate);
		this.enrolledCount = enrolledCount;
	}
	
	public RecruitBoardExt(RecruitBoard recruitBoard,boolean isFavourited, boolean isEnrolled) {
		super(recruitBoard);
		this.isFavourited=isFavourited;
		this.isEnrolled=isEnrolled;
	}
	

	public int getEnrolledCount() {
		return enrolledCount;
	}

	public void setEnrolledCount(int enrolledCount) {
		this.enrolledCount = enrolledCount;
	}

	@Override
	public String toString() {
		return "RecruitBoardExt [enrolledCount=" + enrolledCount + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
