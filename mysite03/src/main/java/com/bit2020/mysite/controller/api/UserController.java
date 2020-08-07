package com.bit2020.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2020.mysite.dto.JsonResult;
import com.bit2020.mysite.service.UserService;

@Controller("userAPIController")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JsonResult checkEmail(
			@RequestParam(value="email",required=true,defaultValue="")String email) {
		boolean exist = userService.existUser(email);
		
		return JsonResult.success(exist);
	}
}
