package com.lec.sts16_interceptor.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//day 81 03
@Controller
@RequestMapping("/user")
public class UserController {

	//day 81 06
	// 예제 테스트용 게정
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";
	
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public String loginOk(String id, String pw, HttpSession session) {
		
		String returnUrl = "";
		
		if(session.getAttribute("id") != null) {
			// 기존 id 세션값이 존재한다면 (즉! 로그인 상태라면)
			session.removeAttribute("id"); // 로그인 된상태라면 일단 이전 로그인 정보 제거후 다시 체크.
		}
		
		// ** 원래는 여기서 회원 db 검색
		
		// 간단한 예제이므로 상수랑 비교
		if(ADMIN_ID.equals(id) && ADMIN_PW.equals(pw)) {
			// 로그인성공
			session.setAttribute("id", id); // 세션에 로그인된 사용자정보 추가.
			
			// 원래 가고자 했던 url 이 있었다면 해당 url로 보내주기. ( session에 담겨 있음 )
			String priorUrl = (String)session.getAttribute("url_prior_login");
		
			if(priorUrl != null) {
				returnUrl = "redirect:" + priorUrl;
				session.removeAttribute("url_prior_login");
			}else {
				// 원래가고자 하던 페이지가 없었다면 list.do로 이동
				returnUrl = "redirect:/board/list.do";
				
			}
			
			
		}else { // 로그인 실패
			
			returnUrl = "user/logfail";
		}
		
		
		
		
		return returnUrl;
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "user/logout";
	}
		
		
		
	
		
	
		
}
