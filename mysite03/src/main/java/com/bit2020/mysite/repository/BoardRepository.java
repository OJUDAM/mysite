package com.bit2020.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2020.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map<String, Object>> findAll() {
		return sqlSession.selectList("board.findAll");
	}

	public Map<String,Object> findContentAndTitleByNo(Long no) {
		return sqlSession.selectOne("board.findContentAndTitleByNo",no);
	}

	public void insert(BoardVo boardVo) {
		sqlSession.insert("board.insert", boardVo);
	}

	public void update(BoardVo boardVo) {
		sqlSession.update("board.update", boardVo);
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}
	
}
