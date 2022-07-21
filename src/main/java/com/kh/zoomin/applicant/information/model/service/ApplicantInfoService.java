package com.kh.zoomin.applicant.information.model.service;

import static com.kh.zoomin.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.zoomin.applicant.information.model.dao.ApplicantInfoDao;
import com.kh.zoomin.applicant.information.model.dto.CompanyInfo;

public class ApplicantInfoService {
	
	private ApplicantInfoDao ApplicantInfoDao = new ApplicantInfoDao();

	public List<CompanyInfo> findByCompany(Map<String, Object> param) {
		Connection conn = getConnection();
		List<CompanyInfo> result= ApplicantInfoDao.findByCompany(conn,param);
		close(conn);
		return result;
	}

	public int totalCompanyCount() {
		Connection conn = getConnection();
		int result= ApplicantInfoDao.totalCompanyCount(conn);
		close(conn);
		return result;
	}

}
