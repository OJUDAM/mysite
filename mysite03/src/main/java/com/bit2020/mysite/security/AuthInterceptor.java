package com.bit2020.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2020.mysite.security.Auth.Role;
import com.bit2020.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 1. Handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// DefaultServletHandler가 처리하는 경우(
			// 보통, assets의 정적 자원 접근
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. HandlerMethod의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. @Auth 가 안 붙어있는 경우
		if(auth == null) {
			return true;
		}
		
		// 5. @Auth가 붙어 있기 때문에 인증(Authentification) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (session == null)? null:(UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
			
		// 6. @Auth 가 붙어있고 인증도 되어있음
		Role role = auth.value();
		
		// 7. User Role 접근이면 인증만 되어 있으면 허용
		
		if(role == Role.USER) {
			return true;
		}
		
		// 8 ADMiN 권한이 없는 사용자이면 메인으로
		if("ADMIN".equals(authUser.getRole()) == false) {
			response.sendRedirect(request.getContextPath()+"/");
			return false;
		}
		
		//9. ADMIN 접근 허용
		return true;
	}

}
