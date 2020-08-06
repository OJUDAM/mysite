package com.bit2020.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.bit2020.mysite.vo.GuestBookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean delete(GuestBookVo vo) {
		return sqlSession.delete("guestbook.drop",vo) ==1 ;
	}

	public boolean insert(GuestBookVo vo) {
		return sqlSession.insert("guestbook.add",vo) == 1;
	}

	public List<GuestBookVo> findAll() {	
		return sqlSession.selectList("guestbook.findAll");
	}

}
