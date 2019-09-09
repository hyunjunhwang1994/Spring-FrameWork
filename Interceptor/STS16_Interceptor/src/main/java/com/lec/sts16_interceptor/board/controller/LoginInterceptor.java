package com.lec.sts16_interceptor.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// day 81 01								// not interface
public class LoginInterceptor extends HandlerInterceptorAdapter {

	// Shife + alt + s + v     ( override 단축키 ) 

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 뷰 response 후 실행됨
		System.out.println("[afterCompletion]");
		super.afterCompletion(request, response, handler, ex);
	}

	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 실행후, 뷰 리스폰스 직전 실행됨
		System.out.println("[postHandle]");
		super.postHandle(request, response, handler, modelAndView);
	}

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러 실행전 실행됨
		System.out.println("[preHandle]");
		
		//  day 81 08
		
		// session 객체 가져오기
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");	// 로그인한 id가 있는지.. ( 로그인상태확인  )
		
		
		// 로그인 되어 있지않은경우
		if(id == null) {
			
			// 직전 요청 url을 세션에 기록
			String urlPrior = request.getRequestURL().toString() + "?" + request.getQueryString();
			session.setAttribute("url_prior_login", urlPrior);
			
			// 만약 로그인이 되어있지 않았다면, 로그인 페이지로 강제 redirect;
			response.sendRedirect(request.getContextPath() + "/user/login");
			
			return false; // ★더이상 컨트롤러 핸들러 진행하지 않도록 false 리턴.
		}
		
		return true;	// ★컨트롤러 핸들러 진행하도록 true 리턴.
	}
	
	
	

}









