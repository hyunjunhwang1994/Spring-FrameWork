package com.lec.sts16_interceptor.board.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.lec.sts16_interceptor.board.beans.BWriteDAO;
import com.lec.sts16_interceptor.board.beans.BWriteDTO;
//day 77 10
public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BWriteDAO dao = new BWriteDAO();
		ArrayList<BWriteDTO> list = dao.select();
		
		
		model.addAttribute("list", list);
	}

}
