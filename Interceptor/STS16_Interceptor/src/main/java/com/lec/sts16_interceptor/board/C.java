package com.lec.sts16_interceptor.board;

import org.springframework.jdbc.core.JdbcTemplate;

// day 77 05
// 상수값 Class
public class C {
	
	// 스프링 컨테이너에 생성된 JdbcTemplate를 받아와서
	// 언제, 어느곳 에서든지 가져다 쓸 수 있도록 public static로 설정
	public static JdbcTemplate template;
	
	// 게시글 관련 쿼리문
	public static final String SQL_WRITE_INSERT = 
			"INSERT INTO test_write"
			+ "(wr_id, wr_subject, wr_content, wr_name) "
			+ "VALUES"
			+ "(test_write_seq.nextval, ?, ?, ?)";
	
	// DB의 Column명과 DTO의 Field(멤버변수)명이 같아야하므로,
	// * 이아닌 이름을 다지정하여 바꾸어서 사용 ( 설계시에 이름을 같게하면 * 로 해도상관없음 )
	public static final String SQL_WRITE_SELECT = 
			"SELECT wr_id id, wr_subject subject, "
			+ "wr_content content, wr_name name, wr_viewcnt viewcnt, "
			+ "wr_regdate regDate FROM test_write ORDER BY wr_id DESC";
	
	public static final String SQL_WRITE_SELECT_BY_ID = 
			"SELECT wr_id id, wr_subject subject, "
			+ "wr_content content, wr_name name, wr_viewcnt viewcnt, "
			+ "wr_regdate regDate FROM test_write WHERE wr_id=?";

	
	public static final String SQL_WRITE_INC_VIEWCNT = 
			"UPDATE test_write SET wr_viewcnt = wr_viewcnt + 1 WHERE wr_id = ?";
	
	public static final String SQL_WRITE_DELETE_BY_ID = 
			"DELETE FROM test_write WHERE wr_id = ?";

	public static final String SQL_WRITE_UPDATE =
			"UPDATE test_write SET wr_subject = ?, wr_content = ? WHERE wr_id = ?";
	

	
}
