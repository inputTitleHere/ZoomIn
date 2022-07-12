package com.java.test;

import java.lang.ProcessHandle.Info;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.java.zoomin.common.JdbcTemplate.*;

public class TestClass {

	private Properties prop = new Properties();

	public static void main(String[] args) {
		
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rset =null;
		String sql = "select * from company_table";
		try {
			conn = getConnection();
			pstmt =conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			System.out.println("Loading");
			while(rset.next()) {
				String company_no=rset.getString("company_no");
				String company_name=rset.getString("company_name");
				String company_info=rset.getString("company_info");
				System.out.printf("%s - %s - %s\n",company_no, company_name, company_info);				
			}
			System.out.println("FINISHED");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		
	}
	
}
