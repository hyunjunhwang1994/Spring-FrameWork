package old;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();
		int result = 0;
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			result = dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", result);
	}

}
