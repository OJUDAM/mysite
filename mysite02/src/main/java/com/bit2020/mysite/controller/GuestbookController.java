package com.bit2020.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bit2020.mvc.util.MVCUtil;
import com.bit2020.mysite.repository.GuestbookRepository;
import com.bit2020.mysite.vo.GuestBookVo;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		
		if("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String message = request.getParameter("content");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			
			new GuestbookRepository().insert(vo);
			MVCUtil.redirect(request.getContextPath()+"/guestbook", request, response);
		}else if("deleteform".equals(action)) {
			MVCUtil.forward("guestbook/deleteform", request, response);
		}else if("delete".equals(action)) {
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			 
			if(!new GuestbookRepository().delete(Long.parseLong(no), password)) {
				String result = "fail";
				request.setAttribute("result", result);
				MVCUtil.forward("guestbook/deleteform", request, response);
			}else {
				MVCUtil.redirect(request.getContextPath()+"/guestbook", request, response);
			}
		}
		else {
			List<GuestBookVo> list = new GuestbookRepository().findAll();
			request.setAttribute("list", list);
			MVCUtil.forward("guestbook/list", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
