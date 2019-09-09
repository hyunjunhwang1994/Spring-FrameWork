package old;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();	// DAO객체 생성 ( 커넥션 생성 )
		
		WriteDTO arr[] = null;
		
		try {
			// 트랜잭션 수행
			arr = dao.select();
			
			// "list"란 name으로 request에 arr 값 저장
			// 즉! request에 담아서 컨트롤러에 전달
			request.setAttribute("list", arr); 
			
		} catch (SQLException e) {
			// 만약 여기서 ConnectionPool 사용한다면
			// 여기서 NamingException 도 catch 해주어야 한다.
			e.printStackTrace();
		}
	}

}
