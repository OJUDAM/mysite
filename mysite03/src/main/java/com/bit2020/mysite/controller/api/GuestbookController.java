package com.bit2020.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2020.mysite.dto.JsonResult;
import com.bit2020.mysite.service.GuestbookService;
import com.bit2020.mysite.vo.GuestBookVo;

@Controller("guestbookAPIConroller")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public JsonResult delete(@ModelAttribute GuestBookVo vo) {
		boolean result = guestbookService.deleteFromList(vo);
		return JsonResult.success(result ? vo.getNo() : -1);
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public JsonResult list(
			@RequestParam(value="no", required=true, defaultValue="0")Long no) {
		List<GuestBookVo> list = guestbookService.getMessageList();
		return JsonResult.success(list);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public JsonResult add(@RequestBody GuestBookVo vo) {
		guestbookService.addList(vo);
		return JsonResult.success(vo);
	}
			
	
}
