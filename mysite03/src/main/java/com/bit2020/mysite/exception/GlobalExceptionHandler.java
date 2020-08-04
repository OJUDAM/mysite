package com.bit2020.mysite.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public void handlerException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e)throws Exception {
		
		//1. 로깅(logging)
		System.out.println("error : "+e);
		//2. 안내 페이지
		request.setAttribute("exception", e);
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
	}
}
