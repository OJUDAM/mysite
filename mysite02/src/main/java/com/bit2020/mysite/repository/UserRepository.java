package com.bit2020.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bit2020.mysite.vo.UserVo;

public class UserRepository {

	public boolean save(UserVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// 1 연결하기
			connection = getConnection();
			String sql = "insert into user values(null, ?, ?, password(?), ?, now())";

			pstmt = connection.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			// 5. sql 실행
			int count = pstmt.executeUpdate();
			result = (count == 1);
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

	public UserVo findByEmailAndPassword(String email, String password) {
		UserVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// 1 연결하기
			connection = getConnection();
			String sql = "select no,name from user where email= ? and password = password(?)";

			pstmt = connection.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			// 5. sql 실행
			 rs = pstmt.executeQuery();
			
			 //결과 가져오기
			 if(rs.next()) {
				 Long no = rs.getLong(1);
				 String name = rs.getString(2);
				 
				 result = new UserVo();
				 result.setNo(no);
				 result.setName(name);
			 }
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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

	public UserVo findByNo(long no) {
		UserVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// 1 연결하기
			connection = getConnection();
			String sql = "select no,name,email,gender from user where no= ?";

			pstmt = connection.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);

			// 5. sql 실행
			 rs = pstmt.executeQuery();
			
			 //결과 가져오기
			 if(rs.next()) {
				 result = new UserVo();
				 result.setNo(rs.getLong(1));
				 result.setName(rs.getString(2));
				 result.setEmail(rs.getString(3));
				 result.setGender(rs.getString(4));
			 }
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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

	public boolean update(UserVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// 1 연결하기
			connection = getConnection();
			String sql = "update user set name=?,password=password(?), gender=? where no=? and email=?";

			pstmt = connection.prepareStatement(sql);
			System.out.println(vo);
			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());
			pstmt.setString(5, vo.getEmail());
			
			// 5. sql 실행
			int count = pstmt.executeUpdate();
			result = (count == 1);
		} catch (SQLException e) {
			System.out.println("Update 에러 : " + e);
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

}
