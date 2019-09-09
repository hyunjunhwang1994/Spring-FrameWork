package com.lec.sts16_interceptor.board.command;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.ui.Model;

import com.lec.sts16_interceptor.board.beans.BWriteDAO;
import com.lec.sts16_interceptor.board.beans.BWriteDTO;

public class BViewCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BWriteDAO dao = new BWriteDAO();

		Map<String, Object> map = model.asMap();
		
		int id = (Integer) map.get("id");
		
		BWriteDTO dto = dao.readById(id);
		
		
		model.addAttribute("dto", dto);
		
		
		

	}

}
