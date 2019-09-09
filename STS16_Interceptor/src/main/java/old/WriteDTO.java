package old;

// test_write 테이블을 표현할 빈 객체 정의
//DTO : Data Transfer Object   ( MVC : Model )
public class WriteDTO {
	private int id;    //  wr_id
	private String subject;  // wr_subject
	private String content;  // wr_content
	private String name;     // wr_name
	private int viewCnt;  // wr_viewcnt
	private String regDate;  // wr_regdate

	// 웹개발시...
	// 가능한, 다음 3가지는 이름을 일치시켜주는게 좋습니다.
	// 클래스 필드명 = DB 필드명 = form의 name명

	// 생성자
	public WriteDTO() {
		super();
		System.out.println("WriteDTO() 객체 생성");
	}

	public WriteDTO(int id, String subject, String content, String name, int viewCnt) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.viewCnt = viewCnt;
		System.out.println("WriteDTO(id, subject, content, name, viewcnt) 객체 생성");
	}

	public int getId() {
		System.out.println("getId() 호출");
		return id;
	}

	public void setId(int id) {
		System.out.println("setId(" + id + ") 호출");
		this.id = id;
	}

	public String getSubject() {
		System.out.println("getSubject() 호출");
		return subject;
	}

	public void setSubject(String subject) {
		System.out.println("setSubject(" + subject + ") 호출");
		this.subject = subject;
	}

	public String getContent() {
		System.out.println("getContent() 호출");
		return content;
	}

	public void setContent(String content) {
		System.out.println("setContent(" + content + ") 호출");
		this.content = content;
	}

	public String getName() {
		System.out.println("getName() 호출");
		return name;
	}

	public void setName(String name) {
		System.out.println("setName(" + name + ") 호출");
		this.name = name;
	}

	public int getViewCnt() {
		System.out.println("getViewCnt() 호출");
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		System.out.println("setViewCnt(" + viewCnt + ") 호출");
		this.viewCnt = viewCnt;
	}

	public String getRegDate() {
		System.out.println("getRegDate() 호출");
		return regDate;
	}

	public void setRegDate(String regDate) {
		System.out.println("setRegDate(" + regDate + ") 호출");
		this.regDate = regDate;
	}
	
	// 개발할때 Class의 toString()을 오버라이딩 해주면
	// 디버깅이나 테스팅하기 좋다.
	@Override
	public String toString() {
		return "WriteDTO] " + id + ":" + subject + ":" + content + ":" + name + ":" + viewCnt + ":" + regDate; 
	} // end toString()
} // end DTO class

