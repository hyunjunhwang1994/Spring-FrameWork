<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${empty dto }">
	<script>
		alert("해당 글은 삭제되거나 존재하지 않습니다.");
		history.back();
	</script>
	</c:when>
		
	<c:otherwise>
	

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>수정 ${dto.subject }</title>
</head>
<script>
// 1차 프론트엔드 form 검증(validation)
// '작성자', '제목' 입력 필수!

function chkSubmit(){
	var frm = document.forms["frm"];
	var subject = frm["subject"].value.trim();
	
	if(subject == ""){
		alert("제목은 반드시 작성해야 합니다.");
		frm["subject"].focus();
		return false;
	}
	
	return true;
}
</script>


<body>
<h2>글 수정 ${dto.subject }</h2>
<%-- 글 내용이 많을수 있기 때문에 POST 방식 사용 --%>
<form name="frm" action="updateOk.do" method="post" onsubmit="return chkSubmit()">
<input type="hidden" name="id" value="${dto.id }"/> <!-- 글번호 같이 넘기기 (post방식) -->
작성자: ${dto.name }<br> <%-- 작성자 이름은 변경불가. --%>
제목:
<input type="text" name="subject" value="${dto.subject }"/><br>
내용:<br>
<textarea name="content">${dto.content }</textarea>
<br><br>
<input type="submit" value="수정"/>
</form>
<br>
<button onclick="history.back();">이전으로</button>
<button type="button" onclick="location.href='list.do'">목록으로</button>
</body>

</html>



</c:otherwise>
</c:choose>












