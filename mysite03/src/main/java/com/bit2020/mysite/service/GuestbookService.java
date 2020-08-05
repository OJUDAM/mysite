package com.bit2020.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2020.mysite.repository.GuestbookRepository;
import com.bit2020.mysite.vo.GuestBookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestBookVo> findAll() {
		return guestbookRepository.findAll();
	}

	public void addList(GuestBookVo vo) {
		guestbookRepository.insert(vo);
	}

	public void deleteFromList(Long no, String password) {
		guestbookRepository.delete(no, password);
	}

	
}