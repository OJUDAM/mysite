package com.bit2020.mysite.repository;

import java.util.HashMap;
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

	public void delete(Long no, Long userNo) {
		Map<String, Long> map = new HashMap<>();
		map.put("no",no);
		map.put("userNo", userNo);
		sqlSession.delete("board.delete", map);
	}

	public void updateHit(Long no) {
		sqlSession.update("board.hitup",no);
	}

	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("board.getTotalCount", keyword);
	}

	public List<BoardVo> getList(String keyword, Integer currentPage, int listSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("keyword",keyword);
		map.put("startIndex", (currentPage-1)*listSize);
		map.put("size",listSize);
		
		return sqlSession.selectList("board.getList",map);
	}

	public int updateOrderNo(Integer groupNo, Integer orderNo) {
		Map<String, Integer> map = new HashMap<>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		return sqlSession.update("board.updateOrderNo",map);
	}
	
}
