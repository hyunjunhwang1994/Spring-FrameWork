package old;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

////day 57 02  
public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
