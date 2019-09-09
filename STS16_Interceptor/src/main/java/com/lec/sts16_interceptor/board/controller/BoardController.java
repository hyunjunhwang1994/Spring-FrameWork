package com.lec.sts16_interceptor.board.controller;

import java.sql.BatchUpdateException;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lec.sts16_interceptor.board.C;
import com.lec.sts16_interceptor.board.beans.BWriteDTO;
import com.lec.sts16_interceptor.board.command.BCommand;
import com.lec.sts16_interceptor.board.command.BDeleteCommand;
import com.lec.sts16_interceptor.board.command.BListCommand;
import com.lec.sts16_interceptor.board.command.BSelectCommand;
import com.lec.sts16_interceptor.board.command.BUpdateCommand;
import com.lec.sts16_interceptor.board.command.BViewCommand;
import com.lec.sts16_interceptor.board.command.BWriteCommand;

//day 77 07

@Controller
@RequestMapping("/board")
public class BoardController {

	
	private BCommand command;
	private JdbcTemplate template;
	
	public BoardController() {
		System.out.println("BoardController 생성");
	}
	
	
	// Controller도 처음에 스프링 컨테이너 생성시 Bean으로 생성되고,
	// JdbcTemplate template도 설정파일에 기입하여, 스프링 컨테이너생성시 bean으로 생성된다.
	// 그러므로 JdbcTemplate타입의 bean이 Controller의 setTemplate에 자동주입된다.
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		System.out.println("setTemplate 호출");
		this.template = template;
		C.template = template;
	}
	
	
	// day 78 01
	// 현재 Model 에 뭐가 담겨 있는지 궁금할때 사용. (디버깅용)
	public void printModel(Model model) {
		Map<String, Object> map = model.asMap(); // asMap : model -> map
		
		Set<String> keySet = map.keySet();
		
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
	}
	
	
	@RequestMapping("/list.do")
	public String list(Model model) {
		command = new BListCommand();
		command.execute(model);
		
		// test용 
//		model.addAttribute("webapp", "Hello Web");
//		printModel(model);
		
		return "board/list";
	}
	
	
	@RequestMapping("/write.do")
	public String write(Model model) {
		
		return "board/write";
	}
	
	//day 78 03
	@RequestMapping(value="/writeOk.do", method = RequestMethod.POST)
	public String writeOk(BWriteDTO dto, Model model){
		model.addAttribute("dto", dto);	// 커맨드객체를 Model에 담아서 WriteCommand에 넘겨줄것
		new BWriteCommand().execute(model);
		
		return "board/writeOk";
	}
	
	// day 78 06  : 나머지 매커니즘 과제.
	
	
	// view
	@RequestMapping("/view.do")
	public String view(int id, Model model) {
		
		model.addAttribute("id", id);
		new BViewCommand().execute(model);
		
		return "board/view";
	}
	
	
	// update 
	@RequestMapping("/update.do")
	public String update(int id, Model model) {
		
		model.addAttribute("id", id);
		new BSelectCommand().execute(model);
		
		return "board/update";
		
	}
	
	
	// updateOk
	@RequestMapping("/updateOk.do")
	public String updateOk(BWriteDTO dto, Model model) {
		
		
		model.addAttribute("dto", dto);
		new BUpdateCommand().execute(model);
		
		return "board/updateOk";
	}
	
	
	
	
			
	// deleteOk
	@RequestMapping("/deleteOk.do")
	public String deleteOk(int id, Model model) {
		
		model.addAttribute("id", id);
		new BDeleteCommand().execute(model);
		
		
		return "board/deleteOk";
	}
	
	
	
	
	
	
	
	
	
	

	

	
	
	
	
	
	
	
} // end Controller
