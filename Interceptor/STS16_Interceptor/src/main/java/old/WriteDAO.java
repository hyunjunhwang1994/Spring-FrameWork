package old;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class WriteDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다   mvc: controller
	public WriteDAO(){
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("WriteDAO생성, 데이터베이스 연결!!");
		} catch (Exception e) {			
			e.printStackTrace();
		} // end try
	}// 생성자
	
	// DB 자원 반납 메소드, 만들어놓으면 편함..
	public void close() throws SQLException{
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	} // end close();
	
	//write.jsp
	// 새글 작성 메소드 <-- DTO
	public int insert(WriteDTO dto) throws SQLException {
		String subject = dto.getSubject();
		String content = dto.getContent();
		String name = dto.getName();
		
		int cnt = this.insert(subject, content, name);
		return cnt;
	}

	// 새글 작성 메소드 <-- 제목, 내용, 작성자
	public int insert(String subject, String content, String name) throws SQLException {
		int cnt = 0;
		try {
			// 트랜잭션 실행
			pstmt = conn.prepareStatement(D.SQL_WRITE_INSERT);
			pstmt.setString(1, subject);	// 게시판 제목	
			pstmt.setString(2, content);	// 글 내용
			pstmt.setString(3, name);		// 글 작성자
			cnt = pstmt.executeUpdate();	// DML = executeUpdate
		} finally {
			close();
		}
		return cnt;
	} // end insert();
	
	
	// list.jsp
	// ResultSet --> DTO 배열로 리턴
	public WriteDTO[] createArray(ResultSet rs) throws SQLException {
		ArrayList<WriteDTO> list = new ArrayList<WriteDTO>();
		
		while(rs.next()) {
			int id = rs.getInt("wr_id");
			String subject = rs.getString("wr_subject");
			String content = rs.getString("wr_content");
			if(content == null) content = ""; // null 처리꼭해주기.
			String name = rs.getString("wr_name");
			int viewcnt = rs.getInt("wr_viewcnt");
			Date d = rs.getDate("wr_regdate");	
			Time t = rs.getTime("wr_regdate");
			String regdate = "";
			if(d != null){
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
			
			WriteDTO dto = new WriteDTO(id, subject, content, name, viewcnt);
			dto.setRegDate(regdate);	
			list.add(dto);
		} // end while()
		
		
		int size = list.size();
		
		WriteDTO [] arr = new WriteDTO[size];
		list.toArray(arr);	// 리스트에 저장된 데이터를 배열객체에 복사
		return arr;
	} // end createArray()
	
	// 전체 SELECT
	public WriteDTO[] select() throws SQLException{
		WriteDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
	} // end select()
	
	
	// 특정 id의 글만 SELECT
	public WriteDTO[] selectById(int id) throws SQLException {
		WriteDTO  arr[] = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
		
	}
	

	
	// view.jsp 
	// 특정 id 글내용 읽기 + 조회수 증가
	// viewcnt 도 1증가해야하고, 글을 읽어와야한다 --> 즉 트랜잭션 처리.
	public WriteDTO [] readById(int id) throws SQLException{
		int cnt = 0;
		WriteDTO [] arr = null;
	
		
		try {
			// 여러 쿼리문을 하나의 트랜잭션으로 처리하기
			conn.setAutoCommit(false);	// 일단 auto-commit 기능 끄기
			
			// 조회수 증가 쿼리 실행
			pstmt = conn.prepareStatement(D.SQL_WRITE_INC_VIEWCNT);
			pstmt.setInt(1, id);
			cnt = pstmt.executeUpdate();
			// ※원래는 cnt 결과값에 따른 처리 필요
			
			pstmt.close();
			
			// 글 읽어오기 쿼리 실행
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			arr = createArray(rs);
			conn.commit();		// 트랜잭션 성공
			
		}catch(SQLException e){	// rollback을 위해 여기는 catch 절 필요.
			conn.rollback();	// 예외 발생시 rollback
			throw e;			// 다시 발생된 예외를 throw 해준다. ( 처리안하고 위로올려버림 )
		}finally {
			close();
		}
		
		
		return arr;
	} // end readById()
	
	
	
	
	// updateOk.jsp
	// 특정id의 글 수정 ( 제목, 내용 )
	public int update(int id, String subject, String content) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_UPDATE);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, id);
			cnt = pstmt.executeUpdate();
		}finally {
			close();
		}
		
		return cnt;
	}
	
	
	// deleteOk.jsp
	// 특정 id의 글 삭제하기
	public int deleteById(int id) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_DELETE_BY_ID);
			pstmt.setInt(1, id);
			cnt = pstmt.executeUpdate();
		}finally {
			close();
		}
		return cnt;
	}// end deleteById()
	
	
	

	
	
	
	
	
	
	

	
	
	
} // end DAO
