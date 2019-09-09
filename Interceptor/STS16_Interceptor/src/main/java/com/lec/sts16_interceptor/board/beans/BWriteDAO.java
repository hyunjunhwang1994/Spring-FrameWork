package com.lec.sts16_interceptor.board.beans;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.lec.sts16_interceptor.board.C;

//day 77 09

public class BWriteDAO {
	JdbcTemplate template;
	
	public BWriteDAO() {
		this.template = C.template;
	}
	
		
	// 전체 SELECT
	public ArrayList<BWriteDTO> select(){
		
		// 이렇게 간단해진다!!
		return (ArrayList<BWriteDTO>)template.query(C.SQL_WRITE_SELECT, 
				new BeanPropertyRowMapper<BWriteDTO>(BWriteDTO.class));
		
	}
	
	
	// day 78 02
	// 새글 작성  : 제목, 내용, 작성자 Insert
	public int insert(final BWriteDTO dto) {
		
		
		return template.update(C.SQL_WRITE_INSERT, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// effective final
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setString(3, dto.getName());
				
				
			}
			
		});

	}
	
	
	// View 
	public BWriteDTO readById(final int id){
		BWriteDTO dto = null;
		
		this.template.update(C.SQL_WRITE_INC_VIEWCNT, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
				
			}
		});
		
	
	
	List<BWriteDTO> list = 
			template.query(C.SQL_WRITE_SELECT_BY_ID, new PreparedStatementSetter() {
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, id);
		}
	}, new BeanPropertyRowMapper<BWriteDTO>(BWriteDTO.class));

	if(list.size() > 0) dto = list.get(0);  // 첫번째 아이템

	return dto;
	}
	
	
	
	
	
	
	// update	
	public BWriteDTO selectById(final int id) {
		BWriteDTO dto = null;
		
		List<BWriteDTO> list = template.query(C.SQL_WRITE_SELECT_BY_ID, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
				
			}
		}, new BeanPropertyRowMapper<BWriteDTO>(BWriteDTO.class));
		
		if(list.size() > 0) dto = list.get(0);  // 첫번째 아이템
		
		return dto;
	}
	
	
	// updateOk
	public int update(final BWriteDTO dto) {
		
	return template.update(C.SQL_WRITE_UPDATE, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// effective final
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setInt(3, dto.getId());
				
				
			}
			
		});
		
	}
	
	
	
	// deleteOk
	public int deleteById(final int id) {
		
		return template.update(C.SQL_WRITE_DELETE_BY_ID, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		});
		
	}


	
	
	
	
	
	
	
}
