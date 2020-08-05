package com.bit2020.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2020.mysite.service.GuestbookService;
import com.bit2020.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<GuestBookVo> list = guestbookService.findAll();
		model.addAttribute("list",list);
		return "/guestbook/list";
	}
	
	@RequestMapping(value ="/add", method=RequestMethod.POST)
	public String add(GuestBookVo vo) {
		guestbookService.addList(vo);
		return "redirect:/guestbook/";
	}
	
	
	@RequestMapping("/delete/{no}")
	public String delete(Model model,@PathVariable("no")Long no) {
		model.addAttribute("no",no);
		return "/guestbook/delete";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(
			@RequestParam(value="no",required=true,defaultValue="0")Long no,
			@RequestParam(value="password",required=true,defaultValue="")String password) {
		guestbookService.deleteFromList(no, password);
		return "redirect:/guestbook/";
	}
	
}