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

	/* handler */
	@RequestMapping("")
	public String index(Model model) {
		List<GuestBookVo> list = guestbookService.getMessageList();
		model.addAttribute("list",list);
		return "guestbook/index";
	}
	
	@RequestMapping(value ="/add", method=RequestMethod.POST)
	public String add(GuestBookVo vo) {
		guestbookService.addList(vo);
		return "redirect:/guestbook/";
	}
	
	
	@RequestMapping("/delete/{no}")
	public String delete(Model model,@PathVariable("no")Long no) {
		model.addAttribute("no",no);
		return "guestbook/delete";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(GuestBookVo vo) {
		guestbookService.deleteFromList(vo);
		return "redirect:/guestbook/";
	}
	
}
