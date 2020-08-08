package com.bit2020.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2020.mysite.repository.BoardRepository;
import com.bit2020.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public List<Map<String, Object>> getList() {
		return boardRepository.findAll();
	}

	public Map<String,Object> getContentsAndTitleByNo(Long no) {
		return boardRepository.findContentAndTitleByNo(no);
	}

	public void addToList(BoardVo boardVo) {
		boardRepository.insert(boardVo);
	}

	public void modifyList(BoardVo boardVo) {
		boardRepository.update(boardVo);
	}

	public BoardVo getUserByNo(Long no) {
		return boardRepository.findByNo(no);
	}

	
	
	
}
