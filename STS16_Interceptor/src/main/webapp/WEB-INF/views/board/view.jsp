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
<title>${dto.subject }</title>
</head>

<script>
function chkDelete(id){
	// 삭제여부, 확인하고 진행하기
	var r = confirm("삭제하시겠습니까?");
	
	if(r){
		location.href = "deleteOk.do?id=" + id;
	}
	
}

</script>

<body>

<h2>${dto.subject }</h2><br>
id : ${dto.id }<br>
작성자 : ${dto.name }<br>
제목 : ${dto.subject }<br>
등록일 : ${dto.regDate }<br>
조회수 : ${dto.viewCnt }<br>
내용 : <br>
<hr>
<div>
${dto.content }
</div>
<hr><br>
<button onclick="location.href = 'update.do?id=${dto.id}'">글 수정하기</button>
<button onclick="location.href = 'list.do'">목록보기</button>
<button onclick="location.href = 'write.do'">새글등록</button>
<button onclick="chkDelete(${dto.id})">글 삭제하기</button>




</body>
</html>
	</c:otherwise>
</c:choose>





















