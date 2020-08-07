package com.bit2020.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit2020.mysite.security.Auth;
import com.bit2020.mysite.service.BoardService;
import com.bit2020.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<Map<String, Object>> list = boardService.getList();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@Auth
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/view/{no}",method=RequestMethod.GET)
	public String view(Model model, @PathVariable("no")Long no) {
		
		Map<String,Object> map =boardService.getContentsAndTitleByNo(no);
		model.addAttribute("map",map);
		return "board/view";
	}
	
}
