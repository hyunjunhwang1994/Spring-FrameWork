<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- //day 77 11 -->

<!-- jstl사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<style>
table, th, td{
	border : 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<hr>
	<h2>리스트</h2>
<c:choose>
	<c:when test="${empty list || fn.length(list) == 0 }">
		멤버가 없습니다.<br>
	</c:when>

	<c:otherwise>
	
<table style="width:100%;">
	<tr>
		<th>번호</th> <!-- 원래는 실제로 내부에서 사용되는 고유 id값(pk)는 보여주지않는다.(내부처리용)
					  따로 Row Number(줄넘버)를 만들어서 보여줘야하지만 예제이므로 그냥 고유 ID값을 쓰겠다. -->
		<th>제목</th>
		<th>작성자</th>
		<th>조회수</th>
		<th>등록일</th>
	</tr>

		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.id }</td>
				<td><a href="view.do?id=${dto.id }">${dto.subject }</a></td>
				<td>${dto.name }</td>
				<td>${dto.viewCnt }</td>
				<td>${dto.regDate }</td>
			</tr>
		</c:forEach>
		
		</table>
	</c:otherwise>
</c:choose>
<br>
<button onclick="location.href = 'write.do'">새글 등록</button>		
</body>
</html>







