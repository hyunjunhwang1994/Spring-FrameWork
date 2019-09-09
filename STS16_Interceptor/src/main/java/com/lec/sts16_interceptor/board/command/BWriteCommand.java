package com.lec.sts16_interceptor.board.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.lec.sts16_interceptor.board.beans.BWriteDAO;
import com.lec.sts16_interceptor.board.beans.BWriteDTO;
//day 78 04
public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// model 안에 담겨 있는 값 꺼내기
		Map<String, Object> map = model.asMap(); // Model 안의 attribute 꺼낼때 . map으로 바꿔 사용
		
		BWriteDTO dto = (BWriteDTO)map.get("dto");
		BWriteDAO dao = new BWriteDAO();
		
		int result = dao.insert(dto); // 트랜잭션 발생
		
		model.addAttribute("result", result);
		
	}

}
