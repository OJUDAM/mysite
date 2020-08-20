package com.bit2020.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2020.mysite.security.Auth;
import com.bit2020.mysite.security.AuthUser;
import com.bit2020.mysite.service.BoardService;
import com.bit2020.mysite.vo.BoardVo;
import com.bit2020.mysite.vo.UserVo;
import com.bit2020.mysite.web.util.WebUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/test")
	public String list(Model model) {
		List<Map<String, Object>> list = boardService.getList();
		model.addAttribute("list", list);
		return "board/list";
	}
	@RequestMapping("")
	public String index(
			@RequestParam(value="p",required=true,defaultValue="1") Integer page,
			@RequestParam(value="kwd",required=true,defaultValue="") String keyword,
			Model model) {
		Map<String, Object> map = boardService.getMessageList(page, keyword);
		model.addAttribute("map",map);
		
		return "board/index";
	}
	
	
	@Auth
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(
			@AuthUser UserVo authUser,
			@ModelAttribute BoardVo boardVo,
			@RequestParam(value="p", required=true, defaultValue="1")Integer page,
			@RequestParam(value="kwd", required=true,defaultValue="")String keyword) {
		boardVo.setUserNo(authUser.getNo());
		if(boardVo.getGroupNo()!=null) {
			boardService.increaseGroupOrderNo(boardVo);
		}
		boardService.addToList(boardVo);
		return (boardVo.getGroupNo() != null) ?
				"redirect:/board?p="+page+"&kwd="+WebUtil.encodeURL(keyword,"UTF-8"):
					"redirect:/board";
	}
	
	@RequestMapping(value="/view/{no}",method=RequestMethod.GET)
	public String view(Model model, @PathVariable("no")Long no) {
		
		Map<String,Object> map =boardService.getContentsAndTitleByNo(no);
		model.addAttribute("map",map);
		return "board/view";
	}

	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getUserByNo(no);
		model.addAttribute("boardVo", boardVo);		
		return "board/modify";
	}
	
	@Auth 
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, BoardVo boardVo) {
		boardVo.setUserNo(authUser.getNo());
		/* 수정해야 할 글은 고대로 
		이렇게 textarea에 뿌려야 합니다.
		개행문자 변경도 하지마세요.
		하하하하하
		즐건 코딩 되세요~~~~*/
	 
		boardService.modifyList(boardVo);
		return "redirect:/board/view/"+boardVo.getNo();
	}
	
	@Auth
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, @AuthUser UserVo authUser) {
		boardService.deleteUser(no,authUser.getNo());
		return "redirect:/board";
	}

	
}
