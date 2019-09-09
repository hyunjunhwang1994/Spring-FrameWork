package com.lec.sts16_interceptor.board.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.lec.sts16_interceptor.board.beans.BWriteDAO;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();

		BWriteDAO dao = new BWriteDAO();
		
		int id = (Integer) map.get("id");
		
		int result = dao.deleteById(id);
		
		model.addAttribute("result", result);

	}

}
