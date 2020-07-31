package com.bit2020.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2020.webutil.MVCUtil;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("doGet() called...");
		int visitCount = 0;
		
		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		if(cookies !=null && cookies.length>0) {
			for(Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if("visitCount".equals(cookieName)) {
					visitCount = Integer.parseInt(cookie.getValue());
				}
			}
		}
		
		// 쿠키 쓰기(굽기)
		visitCount++;
		Cookie cookie = new Cookie("visitCount",String.valueOf(visitCount));
		cookie.setMaxAge(24*60*60); // 하루
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
			
		MVCUtil.forward("main/index", request, response);
		}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service()called...");
		super.service(req, resp);
	}
	@Override
	public void destroy() {
		System.out.println("destroy() called...");
		super.destroy();
	}
	@Override
	public void init() throws ServletException {
		System.out.println("init() called...!!!");

		// 1. 컨테이너 설정 파일(bean 설정)의 
		//    path를 가져오는 작업
		
		// 2 . 컨테이너를 만든다.
		
		
		super.init();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
