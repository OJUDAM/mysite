package com.bit2020.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2020.mysite.exception.UserRepositoryException;
import com.bit2020.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	public boolean save(UserVo vo) {
		int count = sqlSession.insert("user.save", vo);
		return count == 1;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		/*
		 * Map<String, Object> map = new HashMap<>();
		   map.put("email", email);
		   map.put("password", password);
		*/
		UserVo result = sqlSession.selectOne("findByEmailAndPassword", vo);
				return result;
	}

	public UserVo findByNo(long no) throws UserRepositoryException{
		
		return sqlSession.selectOne("user.findByNo",no);
	}

	public boolean update(UserVo vo) {
		int count=sqlSession.update("user.update",vo);
		return count==1;

	}

}
