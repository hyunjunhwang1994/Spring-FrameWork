package old;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		WriteDTO [] arr = null;
		WriteDAO dao = new WriteDAO();
		
		try {
			arr = dao.selectById(id);  // 읽기 only
			request.setAttribute("list", arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
