package com.bit2020.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2020.mysite.vo.GuestBookVo;

@Controller
public class MainController {
	
	@RequestMapping("")
	public String index() {
		
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "안녕하세요~~";
	}
	
	@ResponseBody
	@RequestMapping("/json")
	public Object json() {
		GuestBookVo vo = new GuestBookVo();
		vo.setName("오주담");
		vo.setNo(10L);
		vo.setMessage("안녕하세요~~");
		
		return vo;
	}
}
