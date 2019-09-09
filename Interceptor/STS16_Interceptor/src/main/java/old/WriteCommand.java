package old;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		WriteDAO dao = new WriteDAO();
		
		// 입력한 값 받아오기
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// ** 유효성 체크 **
		
		// **		  **
		
		
		try {
			cnt = dao.insert(subject, content, name);	// 트랜잭션 수행
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("result", cnt);		// ★★ DML 결과값담음
		
		
	}

}
