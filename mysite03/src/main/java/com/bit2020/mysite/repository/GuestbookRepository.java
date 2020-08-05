package com.bit2020.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2020.mysite.vo.GuestBookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean delete(Long no, String password) {
		boolean result = false;
		Connection connection = null;
		/* Statement stmt = null; */
		PreparedStatement pstmt = null;
		try {
			// 1 연결하기
			connection = getConnection();

			/*
			 * // 3. Statement 객체 생성 sql 실행하기 위해서 stmt = connection.createStatement();
			 */

			// 3. SQL 준비
			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = connection.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			// 5. sql 실행
			int count = pstmt.executeUpdate();
			result = (count == 1);

			/*
			 * // 4. SQL 실행 String sql1 =
			 * "insert into guestbook values(null,'"+vo.getName()+"','"+vo.getPassword()+
			 * "',now(),'"+vo.getMessage()+"')"; int count = stmt.executeUpdate(sql1);
			 * result =(count == 1);
			 */
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	public boolean insert(GuestBookVo vo) {
		boolean result = false;
		Connection connection = null;
		/* Statement stmt = null; */
		PreparedStatement pstmt = null;
		try {
			// 1 연결하기
			connection = getConnection();

			/*
			 * // 3. Statement 객체 생성 sql 실행하기 위해서 stmt = connection.createStatement();
			 */

			// 3. SQL 준비
			String sql = "insert into guestbook values(null,?,?,now(),?)";
			pstmt = connection.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());

			// 5. sql 실행
			int count = pstmt.executeUpdate();
			result = (count == 1);

			/*
			 * // 4. SQL 실행 String sql1 =
			 * "insert into guestbook values(null,'"+vo.getName()+"','"+vo.getPassword()+
			 * "',now(),'"+vo.getMessage()+"')"; int count = stmt.executeUpdate(sql1);
			 * result =(count == 1);
			 */
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<GuestBookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}

	private Connection getConnection() throws SQLException {
		Connection connection = null;
		// 1. JDBC Driver(MariaDB Driver)
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";

			connection = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("에러 : " + e);
		}

		return connection;
	}
}
