package com.kh.zoomin.company.service;

import static com.kh.zoomin.common.JdbcTemplate.*;

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


	public List<Company> getCompanyAll() {
		Connection conn = getConnection();
		List<Company> company = companyDao.getCompanyAll(conn);
		close(conn);
		return company;
	}

	public Company getCompanyByName(String userVal) {
		Connection conn = getConnection();
		Company company = companyDao.getCompanyByName(conn, userVal);
		close(conn);
		return company;
	}
	
	public boolean isCompanyExist(String companyNo) {
		Connection conn = getConnection();
		boolean result=false;
		result=companyDao.isCompanyExist(companyNo, conn);
		close(conn);
		return result;
	}

	public int insertNewCompany(Company company) {
		int result=0;
		Connection conn = getConnection();
		try {
			result=companyDao.insertNewCompany(company,conn);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;

	}
	
	
}
