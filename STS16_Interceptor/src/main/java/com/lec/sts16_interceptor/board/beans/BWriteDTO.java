package com.lec.sts16_interceptor.board.beans;

import java.sql.Timestamp;

//day 77 08

// test_write 테이블을 표현할 빈 객체 정의
//DTO : Data Transfer Object   ( MVC : Model )
public class BWriteDTO {
	private int id;    //  wr_id
	private String subject;  // wr_subject
	private String content;  // wr_content
	private String name;     // wr_name
	private int viewCnt;  // wr_viewcnt
	// java.sql.timestamp
	private Timestamp regDate;  // wr_regdate

	// 웹개발시...
	// 가능한, 다음 3가지는 이름을 일치시켜주는게 좋습니다.
	// 클래스 필드명 = DB 필드명 = form의 name명

	// 생성자
	public BWriteDTO() {
		super();
	}

	public BWriteDTO(int id, String subject, String content, String name, int viewCnt) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.viewCnt = viewCnt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return String.format("[BWriteDTO %d,%s,%s,%s,%d,%s]", 
				id, subject, content, name, viewCnt, regDate);
	} // end toString()
} // end DTO class

