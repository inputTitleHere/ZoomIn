package com.kh.zoomin.recruit.board.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.zoomin.recruit.board.exception.RecruitBoardException;

public class TestDao {

	private Properties prop = new Properties();
	
	public TestDao() {
		String filename = TestDao.class.getResource("/sql/kh/test/test.properties")
				.getPath();// 임의로 경로를 만들었다. 여기에 properties파일이 있다고 가정한다.
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			throw new RecruitBoardException("RecruitBoard properties failed to load", e);
		}
	}

	// 편하게 사용하기위해 handler를 정의.
	private Test handleTest(ResultSet rset) throws SQLException{
		int seq = rset.getInt("seq");
		String writer = rset.getString("writer");
		String title = rset.getString("title");
		String content = rset.getString("content");
		Date regDate = rset.getDate("regdate");
		return new Test(seq, writer, title, content, regDate);
	}
	
	public List<Test> selectList(Connection conn){ //Service단에서 Connection객체를 받아왔다고 가정할 것이다. 
		// db url, db username, db password는 모두 conn에서 해결하였다.
		// 이 메소드 아래에서 conn을 생성하는 코드를작성했다.
		// DAO에서는 connection에 대한 close처리를 안하기 때문에 이점은 생략했다.
		List<Test> result=new ArrayList<Test>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql="select * from TEST";
		try {
			pstmt=conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				// 매 행마다 리스트에 추가한다.
				result.add(handleTest(rset));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			//원래라면 사용자지정 오류클래스를 만들어 던진다.
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * 이들은 template에서 선언된 메소드들이다.
	 * 임의로 보여주기 위해 가져왔다.
	 */
	private void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 이들은 template에서 선언된 메소드들이다.
	 * 임의로 보여주기 위해 가져왔다.
	 */
	private void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && !pstmt.isClosed())
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * conn이 어떻게 만들어지는지 보여주기 위해 가져왔다.
	 * template에서 가져와서 문제에 맞게 약간의 변화를 주었다. 
	 * 위 코드에서는 사용되지 않고 server에서 사용될 것이다.
	 * 문제에서 url, username, password를 제공해서 보여주기 위해 가져왔다.
	 */
	private Connection getConnection() {
		Connection conn = null;
		String url="jdbc:oracle:thin:@192.168.10.3:1521:xe";
		String user="kh";
		String password="kh";
		try {
			conn =  DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}

class Test{

	private int seq;
	private String writer;
	private String title;
	private String content;
	private Date regDate; // java.sql.Date;
	
	public Test() {
		
	}

	public Test(int seq, String writer, String title, String content, Date regDate) {
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}

