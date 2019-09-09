package old;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDTO [] arr = null;
		WriteDAO dao = new WriteDAO();
		
		// ***매개변수 검증 필요
		int id = Integer.parseInt(request.getParameter("id"));

		
		try {
			arr = dao.readById(id);		// 읽기 + 조회수증가 트랜잭션 같이 됨
			request.setAttribute("list", arr);	// ** SELECT 쿼리 결과 담음
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
