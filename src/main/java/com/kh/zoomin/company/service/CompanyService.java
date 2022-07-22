package com.kh.zoomin.company.service;

import static com.kh.zoomin.common.JdbcTemplate.close;
import static com.kh.zoomin.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.zoomin.company.dao.CompanyDao;
import com.kh.zoomin.company.dto.Company;

public class CompanyService {

	private CompanyDao companyDao = new CompanyDao();

	public List<Company> findCompany(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Company> list = companyDao.findAll(conn, param);
		close(conn);
		return list;
	}

	public Company getCompanyByNo(String companyNo) {
		Connection conn = getConnection();
		Company company = null;
		company = companyDao.getCompanyByNo(conn, companyNo);
		close(conn);
		return company;
	}
	
	
}
