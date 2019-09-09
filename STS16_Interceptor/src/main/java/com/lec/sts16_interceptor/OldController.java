package com.lec.sts16_interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import old.DeleteCommand;
import old.ListCommand;
import old.SelectCommand;
import old.UpdateCommand;
import old.ViewCommand;
import old.WriteCommand;


// day 77 03
@Controller
@RequestMapping("/old")
public class OldController {

	// 기존에 JSP MVC2 방식으로 사용하던 게시판 메커니즘을, 스프링에서 사용! (old 폴더안에 파일들이 기존 JSP MVC2방식때 사용하던 파일들, Controller만 변경해도 사용가능 )
	// Model 객체를 사용하지 않고 JSP MVC2 Command 방식으로 했기 때문에 상황에 맞게 사용함.
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		
		new ListCommand().execute(request, response);
		
		return "old/list";
	}
	
	@RequestMapping("/write.do")
	public String write(HttpServletRequest request, HttpServletResponse response) {
		
		return "old/write";
	}

	@RequestMapping("/writeOk.do")
	public String writeOk(HttpServletRequest request, HttpServletResponse response) {
		new WriteCommand().execute(request, response);
		
		return "old/writeOk";
	}
	
	@RequestMapping("/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		new ViewCommand().execute(request, response);
		
		return "old/view";
	}
	
	@RequestMapping("/update.do")
	public String update(HttpServletRequest request, HttpServletResponse response) {
		new SelectCommand().execute(request, response);
		
		return "old/update";
	}
	
	@RequestMapping("/updateOk.do")
	public String updateOk(HttpServletRequest request, HttpServletResponse response) {
		new UpdateCommand().execute(request, response);
		
		return "old/updateOk";
	}
	
	@RequestMapping("/deleteOk.do")
	public String deleteOk(HttpServletRequest request, HttpServletResponse response) {
		new DeleteCommand().execute(request, response);
		
		return "old/deleteOk";
	}
		
	
}
